/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.traxadaptor.service.impl;

import ca.bc.gov.educ.exception.EntityNotFoundException;
import ca.bc.gov.educ.grad.dao.GradToIsdDataConvertBean;
import ca.bc.gov.educ.grad.dto.ReportData;
import ca.bc.gov.educ.isd.eis.common.DomainServiceException;
import ca.bc.gov.educ.isd.eis.trax.db.StudentDemographic;
import ca.bc.gov.educ.isd.eis.trax.db.StudentProfileMasterLite;
import ca.bc.gov.educ.isd.eis.trax.db.TabSchool;
import ca.bc.gov.educ.isd.traxadaptor.dao.impl.SchoolMasterEntity;
import ca.bc.gov.educ.isd.traxadaptor.dao.impl.TabSchoolEntity;
import ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl.TswTranDemogEntity;
import ca.bc.gov.educ.isd.traxadaptor.dao.utils.TRAXThreadDataUtility;
import ca.bc.gov.educ.isd.traxadaptor.impl.StudentDemographicImpl;
import ca.bc.gov.educ.isd.traxadaptor.service.StudentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.trimSafe;
import static ca.bc.gov.educ.isd.eis.roles.Roles.*;

/**
 * This is an intermediate layer between the database entities and the unmanaged
 * data objects which are returned to the requesting services. It is a stateless
 * bean providing access to the container managed persistence. These methods
 * provide student information for the student demographic service.
 * <p>
 * The data value objects are created using a JPQL constructor expression where
 * the class constructor is called with the supplied query values. These
 * projected attributes come from 1 or more JPA entities. The outlying
 * attributes which are not directly populated in this manner are individually
 * set by calling additional methods. These attributes may require conditional
 * logic or multiple entity searches to determine what the attribute value
 * is.</p>
 *
 * @author CGI Information Management Consultants Inc.
 */
@Repository
@DeclareRoles({TRAX_READ, USER, FULFILLMENT_SERVICES_USER, PUBLIC_USER})
public class StudentDataBean implements StudentData, Serializable {

    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = StudentDataBean.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    Character DEFAULT_CURRENT_FORMER_FLAG = 'C';

    private static final String QUERY_STUDENT_WITH_GRAD_MINCODE_BY_PEN
            = "SELECT new " + StudentDemographicImpl.class.getCanonicalName() + "(s, c1, c2) "
            + "FROM StudentMasterEntity s, TabSchoolEntity c1, TabSchoolEntity c2 "
            + "WHERE s.studNo = ?1 and s.mincode = c1.mincode and s.mincodeGrad = c2.mincode and (s.mincodeGrad IS NOT NULL OR s.mincodeGrad != ' ')";

    private static final String QUERY_STUDENT_WITH_MINCODE_BY_PEN
            = "SELECT new " + StudentDemographicImpl.class.getCanonicalName() + "(s, c1) "
            + "FROM StudentMasterEntity s, TabSchoolEntity c1 "
            + "WHERE s.studNo = ?1 and s.mincode = c1.mincode and (s.mincodeGrad IS NULL OR s.mincodeGrad = ' ')";

    private final static int DEF_STRINGBUILDER_SIZE = 1024;
    private final static String QUERY_STUDENT_PROFILE_MASTER_LITE = "FROM StudentProfileMasterLiteEntity WHERE ";

    private final static String SPACE = " ";
    private final static String LINEFEED = "\n";
    private final static String LIKE_UPPER_SQL_OPER = "LIKE UPPER(?)";
    private final static String LIKE_WILDCARD = "%";
    private final static String AND_SQL_OPER = "AND";
    private final static String NUMBER_PATTERN = "^\\d+$";
    private final static String WORD_PATTERN = "^\\w+$";

    @Autowired
    GradToIsdDataConvertBean gradtoIsdDataConvertBean;

