/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
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

import ca.bc.gov.educ.isd.eis.trax.db.ExamResult;
import ca.bc.gov.educ.isd.eis.trax.db.ExamStudent;
import ca.bc.gov.educ.isd.traxadaptor.impl.ExamResultImpl;
import ca.bc.gov.educ.isd.traxadaptor.impl.ExamStudentImpl;
import ca.bc.gov.educ.isd.traxadaptor.service.ExamData;
import org.springframework.stereotype.Repository;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.eis.roles.Roles.TRAX_READ;

/**
 * This is an intermediate layer between the database entities and the unmanaged
 * data objects which are returned to the requesting services. It is a stateless
 * bean providing access to the container managed persistence. These methods
 * provide exam information for the exam results service.
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
@DeclareRoles({TRAX_READ})
public class ExamDataBean implements ExamData {

    private static final long serialVersionUID = 1L;

    private static final String CLASSNAME = ExamDataBean.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final String QUERY_FIND_STUD_BY_PEN
            = "SELECT"
            + "  new " + ExamStudentImpl.class.getCanonicalName() + "("
            + "  a.studNo, a.firstName, a.lastName, b.middleName, a.birthdate,"
            + " a.updateDt, b.logoType, b.mincode, c.schlName) "
            + "FROM"
            + "  TswMailerDemogEntity a, TswTranDemogEntity b, TabSchoolEntity c "
            + "WHERE"
            + "  a.studNo = ?1 and a.studNo = b.studNo and b.mincode = c.mincode";

    private static final String QUERY_FIND_COURSES_BY_PEN
            = "SELECT"
            + "  new " + ExamResultImpl.class.getCanonicalName() + "("
            + "  r.primaryKey.studNo, r.primaryKey.crseCode,"
            + "  r.primaryKey.crseLevel, r.courseName, r.primaryKey.crseSession,"
            + "  r.schoolPct, r.bestSchoolPct, r.examPct, r.bestExamPct,"
            + "  r.finalPct, r.finalLg) "
            + "FROM"
            + "  TswMailerExamEntity r "
            + "WHERE"
            + "  r.primaryKey.studNo = ?1 "
            + "ORDER BY"
            + " r.primaryKey.crseSession DESC, r.courseName";

    @Override
    @RolesAllowed(TRAX_READ)
    public List<? extends ExamStudent> findStudentByPEN(String PEN) {
        final String methodName = "findStudentByPEN(String)";
        LOG.entering(CLASSNAME, methodName);

        final List<ExamStudentImpl> studentList = null;

        LOG.exiting(CLASSNAME, methodName);
        return studentList;
    }

    @Override
    @RolesAllowed(TRAX_READ)
    public List<? extends ExamResult> findResultsByPEN(String PEN) {
        final String methodName = "findResultsByPEN(String)";
        LOG.entering(CLASSNAME, methodName);

        final List<ExamResultImpl> resultsList = null;

        LOG.exiting(CLASSNAME, methodName);
        return resultsList;
    }

}
