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

import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.assessment.AssessmentCourseCode;
import ca.bc.gov.educ.isd.eis.common.DomainServiceException;
import ca.bc.gov.educ.isd.eis.trax.db.*;
import ca.bc.gov.educ.isd.traxadaptor.impl.AchievementCourseImpl;
import ca.bc.gov.educ.isd.traxadaptor.impl.TSWRegistryImpl;
import ca.bc.gov.educ.isd.traxadaptor.service.*;
import ca.bc.gov.educ.isd.traxadaptor.utils.ExceptionUtilities;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.common.support.Lists.getFirst;
import static ca.bc.gov.educ.isd.eis.assessment.AssessmentCourseCode.LITERACY_ENGLISH;
import static ca.bc.gov.educ.isd.eis.assessment.AssessmentCourseCode.LITERACY_FRENCH;
import static ca.bc.gov.educ.isd.eis.roles.Roles.*;
import static java.lang.String.format;

/**
 * <p>
 * A stateless session EJB providing access to TRAX legacy data for consumption
 * by other services. The TRAX adaptor is presented as an EJB using the
 * TRAXAdapter interface and exposes methods which can be called to retrieve
 * data for a given PEN. The data value objects returned by the method calls
 * contain the TRAX data associated with the request.
 * </p>
 *
 * @author CGI Information Management Consultants Inc.
 */
@Service
@DeclareRoles({TRAX_READ, USER, FULFILLMENT_SERVICES_USER, PUBLIC_USER})
public class TRAXAdapterServiceImpl implements TRAXAdapter {

    private static final long serialVersionUID = 3L;

    private static final String CLASSNAME = TRAXAdapterServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    /**
     * Exception Message for failed input or pre-condition verification.
     *
     * <p>
     * This constant in consumed by automated unit test code.</p>
     */
    public static final String MSG_VERIFICATION_FAILED_INPUT = "Input and pre-condition verification failed.";

    public static final String TX_PSI_ID = "T04";

    @Autowired
    private ScholarshipData scholarshipDao;
    @Autowired
    private ExamData examDao;
    @Autowired
    private StudentData studentDao;
    @Autowired
    private TranscriptData transcriptDao;
    @Autowired
    private TswPSIChoiceData tswChoicesDao;;
    @Autowired
    private TswStudPSIData tswStudPSIDao;
    @Autowired
    private TswTxPsiData txPsiDao;
    @Autowired
    private TabProvData tabProvDataBean;
    @Autowired
    private AssessmentData assessmentDao;

    private static final Level PERF_LOGGING = Level.FINE;

