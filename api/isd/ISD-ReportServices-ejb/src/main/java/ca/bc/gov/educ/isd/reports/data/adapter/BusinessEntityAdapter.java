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
 *  File:                $Id:: BusinessEntityAdapter.java 12336 2019-12-13 19:30:2#$
 *  Date of Last Commit: $Date:: 2019-12-13 11:30:20 -0800 (Fri, 13 Dec 2019)  $
 *  Revision Number:     $Rev:: 12336                                          $
 *  Last Commit by:      $Author:: cfunk                                       $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.data.adapter;

import ca.bc.gov.educ.isd.grad.GradProgram;
import ca.bc.gov.educ.isd.grad.GraduationProgramCode;
import ca.bc.gov.educ.isd.grad.NonGradReason;
import ca.bc.gov.educ.isd.reports.data.assessment.impl.AssessmentScore;
import ca.bc.gov.educ.isd.reports.data.assessment.impl.LiteracyAssessmentResult;
import ca.bc.gov.educ.isd.reports.data.assessment.impl.NumeracyAssessmentResult;
import ca.bc.gov.educ.isd.reports.data.assessment.impl.RawScore;
import ca.bc.gov.educ.isd.reports.data.impl.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.common.Constants.*;
import static ca.bc.gov.educ.isd.reports.data.BusinessEntity.nullSafe;
import static ca.bc.gov.educ.isd.reports.data.impl.DistrictOrganisation.LOGO_CODE_BC;
import static java.lang.String.format;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 * Responsible for adapting data from the services interfaces into the report
 * object model.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class BusinessEntityAdapter {

    private static final String CLASSNAME = BusinessEntityAdapter.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);
    private static final int TRAX_COURSE_LENGTH = 5;

    /**
     * Creates and populates a Certificate instance.
     *
     * @param certificate The data to adapt for reports.
     * @return A new Certificate instance populated with data.
     */
    public static Certificate adapt(
            final ca.bc.gov.educ.isd.cert.Certificate certificate) {
        return new Certificate.Builder()
                .withIssueDate(certificate.getIssued())
                .withSignatureBlockTypes(certificate.getSignatureBlockTypes())
                .build();
    }

    /**
     * Creates and populates a Student instance.
     *
     * @param student The data to adapt for reports.
     * @return A new Student instance populated with data.
     */
    public static Student adapt(
            final ca.bc.gov.educ.isd.student.Student student) {
        validate(student, "student");

        final PostalAddress address = adapt(student.getCurrentMailingAddress());

        ca.bc.gov.educ.isd.reports.data.impl.Student std = new Student.Builder()
                .withCreatedOn(student.getCreatedOn())
                .withPEN(student.getPen().getValue())
                .withBirthdate(student.getBirthdate())
                .withFirstName(student.getFirstName())
                .withLastName(student.getLastName())
                .withMiddleNames(student.getMiddleName())
                .withGrade(student.getGrade())
                .withAddress(address)
                .withSignatureBlockTypes(student.getSignatureBlockTypes())
                .build();

        return std;
    }

    /**
     * Maps school information to a School instance.
     *
     * @param school The data to adapt for reports.
     * @param logoCode The district organization logo code.
     * @return A new School instance populated with data from studentInfo.
     */
    public static School adapt(
            final ca.bc.gov.educ.isd.school.School school,
            final String logoCode) {
        validate(school, "school");

        final DistrictOrganisation org = new DistrictOrganisation.Builder()
                .withLogoCode(logoCode)
                .build();

        return new School.Builder()
                .withMinistryCode(school.getMinistryCode())
                .withName(school.getName())
                .withTypeIndicator(school.getTypeIndicator())
                .withTypeBanner(school.getTypeBanner())
                .withAddress(adapt(school.getPostalAddress()))
                .withDistrictOrganisation(org)
                .build();
    }

    /**
     * Maps school information to a School instance.
     *
     * @param school The data to adapt for reports.
     * @return A new School instance populated with data from studentInfo.
     */
    public static School adapt(
            final ca.bc.gov.educ.isd.school.School school) {
        return adapt(school, LOGO_CODE_BC);
    }

    /**
     * Maps address information to an Address instance.
     *
     * @param address The data to adapt for reports.
     * @return A new Address instance populated with data.
     */
    private static PostalAddress adapt(
            final ca.bc.gov.educ.isd.common.party.address.PostalAddress address) {
        validate(address, "address");

        return new PostalAddress.Builder()
                .withStreetLine1(address.getStreetLine1())
                .withStreetLine2(address.getStreetLine2())
                .withStreetLine3(address.getStreetLine3())
                .withCity(address.getCity())
                .withRegion(address.getRegion())
                .withPostalCode(address.getPostalCode())
                .withCountryCode(address.getCountryCode())
                .build();
    }

    /**
     * Maps a transcript instance to a student's set of transcript result
     * instances. The XML transcript requires that the courses are grouped by
     * academic session.
     *
     * @param transcript The data to adapt for reports.
     * @param student The student to populate.
     */
    public static void adapt(
            final ca.bc.gov.educ.isd.transcript.Transcript transcript,
            final Student student) {
        validate(transcript, "transcript for student");

        final Map<String, AcademicSession> aSMap = new HashMap<>();

        final GraduationProgram gp = student.getGraduationProgram();
        final GraduationProgramCode code = gp.getCode();

        for (final ca.bc.gov.educ.isd.transcript.TranscriptResult result : transcript.getResults(code)) {
            // Transcipt results immeadiately added to student for report generation.
            final TranscriptResult tr = adapt(result);
            student.addTranscriptResult(tr);

            // Building of Academic session data for results required for XML structure.
            final String formatDate = formatSessionDate(tr.getSessionDate());

            if (!aSMap.containsKey(formatDate)) {
                // Build Academic session
                final AcademicSession newSession = createAcademicSession(formatDate);
                // Add course
                newSession.addTranscriptResult(tr);
                // Put Academic session and date string to map
                aSMap.put(formatDate, newSession);
            } else {
                // Get academic session
                final AcademicSession fetchedSession = aSMap.get(formatDate);
                // Add course to session
                fetchedSession.addTranscriptResult(tr);
            }
        }

        // For each academic session in list add to the student.
        for (final Map.Entry<String, AcademicSession> entry : aSMap.entrySet()) {
            student.addAcademicSession(entry.getValue());
        }
    }

    /**
     * Maps a transcript instance to a student's set of transcript result
     * instances. The XML transcript requires that the courses are grouped by
     * academic session.
     *
     * @param achievement The data to adapt for reports.
     * @param student The student to populate.
     */
    public static void adapt(
            final ca.bc.gov.educ.isd.achievement.Achievement achievement,
            final Student student) {
        validate(achievement, "achievement for student");

        final Map<String, AcademicSession> aSMap = new HashMap<>();

        final GraduationProgram gp = student.getGraduationProgram();
        final GraduationProgramCode code = gp.getCode();

        for (final ca.bc.gov.educ.isd.achievement.AchievementResult result : achievement.getResults(code)) {
            // Transcipt results immeadiately added to student for report generation.
            final AchievementResult tr = adapt(result);
            student.addAchievementResult(tr);

            // Building of Academic session data for results required for XML structure.
            final String formatDate = formatSessionDate(tr.getSessionDate());

            if (!aSMap.containsKey(formatDate)) {
                // Build Academic session
                final AcademicSession newSession = createAcademicSession(formatDate);
                // Add course
                newSession.addAchievementResult(tr);
                // Put Academic session and date string to map
                aSMap.put(formatDate, newSession);
            } else {
                // Get academic session
                final AcademicSession fetchedSession = aSMap.get(formatDate);
                // Add course to session
                fetchedSession.addAchievementResult(tr);
            }
        }

        // For each academic session in list add to the student.
        for (final Map.Entry<String, AcademicSession> entry : aSMap.entrySet()) {
            student.addAcademicSession(entry.getValue());
        }
    }

    /**
     * Maps transcript results (marks and courses) to a flatter hierarchy for
     * reports. A transcript result in the reports contains all the information
     * needed to populate a single row of data in the report.
     *
     * @param tr The data to adapt for reports.
     */
    private static TranscriptResult adapt(
            final ca.bc.gov.educ.isd.transcript.TranscriptResult tr) {
        validate(tr, "transcript result");

        final ca.bc.gov.educ.isd.transcript.Course c = tr.getCourse();
        final ca.bc.gov.educ.isd.transcript.Mark m = tr.getMark();

        // TODO: Add to builder?
        final String originalCourseId
                = getOriginalCourseId(c.getRelatedCourse(), c.getRelatedLevel());

        return new TranscriptResult.Builder()
                .withCourseCode(c.getCode())
                .withCourseLevel(c.getLevel())
                .withCourseName(c.getName())
                .withCredits(c.getCredits())
                .withSessionDate(c.getSessionDate())
                .withReportCourseType(c.getType())
                .withBestExamPercent(m.getExamPercent())
                .withBestSchoolPercent(m.getSchoolPercent())
                .withFinalGrade(createGrade(m.getFinalPercent(), m.getFinalLetterGrade()))
                .withInterimGrade(createGrade(m.getInterimPercent(), m.getInterimLetterGrade()))
                .withEquivalencyChallenge(tr.getEquivalencyChallenge())
                .withRequirementMet(tr.getRequirementMet())
                .withRequirementMetName(tr.getRequirementMetName())
                .withUsedForGrad(tr.getUsedForGrad())
                .withOriginalCourseId(originalCourseId)
                .build();
    }

    /**
     * Maps transcript results (marks and courses) to a flatter hierarchy for
     * reports. A transcript result in the reports contains all the information
     * needed to populate a single row of data in the report.
     *
     * @param tr The data to adapt for reports.
     */
    private static AchievementResult adapt(
            final ca.bc.gov.educ.isd.achievement.AchievementResult tr) {
        validate(tr, "transcript result");

        final ca.bc.gov.educ.isd.transcript.Course c = tr.getCourse();
        final ca.bc.gov.educ.isd.transcript.Mark m = tr.getMark();

        // TODO: Add to builder?
        final String originalCourseId
                = getOriginalCourseId(c.getRelatedCourse(), c.getRelatedLevel());

        return new AchievementResult.Builder()
                .withCourseCode(c.getCode())
                .withCourseLevel(c.getLevel())
                .withCourseName(c.getName())
                .withCredits(c.getCredits())
                .withSessionDate(c.getSessionDate())
                .withReportCourseType(c.getType())
                .withBestExamPercent(m.getExamPercent())
                .withBestSchoolPercent(m.getSchoolPercent())
                .withFinalGrade(createGrade(m.getFinalPercent(), m.getFinalLetterGrade()))
                .withInterimGrade(createGrade(m.getInterimPercent(), m.getInterimLetterGrade()))
                .withEquivalencyChallenge(tr.getEquivalencyChallenge())
                .withRequirementMet(tr.getRequirementMet())
                .withRequirementMetName(tr.getRequirementMetName())
                .withUsedForGrad(tr.getUsedForGrad())
                .withOriginalCourseId(originalCourseId)
                .build();
    }

    /**
     * Maps examination results.
     *
     * @param exam The data to adapt for reports.
     * @param student The student to populate.
     */
    public static void adapt(
            final ca.bc.gov.educ.isd.exam.Exam exam,
            final Student student) {
        validate(exam, "exam");
        validate(student, "student");

        for (ca.bc.gov.educ.isd.exam.ExamResult result : exam.getResults()) {
            final ExaminationResult er = adapt(result);
            student.addExaminationResult(er);
        }
    }

    /**
     * Maps an ExamResult instance to a student's examination result instance.
     * This adds the ExaminationResult to the student's list.
     *
     * @param er The data to adapt for reports.
     * @return A new ExaminationResul instance populated with data from the
     * parameter.
     */
    private static ExaminationResult adapt(
            final ca.bc.gov.educ.isd.exam.ExamResult er) {
        validate(er, "exam result");

        final ca.bc.gov.educ.isd.exam.Mark m = er.getMark();
        validate(m, "mark");

        return new ExaminationResult.Builder()
                .withBestExamPercent(m.getBestExamPercent())
                .withBestSchoolPercent(m.getBestSchoolPercent())
                .withExamPercent(m.getExamPercent())
                .withFinalGrade(createGrade(m.getFinalPercent(), m.getFinalLetterGrade()))
                .withSchoolPercent(m.getSchoolPercent())
                .withSessionDate(er.getSessionDate())
                .withCourseName(er.getTitle())
                .withCourseCode(er.getCourseCode())
                .build();
    }

    /**
     * Associates a numeracy assessment result to a student's assessment
     * results.
     *
     * @param nar The data to adapt for reports.
     * @param student The student to populate with assessment data. parameter.
     */
    public static void adapt(
            final ca.bc.gov.educ.isd.assessment.NumeracyAssessmentResult nar,
            final Student student) {
        validate(nar, "numeracy assessment result");
        validate(student, "student");

        final NumeracyAssessmentResult result = adapt(nar);

        student.setNumeracyAssessmentResult(result);
    }

    /**
     * Maps a numeracy assessment result to a report-centric numeracy assessment
     * result.
     *
     * @param nar The data to adapt for reports.
     */
    private static NumeracyAssessmentResult adapt(
            final ca.bc.gov.educ.isd.assessment.NumeracyAssessmentResult nar) {
        final List<ca.bc.gov.educ.isd.assessment.RawScore> scores = nar.getRawScores();
        final int size = scores.size();
        final List<RawScore> rawScores = new ArrayList<>(size);

        for (final ca.bc.gov.educ.isd.assessment.RawScore rawScore : scores) {
            final RawScore adaptedScore = adapt(rawScore);
            rawScores.add(adaptedScore);
        }

        return new NumeracyAssessmentResult.Builder()
                .withProficiencyScore(nar.getProficiencyScore())
                .withSessionDate(nar.getSessionDate())
                .withRawScores(rawScores)
                .build();
    }
    
    /**
     * Associates a literacy assessment result to a student's assessment
     * results.
     *
     * @param nar The data to adapt for reports.
     * @param student The student to populate with assessment data. parameter.
     */
    public static void adapt(
            final ca.bc.gov.educ.isd.assessment.LiteracyAssessmentResult nar,
            final Student student) {
        validate(nar, "literacy assessment result");
        validate(student, "student");

        final LiteracyAssessmentResult result = adapt(nar);

        student.setLiteracyAssessmentResult(result);
    }

    /**
     * Maps a literacy assessment result to a report-centric literacy assessment
     * result.
     *
     * @param nar The data to adapt for reports.
     */
    private static LiteracyAssessmentResult adapt(
            final ca.bc.gov.educ.isd.assessment.LiteracyAssessmentResult nar) {
        final List<ca.bc.gov.educ.isd.assessment.RawScore> scores = nar.getRawScores();
        final int size = scores.size();
        final List<RawScore> rawScores = new ArrayList<>(size);

        for (final ca.bc.gov.educ.isd.assessment.RawScore rawScore : scores) {
            final RawScore adaptedScore = adapt(rawScore);
            rawScores.add(adaptedScore);
        }

        return new LiteracyAssessmentResult.Builder()
                .withProficiencyScore(nar.getProficiencyScore())
                .withSessionDate(nar.getSessionDate())
                .withRawScores(rawScores)
                .build();
    }

    private static RawScore adapt(
            final ca.bc.gov.educ.isd.assessment.RawScore rawScore) {
        final List<ca.bc.gov.educ.isd.assessment.AssessmentScore> scores
                = rawScore.getAssessmentScores();
        final int size = scores.size();

        final List<AssessmentScore> adaptedScores = new ArrayList<>(size);

        for (final ca.bc.gov.educ.isd.assessment.AssessmentScore score : scores) {
            final AssessmentScore adaptedScore = adapt(score);
            adaptedScores.add(adaptedScore);
        }
        
        final RawScore result = new RawScore.Builder()
                .withAssessmentScores(adaptedScores)
                .withRawScoreCategory(rawScore.getRawScoreCategory())
                .withTotalScore(rawScore.getTotalScore())
                .withTotalStudentScore(rawScore.getTotalStudentScore())
                .build();

        return result;
    }

    private static AssessmentScore adapt(ca.bc.gov.educ.isd.assessment.AssessmentScore score) {
        final AssessmentScore result = new AssessmentScore.Builder()
                .withAssessmentCode(score.getAssessmentCode())
                .withMaximumScore(score.getMaximumScore())
                .withStudentScore(score.getStudentScore())
                .build();

        return result;
    }

    /**
     * Maps student status information (graduation notes, former/current state,
     * etc.).
     *
     * @param nonGradReasons The data to adapt for reports.
     * @param graduationMessageText Description of why the student graduated.
     * @return A Status instance, never null.
     */
    public static Status adapt(
            final List<NonGradReason> nonGradReasons,
            final String graduationMessageText) {
        final List<IncompletionReason> irs = adapt(nonGradReasons);

        return new Status.Builder()
                .withIncompletionReasons(irs)
                .withGraduationMessage(graduationMessageText)
                .build();
    }

    /**
     * Helper method to adapt non-grad reasons to incompletion reasons.
     *
     * @param nonGradReasons The data to adapt.
     * @return A new list of incompletion reasons populated with data from the
     * parameter.
     */
    private static List<IncompletionReason> adapt(
            final List<NonGradReason> nonGradReasons) {
        final List<IncompletionReason> incompletionReasons = new ArrayList<>();

        for (final NonGradReason ngr : nonGradReasons) {
            final IncompletionReason incompletion = new IncompletionReason.Builder()
                    .withCode(ngr.getCode())
                    .withDescription(ngr.getDescription())
                    .build();
            incompletionReasons.add(incompletion);
        }

        return incompletionReasons;
    }

    /**
     * Maps a student's graduation program to a report graduation program
     * instance.
     *
     * @param gradProgram Student information containing a graduation program
     * code.
     * @return A GraduationProgram instance with a description that corresponds
     * to the code.
     */
    public static GraduationProgram adapt(final GradProgram gradProgram) {
        validate(gradProgram, "graduation program");

        return new GraduationProgram.Builder()
                .withCode(gradProgram.getCode().toString())
                .build();
    }

    /**
     * Maps the ISD packing slip details API to the reports-specific packing
     * details API. This performs an internal adaption of the postal address.
     *
     * @param details The packing slip details to adapt for reporting.
     * @return A PackingSlipDetails instance that can be passed into the
     * reports.
     */
    public static PackingSlipDetails adapt(
            final ca.bc.gov.educ.isd.reports.packingslip.PackingSlipDetails details) {
        validate(details, "packing slip details");

        return new PackingSlipDetails.Builder()
                .withAddress(adapt(details.getAddress()))
                .withDocumentsShipped(details.getDocumentsShipped())
                .withRecipient(details.getRecipient())
                .withOrderedByName(details.getOrderedByName())
                .withOrderNumber(details.getOrderNumber())
                .withOrderDate(details.getOrderDate())
                .withDestinationType(details.getDestinationType())
                .build();
    }

    /**
     * Maps the ISD GraduationData to a Student to be used in constructing
     * graduation program data.
     *
     * TODO: Parameter comments should add information.
     *
     * @param academicAward The students graduation program data.
     * @param student The student.
     * @param graduationProgramCode The graduation program code.
     * @return
     */
    public static AcademicAward adapt(
            final ca.bc.gov.educ.isd.transcript.GraduationData academicAward,
            final Student student, final GraduationProgramCode graduationProgramCode) {
        validate(academicAward, "academic award");
        validate(student, "student");
        validate(graduationProgramCode, "graduation program code");

        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_ISO_8601_YMD);
        final Date graduationDate = academicAward.getGraduationDate();

        // When empty, the corresponding PESC XML document element is suppressed.
        final String graduationDateFormatted = academicAward.hasGraduated() ? sdf.format(graduationDate) : "";

        return new AcademicAward.Builder()
                .withGraduationDate(graduationDateFormatted)
                .withHonoursFlag(academicAward.getHonorsFlag())
                .withDogwoodFlag(academicAward.getDogwoodFlag())
                .withTotalCredUFG(academicAward.getTotalCreditsUsedForGrad(), student.getGraduationProgram())
                .withProgramCodeNames(academicAward.getProgramNames())
                .build();
    }

    /**
     * Creates a new grade instance based on the given percent and letter
     * grades.
     *
     * @param percent The percentage (or three-letter code) a student received
     * as the mark for a course.
     * @param letter The corresponding letter grade.
     * @return A new instance that encapsulates both values.
     */
    private static Grade createGrade(final String percent, final String letter) {
        return new Grade(percent, letter);
    }

    /**
     * TODO: Document and use dates/calendar instances, rather than string
     * manipulations and integers (e.g., Calendar.SEPTEMBER).
     *
     * @param sessionDate The TRAX date for the academic session.
     * @return An academic session record.
     */
    private static AcademicSession createAcademicSession(final String sessionDate) {
        final AcademicSession as = new AcademicSession();
        final AcademicSessionDetail asd = new AcademicSessionDetail();
        asd.setSessionDesignator(sessionDate);
        asd.setSessionName(sessionDate);
        asd.setSessionSchoolYear(createSessionSchoolYear(sessionDate));

        as.setAcademicSessionDetail(asd);

        return as;
    }

    /**
     * The PESC format requires session years to be in YYYY-YYYY format, where
     * the years indicate when the session started and ended.
     *
     * @param sessionDate The TRAX date for the academic session.
     * @return A non-null String.
     */
    protected static String createSessionSchoolYear(final String sessionDate) {
        validate(sessionDate, "session date");

        final DateFormat df = new SimpleDateFormat(DATE_YEAR_MONTH);
        String result = nullSafe(sessionDate);

        try {
            if (!result.isEmpty()) {
                final Date date = df.parse(sessionDate);
                final Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);

                final int year = calendar.get(YEAR);
                final int month = calendar.get(MONTH);
                int sessionBegan = year;
                int sessionEnded = year + 1;

                if (month < Calendar.SEPTEMBER) {
                    sessionBegan = year - 1;
                    sessionEnded = year;
                }

                result = format("%s-%s", sessionBegan, sessionEnded);
            }
        } catch (final ParseException e) {
            LOG.log(Level.WARNING, "Could not parse session date: <{0}>", sessionDate);
            result = "FORMAT ERROR (" + sessionDate + ") " + e.getMessage();
        }

        return result;
    }

    /**
     *
     * @param relatedCourse
     * @param relatedLevel
     * @return originalCourseId
     */
    private static String getOriginalCourseId(
            final String relatedCourse, final String relatedLevel) {
        String course = nullSafe(relatedCourse).trim();
        final String level = nullSafe(relatedLevel).trim();
        //ST-1753 TRAX has the format of 5 characters as the course length, STs has to be consistent with it.
        int length = TRAX_COURSE_LENGTH - course.length();
        if (length >= 0) {
            for (int i = 0; i < length; i++) {
                course = course + " ";
            }
        }

        final String result = (course + level);
        return result;
    }

    /**
     * Throws an illegal argument exception if the given object is null.
     *
     * @param o The object to check against null.
     * @param message The human-readable name for the object to include in the
     * error message passed into the exception.
     *
     * @throws IllegalArgumentException when o == null.
     */
    private static void validate(final Object o, final String message) {
        if (o == null) {
            final String msg = "The " + message + " is null.";
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * This method converts a TRAX-specific date format into the PESC standard
     * date format for sessions.
     *
     * @param traxSessionDate TRAX-formatted date.
     * @return PESC-formatted date, or FORMAT ERROR message.
     */
    private static String formatSessionDate(final String traxSessionDate) {
        String result = nullSafe(traxSessionDate);

        try {
            if (!result.isEmpty()) {
                final DateFormat traxSdf = new SimpleDateFormat(DATE_TRAX_YM);
                final DateFormat ymFormat = new SimpleDateFormat(DATE_YEAR_MONTH);

                final Date date = traxSdf.parse(traxSessionDate);
                result = ymFormat.format(date);
            }
        } catch (final ParseException ex) {
            LOG.log(Level.WARNING, "Could not parse session date: <{0}>", traxSessionDate);
            result = "FORMAT ERROR (" + traxSessionDate + ") " + ex.getMessage();
        }

        return result;
    }
}