    /**
     * This first tries to find a student record with a grad min code and, if
     * found, creates a StudentDemographic instances using the student, the
     * student's former school, and student's graduation school. The result is
     * then returned.
     *
     * If the student does not have a grad min code, then this creates a
     * StudentDemographic instance using the student and student's school, with
     * a null graduation school (it is one in the same with the student's
     * school).
     *
     * @param pen The student PEN to use for the search.
     * @return
     */
    @Override
    @RolesAllowed({TRAX_READ, USER, FULFILLMENT_SERVICES_USER, PUBLIC_USER})
    public List<? extends StudentDemographic> findDemogByPEN(final String pen) {
        final String _m = "findDemogByPEN(String)";
        LOG.entering(CLASSNAME, _m);

        List<? extends StudentDemographic> results = findDemogByPEN(
                pen,
                QUERY_STUDENT_WITH_GRAD_MINCODE_BY_PEN);

        // No student found with a grad mincode, so look up using a mincode.
        if (results == null || results.isEmpty()) {
            results = findDemogByPEN(pen, QUERY_STUDENT_WITH_MINCODE_BY_PEN);
        }

        LOG.exiting(CLASSNAME, _m);
        return results;
    }

    /**
     * Set the 'print transcript eligibility flag based on a series of checks.
     * First set the eligibility to TRUE and conduct a sequence of tests to set
     * it to FALSE. First check the schools attribute where the school is the
     * secondary school the student attended. Second check the prov_exam list to
     * see if the student has completed any courses. Third check the stud_xcrse
     * list to see if the student has completed any courses.
     *
     * @param student
     */
    private void setTranscriptEligibility(final StudentDemographicImpl student) {
        final String _m = "setTranscriptEligibility(StudentDemographicImpl)";
        LOG.entering(CLASSNAME, _m);
        student.setTranscriptEligible(Boolean.TRUE);
        final String mincode = student.getMincode();
        final String pen = student.getPen();
        Boolean isEligible = checkSchool(mincode);
        student.setTranscriptEligible(isEligible);

        if (isEligible) {

            isEligible = checkCourses(pen, "StsTranCourseEntity.findByStudNo");

        }

        final String logString = buildLogString("For pen {0}, isEligible is >{1}<", pen, isEligible);

        LOG.log(Level.FINE, logString);

        student.setTransEligibilityFlag(Boolean.toString(isEligible));

        LOG.exiting(CLASSNAME, _m);
    }