    /**
     * An externally exposed method to provide transcript related student
     * information from the TRAX database to a requesting service.
     *
     * @param pen Personal Education Number of the student to
     * @return a StudentInfoImpl object with student information for transcripts
     * associated with the given PEN, or NULL if no information is found.
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    @Override
    @RolesAllowed({TRAX_READ, USER, FULFILLMENT_SERVICES_USER})
    public StudentInfo readStudent_Transcript(String pen) throws EISException {
        final String _m = "readStudent_Transcript(String)";
        LOG.entering(CLASSNAME, _m, pen);
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, for PEN {1}.", new Object[]{_m, pen});

        //<editor-fold defaultstate="collapsed" desc="Verify inputs and pre-conditions.">
        {

            final RuntimeException ex;
            ex = verifyPEN(pen);

            throwInvalidPreconditions(ex, LOG, CLASSNAME, _m);

        }
        //</editor-fold>
        LOG.fine("Verified Inputs and Pre-conditions.");

        final StudentInfo retValue;
        List<? extends StudentInfo> students;
        students = this.transcriptDao.findStudentByPEN(pen);
        LOG.log(Level.FINE, "Searched for all student transcript information for the PEN in TRAX {0}.", students.size());

        boolean emptyList = students.isEmpty();
        if (emptyList) {
            retValue = null;
            LOG.finer("Searched for Transcript information for student but found nothing.");
        } else {
            final int numCerts = students.size();
            if (numCerts == 1) {
                retValue = students.get(0);
                LOG.finer("Found Transcript information for the student.");
            } else {
                retValue = null;
                final String msg = "Found multiple students with a Transcript associated with PEN=" + pen;
                LOG.log(Level.WARNING, msg);
                final TRAXStudentRecordException tsre = new TRAXStudentRecordException(pen, msg);
                LOG.throwing(CLASSNAME, _m, tsre);
            }
        }
        LOG.fine("Extracted information for student transcript search results.");

        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, for PEN {1}.", new Object[]{_m, pen});

        LOG.exiting(CLASSNAME, _m, retValue);
        return retValue;
    }

    //TODO move to common utility between business and EIS layers
    private static RuntimeException verifyPEN(String PEN) {
        final String REG_ALL_DIGITS = "[0-9]+";
        RuntimeException ex;
        if (PEN == null) {
            LOG.warning("PEN parameter is null.");
            ex = new NullPointerException("PEN parameter may not be null.");
        } else if (!PEN.matches(REG_ALL_DIGITS)) {
            LOG.warning("PEN parameter does not match the regular expression.");
            //TODO check the data format and definition of a PEN and make sure that a PEN passing thorugh here is really a PEN.
            ex = new IllegalArgumentException("PEN parameter must be one of more digits.");
        } else {
            LOG.log(Level.FINE, "PEN verified {0}.", PEN);
            ex = null;
        }
        return ex;
    }

    /**
     * An externally exposed method to provide course related information for
     * transcripts from the TRAX database to a requesting service.
     *
     * @param pen
     * @return a list of TranscriptCourseImpl objects, or null no information is
     * found.
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    @Override
    @RolesAllowed({TRAX_READ, USER, FULFILLMENT_SERVICES_USER})
    public List<TranscriptCourse> readCourses_Transcript(String pen) throws EISException {
        final String _m = "readCourses_Transcript(String)";
        LOG.entering(CLASSNAME, _m, pen);
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}.", new Object[]{_m, pen});

        //<editor-fold defaultstate="collapsed" desc="Verify inputs and pre-conditions.">
        {
            RuntimeException ex = verifyPEN(pen);

            throwInvalidPreconditions(ex, LOG, CLASSNAME, _m);

        }
        //</editor-fold>
        LOG.fine("Verified Inputs and Pre-conditions.");

        final List<TranscriptCourse> retList;

        List<? extends TranscriptCourse> courses = this.transcriptDao.findCoursesByPEN(pen);
        LOG.fine("Searched for all transcript courses for the PEN in TRAX.");

        if (courses == null || courses.isEmpty()) {
            retList = null;
            LOG.finer("Searched for course information for Transcript but found nothing.");
        } else {
            retList = new ArrayList<>();
            retList.addAll(courses);
        }
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}.", new Object[]{_m, pen});

        LOG.exiting(CLASSNAME, _m);
        return retList;
    }

    /**
     * An externally exposed method to provide course related information for
     * transcripts from the TRAX database to a requesting service.
     *
     * @param pen
     * @return a list of TranscriptCourseImpl objects, or null no information is
     * found.
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    @Override
    @RolesAllowed({TRAX_READ, USER, FULFILLMENT_SERVICES_USER})
    public List<TranscriptCourse> readCourses_InterimTranscript(final String pen) throws EISException {
        final String _m = "readCourses_InterimTranscript(final String pen)";
        LOG.entering(CLASSNAME, _m, pen);
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}.", new Object[]{_m, pen});

        //<editor-fold defaultstate="collapsed" desc="Verify inputs and pre-conditions.">
        {
            RuntimeException ex = verifyPEN(pen);

            throwInvalidPreconditions(ex, LOG, CLASSNAME, _m);

        }
        //</editor-fold>
        LOG.fine("Verified Inputs and Pre-conditions.");

        final List<TranscriptCourse> retList;

        List<? extends TranscriptCourse> courses = this.transcriptDao.findInterimCoursesByPEN(pen);
        LOG.fine("Searched for all transcript courses for the PEN in TRAX.");

        if (courses == null || courses.isEmpty()) {
            retList = null;
            LOG.finer("Searched for course information for Transcript but found nothing.");
        } else {
            retList = new ArrayList<>();
            retList.addAll(courses);
        }
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}.", new Object[]{_m, pen});

        LOG.exiting(CLASSNAME, _m);
        return retList;
    }

    /**
     * An externally exposed method to provide course related information for
     * transcripts from the TRAX database to a requesting service.
     *
     * @param pen
     * @return a list of TranscriptCourseImpl objects, or null no information is
     * found.
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    @Override
    @RolesAllowed({TRAX_READ, USER})
    public Integer countCourses_Transcript(String pen) throws EISException {
        final String _m = "countCourses_Transcript(String)";
        LOG.entering(CLASSNAME, _m, pen);
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}.", new Object[]{_m, pen});

        //<editor-fold defaultstate="collapsed" desc="Verify inputs and pre-conditions.">
        {
            RuntimeException ex = verifyPEN(pen);

            throwInvalidPreconditions(ex, LOG, CLASSNAME, _m);

        }
        //</editor-fold>
        LOG.fine("Verified Inputs and Pre-conditions.");

        Integer count = this.transcriptDao.countCoursesByPEN(pen);

        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}.", new Object[]{_m, pen});
        LOG.exiting(CLASSNAME, _m);
        return count;
    }

    /**
     * An externally exposed method to provide student demographic information
     * from the TRAX database to a requesting service.
     *
     * @param pen
     * @return a StudentDemographicImpl object with student information
     * associated with the given PEN, or NULL if no information is found.
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    @Override
    @RolesAllowed({TRAX_READ, USER, FULFILLMENT_SERVICES_USER, PUBLIC_USER})
    public StudentDemographic readStudent_Demographic(final String pen) throws EISException {
        final String _m = "readStudent_Demographic(String)";
        LOG.entering(CLASSNAME, _m, pen);
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}.", new Object[]{_m, pen});

        //<editor-fold defaultstate="collapsed" desc="Verify inputs and pre-conditions.">
        {
            RuntimeException ex = verifyPEN(pen);

            throwInvalidPreconditions(ex, LOG, CLASSNAME, _m);

        }
        //</editor-fold>
        LOG.fine("Verified Inputs and Pre-conditions.");

        final StudentDemographic retValue;
        final List<? extends StudentDemographic> students = this.studentDao.findDemogByPEN(pen);
        LOG.log(Level.FINE, "Searched for the student demographic information for the PEN in TRAX.");

        if (students.isEmpty()) {
            retValue = null;
            LOG.log(Level.WARNING, "No student found for PEN = {0}", pen);
        } else {
            final int numStudents = students.size();
            if (numStudents == 1) {
                retValue = students.get(0);
                LOG.log(Level.FINE, "Found Demographic information for the student.");
            } else {
                retValue = null;
                final String msg = "Found multiple students with Demographic information associated with PEN=" + pen;
                LOG.log(Level.WARNING, msg);
                final TRAXStudentRecordException tsre = new TRAXStudentRecordException(pen, msg);
                LOG.throwing(CLASSNAME, _m, tsre);
            }
        }
        LOG.fine("Extracted information for student demographic search results.");

        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}.", new Object[]{_m, pen});

        LOG.exiting(CLASSNAME, _m, retValue);
        return retValue;
    }

    /**
     * An externally exposed method to provide student information for
     * examinations from the TRAX database to a requesting service.
     *
     * @param pen
     * @return an ExamStudentImpl object with exam related student information
     * associated with the given PEN, or NULL if no information is found.
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    @Override
    @RolesAllowed({TRAX_READ, USER})
    public ExamStudent readStudent_Exam(final String pen) throws EISException {
        final String _m = "readStudent_Exam(String)";
        LOG.entering(CLASSNAME, _m, pen);

        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}.", new Object[]{_m, pen});

        //<editor-fold defaultstate="collapsed" desc="Verify inputs and pre-conditions.">
        {
            RuntimeException ex = verifyPEN(pen);

            throwInvalidPreconditions(ex, LOG, CLASSNAME, _m);

        }
        //</editor-fold>
        LOG.fine("Verified Inputs and Pre-conditions.");

        final ExamStudent retValue;
        List<? extends ExamStudent> students;
        students = this.examDao.findStudentByPEN(pen);
        LOG.fine("Searched for all Exam Results for the PEN in TRAX.");

        boolean emptyList = students.isEmpty();
        if (emptyList) {
            retValue = null;
            LOG.finer("Searched for Exam information for student but found nothing.");
        } else {
            final int numCerts = students.size();
            if (numCerts == 1) {
                retValue = students.get(0);
                LOG.finer("Found Exam information for the student.");
            } else {
                retValue = null;
                final String msg = "Found multiple students with Exam information associated with PEN=" + pen;
                LOG.log(Level.WARNING, msg);
                final TRAXStudentRecordException tsre = new TRAXStudentRecordException(pen, msg);
                LOG.throwing(CLASSNAME, _m, tsre);
            }
        }
        LOG.fine("Extracted information for student exam search results.");
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}.", new Object[]{_m, pen});

        LOG.exiting(CLASSNAME, _m, retValue);
        return retValue;
    }

    /**
     * An externally exposed method to provide student information for
     * assessments from the TRAX database to a requesting service.
     *
     * @param pen Student's Personal Education Number
     * @param sessionDate The assessment business key
     * @param code The assessment business key (e.g., LTE, NME, NMF).
     * @return A non-null assessment result populated student assessment data
     * for the given business key (pen, session date, and code).
     * @throws ca.bc.gov.educ.isd.eis.EISException Assessment data could not be
     * found for the given parameters.
     */
    @Override
    @RolesAllowed({TRAX_READ, USER})
    public AssessmentResult readStudent_Assessment(
            final String pen,
            final String sessionDate,
            final AssessmentCourseCode code) throws EISException {
        final String _m = "readStudent_Assessment(String)";
        LOG.entering(CLASSNAME, _m, pen);

        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}.", new Object[]{_m, pen});

