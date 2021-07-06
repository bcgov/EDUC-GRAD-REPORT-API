/*
 * *********************************************************************
 *  Copyright (c) 2018, Ministry of Education, BC.
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
package ca.bc.gov.educ.isd.assessment.impl;

import ca.bc.gov.educ.isd.assessment.*;
import ca.bc.gov.educ.isd.common.BusinessReport;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.support.report.BusinessReportEntity;
import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.trax.db.LitAssessmentResult;
import ca.bc.gov.educ.isd.eis.trax.db.StudentInfo;
import ca.bc.gov.educ.isd.eis.trax.db.TRAXAdapter;
import ca.bc.gov.educ.isd.reports.ReportDocument;
import ca.bc.gov.educ.isd.reports.ReportFormat;
import ca.bc.gov.educ.isd.reports.ReportService;
import ca.bc.gov.educ.isd.school.School;
import ca.bc.gov.educ.isd.student.PersonalEducationNumber;
import ca.bc.gov.educ.isd.student.Student;
import ca.bc.gov.educ.isd.student.StudentXRef;
import ca.bc.gov.educ.isd.student.StudentXRefService;
import ca.bc.gov.educ.isd.student.impl.CanadianPostalAddressImpl;
import ca.bc.gov.educ.isd.student.impl.SchoolImpl;
import ca.bc.gov.educ.isd.student.impl.StudentImpl;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.assessment.AssessmentCode.fromValue;
import static ca.bc.gov.educ.isd.assessment.RawScoreCategory.*;
import static ca.bc.gov.educ.isd.common.support.impl.Roles.USER;
import static ca.bc.gov.educ.isd.transcript.impl.constants.Roles.STUDENT_EXAM_REPORT;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@DeclareRoles({STUDENT_EXAM_REPORT, USER})
public class LiteracyAssessmentServiceImpl implements LiteracyAssessmentService {

    private static final long serialVersionUID = 1L;

    private static final String CLASSNAME = LiteracyAssessmentServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private TRAXAdapter traxAdapter;

    private ReportService reportService;

    private StudentXRefService studentXRefService;

    /**
     * Builds a detailed assessment report for the current student.
     *
     * @param format Media type (HTML, PDF)
     * @param courseCode
     * @return A report instance for the current student.
     * @throws DomainServiceException
     */
    @Override
    @RolesAllowed({STUDENT_EXAM_REPORT, USER})
    public BusinessReport buildReport(
            final String sessionDate,
            final AssessmentCourseCode courseCode,
            final ReportFormat format) throws DomainServiceException {
        final String _m = "buildReport(ReportFormat, AssessmentCourseCode, ReportFormat)";
        LOG.entering(CLASSNAME, _m);

        final BusinessReport result;

        try {
            final TRAXAdapter trax = getTRAXService();
            final PersonalEducationNumber pen = getStudentPEN();
            final String penId = pen.getValue();
            final StudentInfo studentInfo = trax.readStudent_Transcript(penId);
            final Student student = adapt(pen, studentInfo);
            final String logoCode = studentInfo.getLogo();

            final LitAssessmentResult assessmentResult = getAssessmentResult(
                    penId,
                    sessionDate,
                    courseCode);
            final LiteracyAssessmentResult literacyAssessmentResult = adapt(assessmentResult);

            final School school = adapt(studentInfo);

            final ReportService service = getReportService();
            final LiteracyAssessmentReport report = service.createLiteracyAssessmentReport();

            if (isFrench(courseCode))  { report.setLocale(Locale.CANADA_FRENCH); }
            report.setStudent(student);
            report.setSchool(school, logoCode);
            report.setLiteracyAssessmentResult(literacyAssessmentResult);
            report.setFormat(format);

            final ReportDocument export = service.export(report);
            result = adapt(report, export);
        } catch (final DomainServiceException | EISException | IllegalArgumentException | IOException ex) {
            throw new DomainServiceException("Could not build student report", ex);
        }

        LOG.exiting(CLASSNAME, _m);
        return result;
    }

    /**
     * Changes the LiteracyAssessmentReport interface to a business report
     * interface for exporting to the end user (in either HTML or PDF form).
     *
     * @param report The data used to fill the report.
     * @param export The filled report.
     * @return A non-null instance.
     */
    private BusinessReport adapt(
            final LiteracyAssessmentReport report,
            final ReportDocument export) {
        final BusinessReport result = new BusinessReportEntity(
                export.asBytes(),
                report.getFormat(),
                report.getFilename(),
                "GLA"
        );

        return result;
    }

    /**
     * Creates an instance of a literacy assessment result that can be
     * associated with a student instance. This result is passed into the report
     * via the student instance.
     *
     * @param ar The flattened assessment results to adapt to a class hierarchy.
     * @return A non-null instance, possibly having no results.
     */
    private LiteracyAssessmentResult adapt(final LitAssessmentResult ar) {
        final Integer proficiencyScore = ar.getAssessmentProficiencyScore();
        final String sessionDate = ar.getAssessmentSession();

        final List<RawScore> rawScores = adaptRawScores(ar);

        final LiteracyAssessmentResultImpl lar = new LiteracyAssessmentResultImpl(
                proficiencyScore,
                sessionDate,
                rawScores);

        return lar;
    }

    /**
     * Adapts the flattened assessment result interface to a class hierarchy.
     *
     * @param ar The flattened assessment results.
     * @return A list, not empty, with two items, representing the online and
     * written scores.
     */
    private List<RawScore> adaptRawScores(final LitAssessmentResult ar) {
        final List<RawScore> result = new ArrayList<>();
        
        boolean isFrench = isFrench(ar.getAssessmentCode());

        RawScore responses = adaptTaskResponses(
                isFrench ? TASK_FR : TASK,
                null,
                null,
                ar.getAssessmentByTaskNames(),
                ar.getAssessmentByTaskValues(),
                ar.getAssessmentByTaskTotals());
        result.add(responses);
        
        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        List<Integer> totals = new ArrayList<>();
        
        names.addAll(ar.getAssessmentByPartANames());
        values.addAll(ar.getAssessmentByPartAValues());
        totals.addAll(ar.getAssessmentByPartATotals());
        
        names.addAll(ar.getAssessmentByPartBNames());
        values.addAll(ar.getAssessmentByPartBValues());
        totals.addAll(ar.getAssessmentByPartBTotals());
        
        if(isFrench) {
            names.addAll(ar.getAssessmentByPartConceptsNames());
            values.addAll(ar.getAssessmentByPartConceptsValues());
            totals.addAll(ar.getAssessmentByPartConceptsTotals());
        }
        
        responses = adaptTaskResponses(
                isFrench ? PART_FR : PART,
                null,
                null,
                names,
                values,
                totals);
        result.add(responses);

        return result;
    }
    
    /**
     * Adapts the flat raw score categories to a class hierarchy, which
     * eliminates duplication.
     *
     * @param category The response type: written or online.
     * @param totalScore Total possible score for category.
     * @param studentTotalScore Student score within the category.
     * @param responseNames Task name.
     * @param responseValues Student score per task.
     * @param responseTotals Total possible score per task.
     * @return A raw score instance composed with the assessment scores.
     */
    private RawScore adaptTaskResponses(
            final RawScoreCategory category,
            final Integer totalScore,
            final Integer studentTotalScore,
            final List<String> responseNames,
            final List<Integer> responseValues,
            final List<Integer> responseTotals) {
        final RawScoreImpl result = new RawScoreImpl();
        result.setRawScoreCategory(category);
        result.setTotalScore(totalScore);
        result.setTotalStudentScore(studentTotalScore);

        final List<AssessmentScore> extendedScores = adaptAssessmentScores(
                responseNames,
                responseValues,
                responseTotals);
        result.setAssessmentScores(extendedScores);

        return result;
    }

    /**
     * Adapts the flat assessment names, scores, and total scores to a
     * report-friendly class hierarchy.
     *
     * @param names The assessment task names.
     * @param studentScores How the student fared per task.
     * @param totalScores The total possible score per task.
     * @return A list of assessment scores mapped to the given parameters,
     * possibly empty, never null.
     */
    private List<AssessmentScore> adaptAssessmentScores(
            final List<String> names,
            final List<Integer> studentScores,
            final List<Integer> totalScores) {
        final int size = names.size();
        final List<AssessmentScore> result = new ArrayList<>(size);

        final Iterator<String> iNames = names.iterator();
        final Iterator<Integer> iStudentScores = studentScores.iterator();
        final Iterator<Integer> iTotalScores = totalScores.iterator();

        while (iNames.hasNext()) {
            final String name = iNames.next();

            if (iStudentScores.hasNext() && iTotalScores.hasNext()) {
                final Integer studentScore = iStudentScores.next();
                final Integer maxScore = iTotalScores.next();
                final AssessmentCode code = fromValue(name);

                final AssessmentScoreImpl score = new AssessmentScoreImpl();
                score.setAssessmentCode(code);
                score.setStudentScore(studentScore);
                score.setMaximumScore(maxScore);

                result.add(score);
            }
        }

        return result;
    }

    /**
     * Curries student information from student info to a StudentImpl instance.
     *
     * @param pen The unique student number.
     * @param traxStudentInfo Student details from TRAX database.
     * @return A StudentImpl instance, never null.
     */
    private Student adapt(
            final PersonalEducationNumber pen,
            final StudentInfo traxStudentInfo) {
        final StudentImpl student = new StudentImpl();

        student.setPen(pen);
        student.setFirstName(traxStudentInfo.getFirstName());
        student.setMiddleName(traxStudentInfo.getMiddleName());
        student.setLastName(traxStudentInfo.getLastName());
        student.setBirthdate(traxStudentInfo.getBirthDate());

        return student;
    }

    /**
     * TODO: Refactor this code into a common service.
     *
     * @param traxStudent The student details containing school address
     * information.
     * @return
     */
    private School adapt(final StudentInfo traxStudent) {
        final SchoolImpl school = new SchoolImpl();
        school.setMincode(traxStudent.getMincode());
        school.setName(traxStudent.getSchoolName());
        school.setTypeIndicator(traxStudent.getSchoolTypeIndicator());
        school.setTypeBanner(traxStudent.getSchoolTypeBanner());

        final CanadianPostalAddressImpl address = new CanadianPostalAddressImpl();
        address.setStreet1(traxStudent.getSchoolStreet());
        address.setStreet2(traxStudent.getSchoolStreet2());
        address.setCity(traxStudent.getSchoolCity());
        address.setPostalCode(traxStudent.getSchoolPostalCode());
        address.setProvince(traxStudent.getSchoolProv());
        school.setAddress(address);

        return school;
    }

    private LitAssessmentResult getAssessmentResult(
            final String pen,
            final String sessionDate,
            final AssessmentCourseCode courseCode)
            throws DomainServiceException, EISException {
        final TRAXAdapter trax = getTRAXService();
        final String assessmentCourseCode = courseCode.getCode();
        final ca.bc.gov.educ.isd.eis.assessment.AssessmentCourseCode traxCourseCode
                = ca.bc.gov.educ.isd.eis.assessment.AssessmentCourseCode.fromValue(assessmentCourseCode);

        // Get the flattened student assessment records from TRAX.
        final LitAssessmentResult result = (LitAssessmentResult) trax.readStudent_Assessment(
                pen, sessionDate, traxCourseCode);

        return result;
    }

    private PersonalEducationNumber getStudentPEN() throws DomainServiceException {
        final StudentXRefService xrefService = getStudentXRefService();
        final StudentXRef xref = xrefService.read();
        final PersonalEducationNumber pen = xref.getPen();

        return pen;
    }
    
    private boolean isFrench(AssessmentCourseCode code) {
        return isFrench(code.getCode());
    }
    
    private boolean isFrench(String code) {
        return AssessmentCourseCode.LITERACY_FRENCH.getCode().equals(code);
    }

    private TRAXAdapter getTRAXService() {
        return this.traxAdapter;
    }

    private ReportService getReportService() {
        return this.reportService;
    }

    private StudentXRefService getStudentXRefService() {
        return this.studentXRefService;
    }
}