    /**
     * Using the students <code>mincode</code> value lookup the school
     * information. If the school xcript elig character is 'N' or 'n' then
     * return a FALSE value. If there is a problem with the mincode such as it
     * is null or empty, then return FALSE. If there is a data error such as no
     * school is found with the mincode or more than one school is found with
     * the mincode then return FALSE. This is part of the algorithm to determine
     * if a student is eligible to print a transcript.
     *
     * @param mincode
     * @return FALSE if the school transcript eligible flag is 'N' or if it can
     * not be determined.
     */
    private Boolean checkSchool(final String mincode) {
        final String _m = "checkSchool(String)";
        LOG.entering(CLASSNAME, _m);

        // FIXME: Methods must exit from single location.
        if (mincode == null || mincode.isEmpty()) {
            return false;
        }

        ReportData reportData = TRAXThreadDataUtility.getGenerateReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    null,
                    "Report Data not exists for the current report generation");
            LOG.throwing(CLASSNAME, _m, dse);
            throw dse;
        }

        Boolean value = true;
        LOG.fine("Create checkSchool query.");

        try {
            final Character eligible = gradtoIsdDataConvertBean.getSchoolEligibility(reportData);

            if (eligible == null || eligible.equals('N') || eligible.equals('n')) {
                value = false;
            }
        } catch (final EntityNotFoundException ex) {
            final String simpleName = ex.getClass().getSimpleName();
            value = false;
            // Unable to determine correct record.
            LOG.log(Level.WARNING,
                    "Invalid number of school entries found for school mincode {0}, catching exception {1}",
                    new Object[]{mincode, simpleName}
            );
        }

        LOG.exiting(CLASSNAME, _m);
        return value;
    }

    /**
     * Search entities using the provided named query looking for any record
     * associated with the student PEN and return a FALSE value if one does not
     * exist. This is part of the algorithm to determine if a student is
     * eligible to print a transcript.
     *
     * @param id
     * @param queryName
     * @return FALSE if no course records exist
     */
    private Boolean checkCourses(final String id, final String queryName) {
        final String _m = "checkCourses(String, String)";
        LOG.entering(CLASSNAME, _m);

        ReportData reportData = TRAXThreadDataUtility.getGenerateReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    null,
                    "Report Data not exists for the current report generation");
            LOG.throwing(CLASSNAME, _m, dse);
            throw dse;
        }

        final Boolean result = !gradtoIsdDataConvertBean.getTranscriptCources(reportData).isEmpty();

        LOG.exiting(CLASSNAME, _m);
        return result;
    }

    /**
     * Set the student type of 'F'ormer or 'C'urrent based on the flag found in
     * the <code>StsTranCourseEntity</code>.
     *
     * @param student
     */
    private void setFormerStudent(final StudentDemographicImpl student) {
        final String _m = "setFormerStudent(StudentDemographicImpl)";
        LOG.entering(CLASSNAME, _m);

        final String pen = student.getPen();

        final Character currentFormerFlag = getCurrentFormerFlag(pen, "TswTranDemogEntity.findByStudNo");

        student.setStudentType(currentFormerFlag);

        final String logString = buildLogString("[PEN:{0}] Student Type {1}", pen, student.getStudentType());

        LOG.log(Level.FINE, logString);

        LOG.exiting(CLASSNAME, _m);
    }

    /**
     *
     * @param pen
     * @param queryName
     * @return Date
     */
    private Character getCurrentFormerFlag(final String pen, final String queryName) {
        final String _m = "getCurrentFormerFlag(String, String)";
        LOG.entering(CLASSNAME, _m);

        Character retVal = DEFAULT_CURRENT_FORMER_FLAG;

        try {

            ReportData reportData = TRAXThreadDataUtility.getGenerateReportData();

            if (reportData == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        null,
                        "Report Data not exists for the current report generation");
                LOG.throwing(CLASSNAME, _m, dse);
                throw dse;
            }

            final TswTranDemogEntity tde = gradtoIsdDataConvertBean.getTswTranDemog(reportData);
            retVal = tde.getCurrentFormerFlag();

        } catch (EntityNotFoundException ex) {
            String simpleName = ex.getClass().getSimpleName();
            // this indicates a data issue.  Unable to determine correct record.

            final String exceptionLogString = buildLogString("Issues with querying results for pen {0}, catching exception {1}", pen, simpleName);

            LOG.log(Level.WARNING, exceptionLogString);
        }

        final String logString = buildLogString("For pen {0}, currentFormerFlag is >{1}<", pen, retVal);

        LOG.log(Level.FINE, logString);

        LOG.exiting(CLASSNAME, _m);

        return retVal;
    }

    /**
     * Search the school master entities looking for the student mincode and set
     * the school category code.
     *
     * @param student
     */
    private void setSchoolCategory(final StudentDemographicImpl student) {
        final String _m = "setSchoolCategory(StudentDemographicImpl)";
        LOG.entering(CLASSNAME, _m);

        final String mincode = student.getMincode();

        ReportData reportData = TRAXThreadDataUtility.getGenerateReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    null,
                    "Report Data not exists for the current report generation");
            LOG.throwing(CLASSNAME, _m, dse);
            throw dse;
        }

        try {
            final SchoolMasterEntity school = gradtoIsdDataConvertBean.getSchoolMaster(reportData);
            final String cat = school.getSchoolCategoryCode();
            final String category = trimSafe(cat);
            student.setSchoolCategory(category);
        } catch (final EntityNotFoundException ex) {
            final String simpleName = ex.getClass().getSimpleName();
            // this indicates a data issue.  Unable to determine correct record.

            final String logString = buildLogString("Unable to find a School Master record with school mincode {0}, catching exception {1}",
                    mincode,
                    simpleName);

            LOG.log(Level.WARNING, logString);

            student.setSchoolCategory("");
        }

        LOG.exiting(CLASSNAME, _m);
    }

    /**
     * The school information is conditionally obtained from different
     * tab_school records.
     * <p>
     * The conditional check is:<br>
     * lookup school with {@code gradMincode} if it exists use the school name
     * otherwise lookup the school with the {@code mincode} and if it exists use
     * that school name.<br>
     *
     * @param student
     */
    private void setSchoolName(final StudentDemographicImpl student) {
        final String _m = "setSchoolName(StudentDemographicImpl)";
        LOG.entering(CLASSNAME, _m);

        TabSchool school;
        String schlName = "";
        final String gradMincode = student.getGradMincode();
        final String mincode = student.getMincode();

        if (!(gradMincode.isEmpty())) {
            school = findSchoolByMincode(gradMincode);
            // FIXME: Remove duplicate code
            if (school != null) {
                final String name = school.getSchlName();
                schlName = trimSafe(name);
                student.setSchool(school);
            }
        }

        if (schlName.isEmpty() && !(mincode.isEmpty())) {
            school = findSchoolByMincode(mincode);
            // FIXME: Remove duplicate code
            if (school != null) {
                final String name = school.getSchlName();
                schlName = trimSafe(name);
                student.setSchool(school);
            }
        }
        student.setSchoolName(schlName);

        LOG.exiting(CLASSNAME, _m);
    }

    /**
     * perform the school lookup by {@code mincode} using the named query
     * TabSchoolEntity.findByMincode.
     *
     * @param mincode
     * @return TabSchoolEntity
     */
    private TabSchoolEntity findSchoolByMincode(final String mincode) {
        final String _m = "findSchoolByMincode(String)";
        LOG.entering(CLASSNAME, _m);

        ReportData reportData = TRAXThreadDataUtility.getGenerateReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    null,
                    "Report Data not exists for the current report generation");
            LOG.throwing(CLASSNAME, _m, dse);
            throw dse;
        }

        TabSchoolEntity school = null;
        try {
            school = gradtoIsdDataConvertBean.getTabSchool(reportData);
        } catch (EntityNotFoundException e) {
            LOG.log(Level.WARNING, "No school record found for mincode {0}", new Object[]{mincode});
        }

        LOG.exiting(CLASSNAME, _m);
        return school;
    }

    private List<? extends StudentDemographic> findDemogByPEN(final String pen, final String hql) {
        final String _m = "findDemogByPEN(String, String)";
        LOG.entering(CLASSNAME, _m);

        ReportData reportData = TRAXThreadDataUtility.getGenerateReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    null,
                    "Report Data not exists for the current report generation");
            LOG.throwing(CLASSNAME, _m, dse);
            throw dse;
        }

        final List<StudentDemographic> students = gradtoIsdDataConvertBean.getStudentDemog(reportData);

        for (final StudentDemographic s : students) {
            StudentDemographicImpl student = (StudentDemographicImpl)s;
            setTranscriptEligibility(student);
            setFormerStudent(student);
            setSchoolCategory(student);
            setSchoolName(student);
        }

        LOG.exiting(CLASSNAME, _m);
        return students;
    }

    @RolesAllowed({TRAX_READ, USER, FULFILLMENT_SERVICES_USER, PUBLIC_USER})
    @Override
    public List<StudentProfileMasterLite> searchStudentPartialMatchAll(final List<String> tokens)
            throws DomainServiceException {
        final String _m = "searchStudentPartialMatchAll(String, final List<String> tokens)";

        ReportData reportData = TRAXThreadDataUtility.getGenerateReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    null,
                    "Report Data not exists for the current report generation");
            LOG.throwing(CLASSNAME, _m, dse);
            throw dse;
        }

        final List<StudentProfileMasterLite> result = gradtoIsdDataConvertBean.getStudentProfileMaster(reportData);

        return result;
    }

    private String buildLogString(final String logString, final Object... objects) {
        return MessageFormat.format(logString, objects);
    }
}