        //<editor-fold defaultstate="collapsed" desc="Verify inputs and pre-conditions.">
        {
            RuntimeException ex = verifyPEN(pen);

            throwInvalidPreconditions(ex, LOG, CLASSNAME, _m);

        }
        //</editor-fold>
        LOG.fine("Verified Inputs and Pre-conditions.");

        final List<? extends AssessmentResult> assessments;
        
        if (LITERACY_ENGLISH.equals(code) || LITERACY_FRENCH.equals(code)) {
            assessments = this.assessmentDao.findLiteracyAssessments(pen, sessionDate, code);
        } else {
            assessments = this.assessmentDao.findAssessments(pen, sessionDate, code);
        }
        LOG.fine("Searched for all Exam Results for the PEN in TRAX.");

        final AssessmentResult result = getFirst(assessments);

        if (result == null) {
            final String msg = format(
                    "No assessment record found for pen <%s>, session <%s>, and course code <%s>.",
                    pen, sessionDate, code.getCode());
            throw new TRAXStudentRecordException(pen, msg);
        }

        LOG.fine("Extracted information for student assessment search results.");
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}.", new Object[]{_m, pen});

        LOG.exiting(CLASSNAME, _m, result);
        return result;
    }
    
    /**
     * An externally exposed method to provide examination results from the TRAX
     * database to a requesting service.
     *
     * @param pen
     * @return List of ExamResultImpl objects for a given student, or null if no
     * information is found.
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    @Override
    @RolesAllowed({TRAX_READ, USER})
    public List<ExamResult> readCourses_Exam(String pen) throws EISException {
        final String _m = "readCourses_Exam(String)";
        LOG.entering(CLASSNAME, _m, pen);
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}.", new Object[]{_m, pen});

        //<editor-fold defaultstate="collapsed" desc="Verify inputs and pre-conditions.">
        {
            RuntimeException ex = verifyPEN(pen);

            throwInvalidPreconditions(ex, LOG, CLASSNAME, _m);

        }
        //</editor-fold>
        LOG.fine("Verified Inputs and Pre-conditions.");

        final List<ExamResult> retList;

        List<? extends ExamResult> results;
        results = this.examDao.findResultsByPEN(pen);
        LOG.fine("Searched for all Exam Results for the PEN in TRAX.");

        if (results == null || results.isEmpty()) {
            retList = null;
            LOG.finer("Searched for course information for Exams but found nothing.");
        } else {
            retList = new ArrayList<>();
            retList.addAll(results);
        }

        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}.", new Object[]{_m, pen});
        LOG.exiting(CLASSNAME, _m);
        return retList;
    }

    /**
     * An externally exposed method to provide scholarship information from the
     * TRAX database to a requesting service.
     *
     * @param PEN
     * @return List of ScholarshipImpl objects for a given student, or an empty
     * list if no information is found.
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    @Override
    @RolesAllowed({TRAX_READ, USER})
    public List<Scholarship> readScholarships(String PEN) throws EISException {
        final String _m = "readScholarships(String)";
        LOG.entering(CLASSNAME, _m);
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}.", new Object[]{_m, PEN});

        //<editor-fold defaultstate="collapsed" desc="Verify inputs and pre-conditions.">
        {
            RuntimeException ex = verifyPEN(PEN);

            throwInvalidPreconditions(ex, LOG, CLASSNAME, _m);

        }
        //</editor-fold>
        LOG.fine("Verified Inputs and Pre-conditions.");

        final List<Scholarship> retList;

        List<? extends Scholarship> scholarships;
        scholarships = this.scholarshipDao.findScholarshipsByPEN(PEN);
        LOG.fine("Searched for all Scholarships for the PEN in TRAX.");

        if (scholarships.isEmpty()) {
            retList = null;
            LOG.finer("Searched for scholarships but found nothing.");
        } else {
            retList = new ArrayList<>();
            retList.addAll(scholarships);
        }

        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}.", new Object[]{_m, PEN});
        LOG.exiting(CLASSNAME, _m);
        return retList;
    }

    /**
     * An externally exposed method to provide student information associated
     * with scholarships from the TRAX database to a requesting service.
     *
     * @param pen
     * @return ScholarshipStudentImpl object for a given student, or NULL if no
     * information is found.
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    @Override
    @RolesAllowed({TRAX_READ, USER})
    public ScholarshipStudent readStudent_Scholarship(final String pen) throws EISException {
        final String _m = "readStudent_Scholarship";
        LOG.entering(CLASSNAME, _m, pen);
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}.", new Object[]{_m, pen});

        //<editor-fold defaultstate="collapsed" desc="Verify inputs and pre-conditions.">
        {
            RuntimeException ex = verifyPEN(pen);

            throwInvalidPreconditions(ex, LOG, CLASSNAME, _m);

        }
        //</editor-fold>
        LOG.fine("Verified Inputs and Pre-conditions.");

        final ScholarshipStudent retValue;
        List<? extends ScholarshipStudent> students;
        students = this.scholarshipDao.findStudentByPEN(pen);
        LOG.fine("Searched for all Scholarships for the PEN in TRAX.");

        boolean emptyList = students.isEmpty();
        if (emptyList) {
            retValue = null;
            LOG.finer("Searched for student information for Scholarships but found nothing.");
        } else {
            final int numCerts = students.size();
            if (numCerts == 1) {
                retValue = students.get(0);
                LOG.finer("Found a Scholarship for the student.");
            } else {
                retValue = null;
                final String msg = "Found multiple students with Scholarships associated with PEN=" + pen;
                LOG.log(Level.WARNING, msg);
                final TRAXStudentRecordException tsre = new TRAXStudentRecordException(pen, msg);
                LOG.throwing(CLASSNAME, _m, tsre);
            }
        }
        LOG.fine("Extracted student information for Scholarships search results.");

        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}.", new Object[]{_m, pen});
        LOG.exiting(CLASSNAME, _m, retValue);
        return retValue;
    }

    @Override
    @RolesAllowed({TRAX_READ, USER})
    public List<PSIChoice> readStudentChoices(final String PEN) throws EISException {
        final String _m = "readStudent_Choices_TRAX(String)";
        LOG.entering(CLASSNAME, _m, PEN);
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}.", new Object[]{_m, PEN});

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (PEN == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PEN parameter should not be null.");
            } else if (PEN.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PEN parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        final List<PSIChoice> retList;

        List<? extends PSIChoice> tswChoices = tswChoicesDao.findChoiceBy(PEN);
        LOG.log(Level.FINE, "Searched for all Student Choices for the PEN {0} in TRAX.", PEN);

        if (tswChoices.isEmpty()) {
            retList = null;
            LOG.finer("Searched for Student Choices but found nothing.");
        } else {
            retList = new ArrayList<>();
            retList.addAll(tswChoices);
        }

        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}.", new Object[]{_m, PEN});
        LOG.exiting(CLASSNAME, _m);
        return retList;
    }

    @Override
    @RolesAllowed({TRAX_READ, USER})
    public List<PSIChoice> readStudentChoices(final String PEN, final String psiCode, final String psiYear) throws EISException {
        final String _m = "readStudent_Choices_TRAX(String, String, String)";
        LOG.entering(CLASSNAME, _m, new Object[]{PEN, psiCode, psiYear});
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}, {2}, {3}.", new Object[]{_m, PEN, psiCode, psiYear});

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (PEN == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PEN parameter should not be null.");
            } else if (PEN.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PEN parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (psiYear == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Year parameter should not be null.");
            } else if (psiYear.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Year parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        final List<PSIChoice> retList;

        List<? extends PSIChoice> tswChoices = tswChoicesDao.findChoiceBy(PEN, psiCode, psiYear);
        LOG.log(Level.FINE, "Searched for all Student Choices for the PEN {0}, PSI code {1}, and Year {2} in TRAX.", new Object[]{PEN, psiCode, psiYear});

        if (tswChoices.isEmpty()) {
            retList = null;
            LOG.finer("Searched for Student Choices but found nothing.");
        } else {
            retList = new ArrayList<>();
            retList.addAll(tswChoices);
        }

        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}, {2}, {3}.", new Object[]{_m, PEN, psiCode, psiYear});
        LOG.exiting(CLASSNAME, _m);
        return retList;
    }

    @Override
    @RolesAllowed(TRAX_READ)
    public List<TSWTxPSI> readTxPSI(final String PEN) throws EISException {
        final String _m = "readTx_PSI_TRAX(String)";
        LOG.entering(CLASSNAME, _m, PEN);
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}.", new Object[]{_m, PEN});

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (PEN == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PEN parameter should not be null.");
            } else if (PEN.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PEN parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        final List<TSWTxPSI> retList;

        List<? extends TSWTxPSI> txPSI = txPsiDao.findTxPSIBy(PEN);
        LOG.log(Level.FINE, "Searched for all Tx PSI for the PEN {0} in TRAX.", PEN);

        if (txPSI.isEmpty()) {
            retList = null;
            LOG.finer("Searched for Tx PSI but found nothing.");
        } else {
            retList = new ArrayList<>();
            retList.addAll(txPSI);
        }

        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}.", new Object[]{_m, PEN});
        LOG.exiting(CLASSNAME, _m);
        return retList;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TSWTxPSI> readTxPSI(final String PEN, final String psiCode) throws EISException {
        return readTxPSI(PEN, TX_PSI_ID, psiCode);
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TSWTxPSI> readTxPSI(final String PEN, final String psiCode, final Date processDate) throws EISException {
        return readTxPSI(PEN, TX_PSI_ID, psiCode, processDate);
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TSWTxPSI> readTxPSI(final String PEN, final String txId, final String psiCode) throws EISException {
        final String _m = "readTx_PSI_TRAX(String,String,String)";
        LOG.entering(CLASSNAME, _m, new Object[]{PEN, txId, psiCode});
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}, {2}, {3}.", new Object[]{_m, PEN, txId, psiCode});

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (PEN == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PEN parameter should not be null.");
            } else if (PEN.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PEN parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (txId == null) {
                ex = ExceptionUtilities.setupRuntimeException("The TX PSI parameter should not be null.");
            } else if (txId.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The TX PSI parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        final List<TSWTxPSI> retList;

        List<? extends TSWTxPSI> txPSI = txPsiDao.findTxPSIBy(txId, PEN, psiCode);
        LOG.log(Level.FINE, "Searched for all Tx PSI for the PEN {0}, PSI code {1}, and Tx Id {2} in TRAX.", new Object[]{PEN, psiCode, txId});

        if (txPSI.isEmpty()) {
            retList = null;
            LOG.finer("Searched for Tx PSI but found nothing.");
        } else {
            retList = new ArrayList<>();
            retList.addAll(txPSI);
        }

        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}, {2}, {3}.", new Object[]{_m, PEN, txId, psiCode});
        LOG.exiting(CLASSNAME, _m);
        return retList;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TSWTxPSI> readTxPSI(final String PEN, final String txId, final String psiCode, final Date processDate) throws EISException {
        final String _m = "readTx_PSI_TRAX(String,String,String)";
        LOG.entering(CLASSNAME, _m, new Object[]{PEN, txId, psiCode});
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}, {2}, {3}.", new Object[]{_m, PEN, txId, psiCode});

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (PEN == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PEN parameter should not be null.");
            } else if (PEN.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PEN parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (txId == null) {
                ex = ExceptionUtilities.setupRuntimeException("The TX PSI parameter should not be null.");
            } else if (txId.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The TX PSI parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        final List<TSWTxPSI> retList;

        List<? extends TSWTxPSI> txPSI = txPsiDao.findTxPSIBy(txId, PEN, psiCode, processDate);
        LOG.log(Level.FINE, "Searched for all Tx PSI for the PEN {0}, PSI code {1}, Tx Id {2} and {3} in TRAX.", new Object[]{PEN, psiCode, txId, processDate});

        if (txPSI.isEmpty()) {
            retList = new ArrayList<>();
            LOG.fine("Searched for Tx PSI but found nothing.");
        } else {
            retList = new ArrayList<>();
            retList.addAll(txPSI);
        }

        LOG.log(PERF_LOGGING, "{0} TSW TX PSI where found for pen= {1}, txId ={2}, psiCode={3} and processDate = {4}.", new Object[]{txPSI.size(), PEN, txId, psiCode, processDate});
        LOG.exiting(CLASSNAME, _m);
        return retList;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public void updateTxPSI(final String txId, final String studNo, final String psiCode, final Character status) throws EISException {
        final String _m = "updateTxPSI(String,String,String,String)";
        LOG.entering(CLASSNAME, _m, new Object[]{txId, studNo, psiCode, status});
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}, {2}, {3}, {4}.", new Object[]{_m, studNo, txId, psiCode, status});

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (txId == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (txId.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be empty.");
            } else if (status == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Status parameter should not be null.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        try {
            txPsiDao.updateTxPSIStatus(txId, studNo, psiCode, status);
            LOG.log(Level.FINE, "Row identfied with txId = {0}, studNo = {1} and psiCode = {2} was updated", new Object[]{txId, studNo, psiCode});
        } catch (EISException eisex) {
            LOG.throwing(CLASSNAME, _m, eisex);
            throw eisex;
        }
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}, {2}, {3}, {4}.", new Object[]{_m, studNo, txId, psiCode, status});
        LOG.exiting(CLASSNAME, _m);
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public void insertTxPSI(final String txId, final String studNo, final String psiCode, final Character status) throws EISException {
        final String _m = "insertTxPSI(String,String,String,String)";
        LOG.entering(CLASSNAME, _m, new Object[]{txId, studNo, psiCode, status});
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}, {2}, {3}, {4}.", new Object[]{_m, studNo, txId, psiCode, status});

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (txId == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (txId.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be empty.");
            } else if (status == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Status parameter should not be null.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        try {
            txPsiDao.insertNewTxPSI(txId, studNo, psiCode, status);
            LOG.log(Level.FINE, "Row identfied with txId = {0}, studNo = {1} and psiCode = {2} was inserted", new Object[]{txId, studNo, psiCode});
        } catch (EISException eisex) {
            LOG.throwing(CLASSNAME, _m, eisex);
            throw eisex;
        }
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}, {2}, {3}, {4}.", new Object[]{_m, studNo, txId, psiCode, status});
        LOG.exiting(CLASSNAME, _m);
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public void deleteTxPSI(final String txId, final String studNo, final String psiCode) throws EISException {
        final String _m = "insertTxPSI(String,String,String,String)";
        LOG.entering(CLASSNAME, _m, new Object[]{txId, studNo, psiCode});
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}, {2}, {3}.", new Object[]{_m, studNo, txId, psiCode});

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (txId == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (txId.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        try {
            txPsiDao.deleteTxPSI(txId, studNo, psiCode);
            LOG.log(Level.FINE, "Row identfied with txId = {0}, studNo = {1} and psiCode = {2} was deleted", new Object[]{txId, studNo, psiCode});
        } catch (EISException eisex) {
            LOG.throwing(CLASSNAME, _m, eisex);
            throw eisex;
        }
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}, {2}, {3}.", new Object[]{_m, studNo, txId, psiCode});

        LOG.exiting(CLASSNAME, _m);
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public void mergeTxPSI(final String studNo, final String psiCode, final Character status) throws EISException {
        final String _m = "mergeTxPSI(String,String,String)";

        mergeTxPSI(TX_PSI_ID, studNo, psiCode, status);

        LOG.entering(CLASSNAME, _m);
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public void mergeTxPSI(final String txId, final String studNo, final String psiCode, final Character status) throws EISException {
        final String _m = "mergeTxPSI(String,String,String,String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (txId == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (txId.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be empty.");
            } else if (status == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Status parameter should not be null.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        boolean isUpdated = txPsiDao.updateTxPSIStatus(txId, studNo, psiCode, status);
        if (!isUpdated) {
            txPsiDao.insertNewTxPSI(txId, studNo, psiCode, status);
        }
        LOG.exiting(CLASSNAME, _m);
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<? extends TSWStud> searchTswStudPSI(final String studNo, final String psiCode, final String psiYear) throws EISException {
        final String _m = "deleteTxPSI(String, String, String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (psiYear == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be null.");
            } else if (psiYear.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        List<? extends TSWStud> response = tswStudPSIDao.findChoiceBy(studNo, psiCode, psiYear);

        LOG.exiting(CLASSNAME, _m);
        return response;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public void mergeTswStudPSI(final String studNo, final String psiCode, final String psiYear, final Character status) throws EISException {
        final String _m = "updateTxPSIStatus(String, String, String, Character)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (psiYear == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be null.");
            } else if (psiYear.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be empty.");
            } else if (status == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Status parameter should not be null.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        boolean isUpdated = tswStudPSIDao.updateChoiceStatus(studNo, psiCode, psiYear, status);
        if (!isUpdated) {
            tswStudPSIDao.insertNewChoice(studNo, psiCode, psiYear, status);
        }
        LOG.exiting(CLASSNAME, _m);
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public void insertTswStudPSI(final String studNo, final String psiCode, final String psiYear, final Character status) throws EISException {
        final String _m = "insertNewTxPSI(String, String, String, Character)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (psiYear == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be null.");
            } else if (psiYear.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        tswStudPSIDao.insertNewChoice(studNo, psiCode, psiYear, status);

        LOG.exiting(CLASSNAME, _m);
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public void updateTswStudPSIStatus(final String studNo, final String psiCode, final String psiYear, final Character status) throws EISException {
        final String _m = "updateTxPSIStatus(String, String, String, Character)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (psiYear == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be null.");
            } else if (psiYear.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be empty.");
            } else if (status == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Status parameter should not be null.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        tswStudPSIDao.updateChoiceStatus(studNo, psiCode, psiYear, status);
        LOG.exiting(CLASSNAME, _m);
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public void deleteTswStudPSI(final String studNo, final String psiCode, final String psiYear, final Character status) throws EISException {
        final String _m = "deleteTxPSI(String, String, String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (psiYear == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be null.");
            } else if (psiYear.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        tswStudPSIDao.deleteChoice(studNo, psiCode, psiYear);

        LOG.exiting(CLASSNAME, _m);
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public TSWRegistry createTswRegistry(String psiCode) throws EISException {
        final String _m = "createTswRegistry()";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        TSWRegistry response = new TSWRegistryImpl(psiCode);

        LOG.exiting(CLASSNAME, _m);
        return response;
    }

    //TODO move to utility class
    /**
     * Utility to throw exception when parameters on pre-conditions are invalid.
     *
     * <p>
     * <b>Implementation Note:</b> This method does not contain logging or
     * constraint checks.
     *
     * @param causeEx The causal exception, if this value is null, then a Domain
     * service exception is not thrown.
     * @param logger Logger of the caller.
     * @param className Class Name of the caller (used in conjunction with the
     * Logger)
     * @param _m Calling Method Name (used in conjunction with the Logger)
     * @throws EISException if the cause exception is not null.
     */
    private static void throwInvalidPreconditions(Throwable causeEx, Logger logger, String className, String _m) throws EISException {
        if (causeEx != null) {
            logger.log(Level.WARNING, "Input and pre-condition verification failed for {0}: {1}", new Object[]{className, _m});

            final EISException des = new EISException(MSG_VERIFICATION_FAILED_INPUT, causeEx);
            logger.throwing(className, _m, des);

            throw des;
        }
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TabProvince> readAllRegions() throws EISException {
        return (List<TabProvince>) tabProvDataBean.findAll();
    }

    @Override
    public StudentDemographic findStudentDemographic(final StudentDemographic student)
            throws EISException {
        throw new UnsupportedOperationException();
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<? extends StudentProfileMasterLite> searchStudentPartialMatchAll(final List<String> tokens)
            throws DomainServiceException {
        final List<? extends StudentProfileMasterLite> result = studentDao.searchStudentPartialMatchAll(tokens);
        return result;
    }

    @Override
    public StudentInfo readStudent_Achievement(String pen) throws EISException {
        final String _m = "readStudent_Achievement(String)";
        LOG.entering(CLASSNAME, _m, pen);
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, for PEN {1}.", new Object[]{_m, pen});

        //<editor-fold defaultstate="collapsed" desc="Verify inputs and pre-conditions.">
        {

            final RuntimeException ex;
            ex = verifyPEN(pen);

            throwInvalidPreconditions(ex, LOG, CLASSNAME, _m);

        }
        //</editor-fold>
        LOG.fine("Verified Inputs and Pre-conditions.");

        final StudentInfo retValue;
        List<? extends StudentInfo> students;
        students = this.transcriptDao.findStudentByPEN(pen);
        LOG.log(Level.FINE, "Searched for all student transcript information for the PEN in TRAX {0}.", students.size());

        boolean emptyList = students.isEmpty();
        if (emptyList) {
            retValue = null;
            LOG.finer("Searched for Transcript information for student but found nothing.");
        } else {
            final int numCerts = students.size();
            if (numCerts == 1) {
                retValue = students.get(0);
                LOG.finer("Found Transcript information for the student.");
            } else {
                retValue = null;
                final String msg = "Found multiple students with a Transcript associated with PEN=" + pen;
                LOG.log(Level.WARNING, msg);
                final TRAXStudentRecordException tsre = new TRAXStudentRecordException(pen, msg);
                LOG.throwing(CLASSNAME, _m, tsre);
            }
        }
        LOG.fine("Extracted information for student transcript search results.");

        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, for PEN {1}.", new Object[]{_m, pen});

        LOG.exiting(CLASSNAME, _m, retValue);
        return retValue;
    }

    @Override
    public Integer countCourses_Achievement(String pen) throws EISException {
        final String _m = "countCourses_Achievement(String)";
        LOG.entering(CLASSNAME, _m, pen);
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}.", new Object[]{_m, pen});

        //<editor-fold defaultstate="collapsed" desc="Verify inputs and pre-conditions.">
        {
            RuntimeException ex = verifyPEN(pen);

            throwInvalidPreconditions(ex, LOG, CLASSNAME, _m);

        }
        //</editor-fold>
        LOG.fine("Verified Inputs and Pre-conditions.");

        Integer count = this.transcriptDao.countCoursesByPEN(pen);

        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}.", new Object[]{_m, pen});
        LOG.exiting(CLASSNAME, _m);
        return count;
    }

    @Override
    public List<AchievementCourse> readCourses_InterimAchievement(String pen) throws EISException {
        final String _m = "readCourses_InterimAchievement(final String pen)";
        LOG.entering(CLASSNAME, _m, pen);
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}.", new Object[]{_m, pen});

        //<editor-fold defaultstate="collapsed" desc="Verify inputs and pre-conditions.">
        {
            RuntimeException ex = verifyPEN(pen);

            throwInvalidPreconditions(ex, LOG, CLASSNAME, _m);

        }
        //</editor-fold>
        LOG.fine("Verified Inputs and Pre-conditions.");

        final List<AchievementCourse> retList;

        List<? extends TranscriptCourse> courses = this.transcriptDao.findInterimCoursesByPEN(pen);
        LOG.fine("Searched for all transcript courses for the PEN in TRAX.");

        if (courses == null || courses.isEmpty()) {
            retList = null;
            LOG.finer("Searched for course information for Transcript but found nothing.");
        } else {
            retList = new ArrayList<>();
            for(TranscriptCourse course: courses) {
                AchievementCourseImpl c = new AchievementCourseImpl();
                BeanUtils.copyProperties(course, c);
                retList.add(c);
            }
        }
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}.", new Object[]{_m, pen});

        LOG.exiting(CLASSNAME, _m);
        return retList;
    }

    @Override
    public List<AchievementCourse> readCourses_Achievement(String pen) throws EISException {
        final String _m = "readCourses_Transcript(String)";
        LOG.entering(CLASSNAME, _m, pen);
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} start, with pen {1}.", new Object[]{_m, pen});

        //<editor-fold defaultstate="collapsed" desc="Verify inputs and pre-conditions.">
        {
            RuntimeException ex = verifyPEN(pen);

            throwInvalidPreconditions(ex, LOG, CLASSNAME, _m);

        }
        //</editor-fold>
        LOG.fine("Verified Inputs and Pre-conditions.");

        final List<AchievementCourse> retList;

        List<? extends TranscriptCourse> courses = this.transcriptDao.findCoursesByPEN(pen);
        LOG.fine("Searched for all transcript courses for the PEN in TRAX.");

        if (courses == null || courses.isEmpty()) {
            retList = null;
            LOG.finer("Searched for course information for Transcript but found nothing.");
        } else {
            retList = new ArrayList<>();
            for(TranscriptCourse course: courses) {
                AchievementCourseImpl c = new AchievementCourseImpl();
                BeanUtils.copyProperties(course, c);
                retList.add(c);
            }
        }
        LOG.log(PERF_LOGGING, "In TRAXAdapter {0} done, with pen {1}.", new Object[]{_m, pen});

        LOG.exiting(CLASSNAME, _m);
        return retList;
    }
}
