/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: JRDataSourceFactory.java 12336 2019-12-13 19:30#$
 *  Date of Last Commit: $Date:: 2019-12-13 11:30:20 -0800 (Fri, 13 Dec 2019)  $
 *  Revision Number:     $Rev:: 12336                                          $
 *  Last Commit by:      $Author:: cfunk                                       $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.jasper.impl;

import ca.bc.gov.educ.grad.report.dto.reports.data.assessment.impl.AssessmentScore;
import ca.bc.gov.educ.grad.report.dto.reports.data.impl.*;
import ca.bc.gov.educ.grad.report.model.assessment.AssessmentCode;
import ca.bc.gov.educ.grad.report.model.assessment.RawScoreCategory;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static ca.bc.gov.educ.grad.report.dto.reports.data.impl.DistrictOrganisation.LOGO_CODE_BC;
import static ca.bc.gov.educ.grad.report.dto.reports.data.impl.TranscriptResult.*;
import static ca.bc.gov.educ.grad.report.model.assessment.AssessmentCode.*;
import static ca.bc.gov.educ.grad.report.model.common.Constants.DATE_TRAX_YM;
import static ca.bc.gov.educ.grad.report.model.graduation.GraduationProgramCode.PROGRAM_1950;
import static java.lang.String.format;

/**
 * Only used by the Jaspersoft Studio reports IDE to obtain mock data for
 * various report previews.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class JRDataSourceFactory {

    /**
     * Fill with data known to cause reporting issues to expedite development
     * within the reporting IDE.
     */
    private final static List<TranscriptResult> GROUPED_RESULTS[] = new List[3];

    /**
     * Created the following arrays using:
     *
     * <code>
     * select
     * 'results.add(new TranscriptResult.Builder()' ||
     * '.withCourseName("'|| c.COURSE_NAME ||'")' ||
     * '.withBestExamPercent(randomPercent())' ||
     * '.withBestSchoolPercent(randomPercent())' ||
     * '.withCourseCode("'|| c.CRSE_CODE ||'")' ||
     * '.withCourseLevel("'|| c.CRSE_LEVEL ||'")' ||
     * '.withFinalGrade(randomGrade())' ||
     * '.withSessionDate(randomSessionDate())' ||
     * '.withCredits(randomCredits())' ||
     * '.withReportCourseType("'|| c.RPT_CRS_TYPE ||'")' ||
     * '.withRequirementMet(randomRequirement())' ||
     * '.withEquivalencyChallenge(randomEC())' ||
     * '.build());'
     * from
     * ISD.TEMP_STS_TRAN_CRSE c
     * where
     * c.stud_no = '114253214'
     * order by
     * c.CRSE_LEVEL desc, c.RPT_CRS_TYPE, c.COURSE_NAME;
     * </code>
     */
    static {
        // Unused.
        GROUPED_RESULTS[0] = new ArrayList<>(64);

        GROUPED_RESULTS[1] = new ArrayList<>(26);
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("WORK EXPERIENCE 12B").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("WEX").withCourseLevel("12B").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("WORK EXPERIENCE 12A").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("WEX").withCourseLevel("12A").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("COMMUNICATIONS 12").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("COM").withCourseLevel("12").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("1").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("ENGLISH 12").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("EN").withCourseLevel("12").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("1").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("BA PHILOSOPHY 12").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("YPHIL").withCourseLevel("12").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("BA STRENGTH & CONDITIONING 12").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("YSCD").withCourseLevel("12").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("LAW 12").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("LAW").withCourseLevel("12").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("PHYSICAL EDUCATION 12").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("PE").withCourseLevel("12").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("SOCIAL STUDIES 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("SS").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("1").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("APPLICATIONS OF MATHEMATICS 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("AMA").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("BA PSYCHOLOGY 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("YPSY").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("ELECTRONICS 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("EL").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("ENGLISH 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("EN").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("ENGLISH 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("EN").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("METAL FABRICATION AND MACHINING 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("MFM").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("PHYSICAL EDUCATION 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("PE").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("SCIENCE & TECHNOLOGY 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("SCT").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("APPLICATIONS OF MATHEMATICS 10").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("AMA").withCourseLevel("10").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("1").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("ENGLISH 10").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("EN").withCourseLevel("10").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("1").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("PHYSICAL EDUCATION 10").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("PE").withCourseLevel("10").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("PLANNING 10").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("PLAN").withCourseLevel("10").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("SOCIAL STUDIES 10").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("SS").withCourseLevel("10").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("GRADUATION TRANSITIONS").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("GT").withCourseLevel(" ").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("LITERACY ENGLISH").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("LTE").withCourseLevel(" ").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("3").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("LITERACY ENGLISH").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("LTE").withCourseLevel(" ").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("3").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[1].add(new TranscriptResult.Builder().withCourseName("NUMERACY ENGLISH").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("NME").withCourseLevel(" ").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("3").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());

        GROUPED_RESULTS[2] = new ArrayList<>(35);
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("BA LEISURE & RECREATIONAL ACTIVITIES 12G").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("YLRA").withCourseLevel("12G").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("BA LEISURE & RECREATIONAL ACTIVITIES 12E").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("YLRA").withCourseLevel("12E").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("JDF BA LEISURE & RECREATIONAL ACTIVITIES").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("YLRA").withCourseLevel("12D").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("CAMOSUN COLLEGE COURSE 12C").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("PSIB").withCourseLevel("12C").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("BA HEALTH RELATED ACTIVITIES 12B").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("YHRA").withCourseLevel("12B").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("CAMOSUN COLLEGE COURSE 12B").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("PSIB").withCourseLevel("12B").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("CAMOSUN COLLEGE COURSE 12A").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("PSIB").withCourseLevel("12A").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("ENGLISH 12").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("EN").withCourseLevel("12").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("1").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("BIOLOGY 12").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("BI").withCourseLevel("12").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("EXTERNAL SPORT: ATHLETE 12").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("UXSA").withCourseLevel("12").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("HISTORY 12").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("HI").withCourseLevel("12").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("PHYSICAL EDUCATION 12").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("PE").withCourseLevel("12").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("JDF BA LRA 11G - IGNITE ATHLETE TRAINING").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("YLRA").withCourseLevel("11G").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("JDF BA HRA 11E - INDIVIDUAL SPORT TRAINI").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("YHRA").withCourseLevel("11E").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("JDF BA LRA 11D- INDIVIDUAL SPORT TRAININ").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("YLRA").withCourseLevel("11D").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("JDF BA HRA 11B - SPORT PERFORMANCE 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("YHRA").withCourseLevel("11B").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("JDF APPLIED SKILLS 11A").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("ASK").withCourseLevel("11A").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("PSYCHOLOGY 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("YPSYC").withCourseLevel("11A").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("SOCIAL STUDIES 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("SS").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("1").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("CHEMISTRY 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("CH").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("ENGLISH 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("EN").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("FOUNDATIONS OF MATHEMATICS 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("FOM").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("JDF APPLIED SKILLS 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("ASK").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("PHYSICAL EDUCATION 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("PE").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("PHYSICS 11").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("PH").withCourseLevel("11").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("ENGLISH 10").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("EN").withCourseLevel("10").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("1").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("FOUNDATIONS OF MATH AND PRE-CALCULUS").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("FMP").withCourseLevel("10").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("1").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("PHYSICAL EDUCATION 10").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("PE").withCourseLevel("10").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("PLANNING 10").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("PLAN").withCourseLevel("10").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("SOCIAL STUDIES 10").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("SS").withCourseLevel("10").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("SPANISH 10").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("SP").withCourseLevel("10").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("TECHNOLOGY EDUC 10: DRAFTING AND DESIGN").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("TED").withCourseLevel("10").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("GRADUATION TRANSITIONS").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("GT").withCourseLevel(" ").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("2").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("LITERACY ENGLISH").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("LTE").withCourseLevel(" ").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("3").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
        GROUPED_RESULTS[2].add(new TranscriptResult.Builder().withCourseName("NUMERACY ENGLISH").withBestExamPercent(randomPercent()).withBestSchoolPercent(randomPercent()).withCourseCode("NME").withCourseLevel(" ").withFinalGrade(randomGrade()).withSessionDate(randomSessionDate()).withCredits(randomCredits()).withReportCourseType("3").withRequirementMet(randomRequirementCode()).withEquivalencyChallenge(randomEC()).build());
    }

    /**
     * Changes randomly (used to test changing expressions in the report).
     */
    private static Date sessionDate = randomDate();

    public static final String[] GRADUATION_MESSAGES = {
        ""
        + "Based on the information provided by the school, this student "
        + "has satisfied British Columbia course-based graduation "
        + "requirements with Honours Standing as of June 1994. This "
        + "student has participated in the Advanced Placement Program.",
        ""
        + "Based on the information provided by the school, this student has "
        + "graduated in the Adult Graduation Program. Graduation date: March "
        + "2018.",
        ""
        + "Based on the information provided by "
        + "the school, this student has not satisfied British "
        + "Columbia course-based graduation requirements as of "
        + "September 2016.  Note: Students are no longer eligible "
        + "to graduate on the course-based graduation p program "
        + "unless required courses were completed by June 30, 2001. "
        + "Contact your school counselor for more information.",
        ""
        + "Based on the information provided by the school, this student has "
        + "not yet graduated in the 2004 Graduation Program. Students with "
        + "questions should  contact their schools immediately.",
        ""
        + "Based on the information provided by the school, this student has "
        + "satisfied British Columbia course-based graduation requirements with "
        + "Honours Standing as of June 1994.",
        ""
        + "Based on the information provided by the school, this student has "
        + "graduated in the 2004 Graduation Program. Graduation date: August "
        + "2008.  This student has successfully completed the "
        + "Programme Francophone."
    };

    private final static int MIN_SCHOOL_YEAR = 2002;

    /**
     * Returns an Address instance populated with mock data for a school.
     *
     * @return A mock Address for testing purposes.
     */
    public static PostalAddress createSchoolAddress() {
        return new PostalAddress.Builder()
                .withStreetLine1("1230 School Street")
                .withStreetLine2("PO Box 500")
                .withCity(randomCity())
                .withRegion("BC")
                .withPostalCode("V8N1W1")
                .build();
    }

    /**
     * Returns an Address instance populated with mock data for a student.
     *
     * @return A mock Address for testing purposes.
     */
    public static PostalAddress createStudentAddress() {
        return new PostalAddress.Builder()
                .withStreetLine1("1230 Student Street")
                .withStreetLine2(randomPercent() % 2 == 0 ? "PO Box 404" : "")
                .withCity(randomCity())
                .withRegion("BC")
                .withPostalCode("V8W1W1")
                .withCountryCode(randomCountryCode())
                .build();
    }

    /**
     * Returns a GraduationProgram instance with mock data for a student.
     *
     * @param code The graduation program code.
     * @return A mock GraduationProgram for testing purposes.
     */
    public static GraduationProgram createGraduationProgram(final String code) {
        return new GraduationProgram.Builder()
                .withCode(code, null)
                .build();
    }

    /**
     * Returns signatures that are digitally added to certificate reports. The
     * signatures are real, but which signatures are used is mock.
     *
     * @return A mock Signatories for testing purposes.
     */
    public static Signatories createSignatureSet() {
        final Signatories signatures = new Signatories();
        signatures.setSchoolSignatory(randomSchoolSignatory());
        return signatures;
    }

    /**
     * Returns a school with mock data.
     *
     * @return A new School instance, never null.
     */
    public static School createSchool() {
        final DistrictOrganisation districtOrganisation
                = createDistrictOrganisation(LOGO_CODE_BC);

        return createSchool(districtOrganisation);
    }

    /**
     * Returns a School instance populated with mock data.
     *
     * @param districtOrganisation For the organisation name and logo.
     * @return A mock School for testing purposes.
     */
    public static School createSchool(final DistrictOrganisation districtOrganisation) {
        final PostalAddress address = createSchoolAddress();

        // The ministry code needs to contain a number that coincides with the
        // signature flash.
        return new School.Builder()
                .withMinistryCode("099" + (randomPercent() * 10000))
                .withName(randomSchoolName())
                .withTypeBanner(randomTypeBanner())
                .withAddress(address)
                .withDistrictOrganisation(districtOrganisation)
                .build();
    }

    /**
     * Returns a Status instance populated with mock data.
     *
     * @return A mock Status for testing purposes.
     */
    public static Status createStatus() {
        final String message = random(GRADUATION_MESSAGES);
        final Status status = new Status.Builder()
                .withGraduationMessage(message)
                .build();

        return status;
    }

    /**
     * Creates a single TranscriptResult instance populated with mock data.
     *
     * @param reportCourseType "1" means provincially examinable status; "2"
     * means non-provincially examinable status; and "3" means assessment.
     * @param courseLevel Grade level for the course (e.g., "10", "11").
     * @return A mock TranscriptResult instance for testing purposes.
     */
    public static TranscriptResult createTranscriptResult(
            final String reportCourseType, final String courseLevel) {

        return new TranscriptResult.Builder()
                .withCourseName(randomCourseName(reportCourseType) + " " + courseLevel)
                .withBestExamPercent(randomPercent())
                .withBestSchoolPercent(randomPercent())
                .withCourseCode(randomCourseCode())
                .withCourseLevel(randomCourseLevel(courseLevel))
                .withFinalGrade(randomGrade())
                .withSessionDate(randomSessionDate())
                .withCredits(randomCredits())
                .withReportCourseType(reportCourseType)
                .withRequirementMet(randomRequirementCode())
                .withEquivalencyChallenge(randomEC())
                .build();
    }

    /**
     * Returns the district organisation of the school the student attended.
     *
     * @param logoCode The logo code for the school (e.g., "BC", "YU").
     * @return A mock Address for testing purposes.
     */
    public static DistrictOrganisation createDistrictOrganisation(
            final String logoCode) {
        return new DistrictOrganisation.Builder()
                .withLogoCode(logoCode)
                .build();
    }

    /**
     * Returns a single reason (from potentially many reasons) that a student
     * has not yet graduated.
     *
     * @param code A codified reason for not graduating.
     * @param reason A descriptive reason for not graduating.
     * @return A reason the student has not yet graduated.
     */
    public static IncompletionReason createIncompletionReason(
            final String code, final String reason) {
        return new IncompletionReason.Builder()
                .withCode(code)
                .withDescription(reason)
                .build();
    }

    /**
     * Returns a mock student object.
     *
     * @return A student in BC, never null.
     */
    public static Student createStudent() {
        return createStudent(randomPEN());
    }

    /**
     * Returns a Student instance populated with mock data for a BC district
     * organization and graduation program of SCCP.
     *
     * @param pen Personal Education Number
     * @return A mock Student for testing purposes.
     */
    public static Student createStudent(final String pen) {
        return createStudent(pen, LOGO_CODE_BC);
    }

    /**
     * Returns a Student instance populated with mock data.
     *
     * @param pen Personal Education Number
     * @param logoCode "BC" or "YU" to indicate what organisation and logo.
     * @return A mock Student for testing purposes.
     */
    public static Student createStudent(final String pen, final String logoCode) {
        return createStudent(pen, logoCode, PROGRAM_1950.toString());
    }

    /**
     * Returns a Student instance populated with mock data.
     *
     * @param pen Personal Education Number
     * @param logoCode "BC" or "YU" to indicate what organisation and logo.
     * @param grad The graduation program code.
     * @return A mock Student for testing purposes.
     */
    public static Student createStudent(
            final String pen, final String logoCode, final String grad) {
        final DistrictOrganisation district = createDistrictOrganisation(logoCode);
        final School school = createSchool(district);
        final PostalAddress studentAddress = createStudentAddress();
        final GraduationProgram graduationProgram = createGraduationProgram(grad);
        final Status status = createStatus();

        int examinable = 32;
        int nonExaminable = 15;
        int assessable = 0;

        final List<TranscriptResult> transcriptResults = graduationProgram.isAdult()
                ? createAdultTranscriptResults()
                : createTranscriptResults(examinable, nonExaminable, assessable);

        int size = randomPercent() % 5 + 1;
        final List<Certificate> certificates = createCertificateResults(size);

        if (randomPercent() % 2 == 0) {
            final List<IncompletionReason> reasons = createIncompletionReasons();
            status.setIncompletionReasons(reasons);
        }

        final Student student = new Student.Builder()
                .withPEN(pen)
                .withFirstName(randomFirstName())
                .withLastName(randomLastName())
                .withMiddleNames(randomMiddleName())
                .withBirthdate(getStudentBirthdate())
                .withSchool(school)
                .withAddress(studentAddress)
                .withTranscriptResults(transcriptResults)
                .withCertificates(certificates)
                .withGraduationProgram(graduationProgram)
                .withStatus(status)
                .build();

        return student;
    }

    /**
     * Used by the reporting IDE to help test transcript results independently
     * of a transcript.
     *
     * @return A variety of transcript results.
     */
    public static Collection<TranscriptResult> createTranscriptResultsCollection() {
        return createTranscriptResults(15, 16, 2);
    }

    private static List<TranscriptResult> createTranscriptResults(
            final int examinable, final int nonExaminable, final int assessable) {

        final int size = examinable + nonExaminable + assessable;
        final List<TranscriptResult> results = new ArrayList<>(size);

        final List<TranscriptResult> examinableCourses
                = createTranscriptCourses(
                        examinable, COURSE_TYPE_PROVINCIAL, size);

        results.addAll(examinableCourses);

        final List<TranscriptResult> nonExaminableCourses
                = createTranscriptCourses(
                        examinable, COURSE_TYPE_NONPROVINCIAL, size);

        results.addAll(nonExaminableCourses);

        final List<TranscriptResult> assessableCourses
                = createTranscriptCourses(
                        examinable, COURSE_TYPE_ASSESSMENT, size, false);

        results.addAll(assessableCourses);

        return results;
    }

    private static List<TranscriptResult> createTranscriptCourses(
            final int courses,
            final String courseType,
            final int size) {
        return createTranscriptCourses(courses, courseType, size, true);
    }

    private static List<TranscriptResult> createTranscriptCourses(
            final int courses,
            final String courseType,
            final int size,
            final boolean includeLevel) {
        final List<TranscriptResult> results = new ArrayList<>(courses);

        for (int i = 0; i < courses; i++) {
            final String type = courseType;
            final String level = includeLevel ? calculateLevel(i, size) : "";

            final TranscriptResult tr = createTranscriptResult(type, level);
            results.add(tr);
        }

        return results;
    }

    private static List<TranscriptResult> createAdultTranscriptResults() {
        // Provincial, Non-Provincial, and Assessments, respectively.
//        final int p = (randomPercent() % 30) + 10;
//        final int n = random(0, 1, (randomPercent() % 10) + 10);
//        final int a = random(0, 1, (randomPercent() % 5) + 3);

        // Set n = 15 to test a bug with "End of Section" not appearing on
        // the second page when p > 15 for this Adult transcript.
        final int p = 32;
        final int n = 14;
        final int a = 1;
        final List<TranscriptResult> results = new ArrayList<>(p + n + a);

        populateTranscriptResults(COURSE_TYPE_PROVINCIAL, p, results);
        populateTranscriptResults(COURSE_TYPE_NONPROVINCIAL, n, results);
        populateTranscriptResults(COURSE_TYPE_ASSESSMENT, a, results);

        return results;
    }

    /**
     * Adds a number of Provincial transcript results to the given list.
     *
     * @param size Number of transcript results to create.
     * @param results List to populate with the results.
     */
    private static void populateTranscriptResults(
            final String courseType,
            final int size,
            final List<TranscriptResult> results) {
        for (int i = 0; i < size; i++) {
            final String level = calculateLevel(i, size);

            final TranscriptResult tr = createTranscriptResult(courseType, level);
            results.add(tr);
        }
    }

    private static List<Certificate> createCertificateResults(final int size) {
        final List<Certificate> results = new ArrayList<>(size);

        if (randomPercent() % 5 != 0) {
            for (int i = 1; i <= size; i++) {
                results.add(createCertificate());
            }
        }

        return results;
    }

    private static List<IncompletionReason> createIncompletionReasons() {
        final List<IncompletionReason> incompletionReasons = Arrays.asList(
                createIncompletionReason("DSC11", "Disturbed class with Disco Duck"),
                createIncompletionReason("BPI10", "Played Banana Phone incessantly"),
                createIncompletionReason("RRT12", "Rick-rolled students twice"),
                createIncompletionReason("BMS10", "Badgers, mushrooms, and snakes"),
                createIncompletionReason("PMH12", "Pretended to be Max Headroom"),
                createIncompletionReason("MCE10", "Made classroom Escheresque"),
                createIncompletionReason("TMS12", "Continuously traced Möbius strip"),
                createIncompletionReason("DCW12", "Drummed like Chick Webb during examination"),
                createIncompletionReason("ENG10", "No EN 10, FRALP 10 or EFP 12"),
                createIncompletionReason("ENG11", "No EN 11, COM 11, FRALP 11 or EFP 11"),
                createIncompletionReason("ENG12", "No EN 12, COM 12, FRALP 12 or EFP 12"),
                createIncompletionReason("SOC10", "No Socials 10"),
                createIncompletionReason("SOC11", "No Social Studies 11"),
                createIncompletionReason("MAT10", "No Math 10"),
                createIncompletionReason("MAT11", "No Mathematics 11"),
                createIncompletionReason("SCI10", "No Science 10"),
                createIncompletionReason("SCI11", "No Science 11")
        );

        return incompletionReasons;
    }

    /**
     * Returns a student with the given names and a PEN of STUDENT_PEN.
     *
     * @param first Student's first name.
     * @param middle Student's middle names.
     * @param last Student's last name.
     * @return A Student instance with mock data, never null.
     */
    public static Student createStudentNamed(
            final String first,
            final String middle,
            final String last) {
        return new Student.Builder()
                .withPEN(randomPEN())
                .withFirstName(first)
                .withLastName(last)
                .withMiddleNames(middle)
                .withBirthdate(getStudentBirthdate())
                .withSchool(createSchool())
                .build();
    }

    /**
     * Returns a Certificate instance with mock data.
     *
     * @return A mock Certificate for testing purposes.
     */
    public static Certificate createCertificate() {
        return new Certificate.Builder()
                .withIssueDate(randomDay(2011, 3))
                .withStudent(createStudentNamed("Delia", "Alicja", "Skinner"))
                .withSignatories(createSignatureSet())
                .build();
    }

    /**
     * Returns the single student instance wrapped in a collection. The value
     * returned is passed into the reporting engine.
     *
     * @return A collection with a single student instance.
     */
    public static Collection<Student> createStudentCollection() {
        return Arrays.asList(createStudent());
    }

    /**
     * For testing packing slip reports using Jaspersoft Studio.
     *
     * @return A collection of 1 packing slip details instance.
     */
    public static Collection<PackingSlipDetails> createPackingSlipCollection() {
        return Arrays.asList(createPackingSlipDetails());
    }

    /**
     * For testing certificate reports.
     *
     * @return A collection of 1 certificate instances.
     */
    public static Collection<Certificate> createCertificateCollection() {
        return Arrays.asList(createCertificate());
    }

    /**
     * 12/28/1993 must display as 28-DEC-1993. This tests the difference between
     * "dd-MMM-yyyy" (correct) and "dd-MMM-YYYY" (incorrect).
     */
    private static Date getStudentBirthdate() {
        return new Date(757065600000L);
    }

    /**
     * Returns a random name from a list of post-secondary institutions.
     *
     * @return A non-null String, possibly empty.
     */
    private static String randomPSIName() {
        return random(
                "University of British Columbia",
                "Harvard University",
                "Langara College",
                "(New) Westminster"
        );
    }

    /**
     * Returns a delivery method at random.
     *
     * @return One of "Mail", "PDF", "PSI: XML send updates", etc.
     */
    private static String randomDelivery() {
        return random(
                "Mail",
                "PDF",
                "PSI: XML send now",
                "PSI: XML send updates",
                "PSI: Electronic send now",
                "PSI: Electronic send interim and final"
        );
    }

    /**
     * For testing reports using Jaspersoft Studio.
     *
     * @return An object to wrap inside a collection.
     */
    public static PackingSlipDetails createPackingSlipDetails() {
        final PostalAddress address = createStudentAddress();

        return new PackingSlipDetails.Builder()
                .withAddress(address)
                .withDocumentsShipped(200)
                .withCurrentSlip(1)
                .withTotalSlips(4)
                .withOrderedByName(randomName())
                .withRecipient(randomName())
                .withAttentionTo(randomFullName())
                .withPaperTypeCode("YED4")
                .withOrderNumber("A123456")
                .withOrderDate(randomDate())
                .withOrderTypeName(PackingSlipDetails.CERTIFICATES)
                .build();
    }

    /**
     * Returns a grade that has a random percent and a random letter. The two
     * don't need to align, logically. This can also return a grade of AEG or
     * DSQ to help test suppression of assessment hyperlinks on the PEAR.
     *
     * @return A new Grade instance, never null.
     */
    private static Grade randomGrade() {
        final String percentage;
        final String letter;

        if (randomInteger() % 3 == 0) {
            percentage = randomBoolean() ? "AEG" : "DSQ";
            letter = "---";
        } else {
            final int randomPercent = randomPercent();

            percentage = Integer.toString(randomPercent);
            letter = calculateLetterGrade(randomPercent);
        }

        final Grade grade = new Grade(percentage, letter);
        return grade;
    }

    /**
     * Returns a random course name (with a prefix to help with debugging).
     *
     * @return A non-null string.
     */
    private static String randomCourseName(final String reportCourseType) {
        final String prefix;

        // Prefix the course name for debugging purposes.
        switch (reportCourseType) {
            case COURSE_TYPE_PROVINCIAL:
                // Provincial
                prefix = "P.";
                break;
            case "2":
                // Non-Provincial
                prefix = "N.";
                break;
            case "3":
            default:
                // Assessment
                prefix = "A.";
                break;
        }

        final String courses[] = {
            "Quantum Mechanics",
            "Calculus",
            "Linear Algebra",
            "Quantum Computing",
            "Aerospace Engineering",
            "Astronomy",
            "Biochemistry",
            "Computer Science",
            "Civil Engineering",};

        return format("%s %s", prefix, random(courses));
    }

    private static String randomScholarshipName() {
        return random(
                "BC International Student Ambassador Scholarship for K-12 and Post-Secondary",
                "Secondary School Apprentice Scholarship",
                "District/Authority Scholarship (voucher only)",
                "Graduation Program Examinations"
        );
    }

    private static String randomRedeemed() {
        return random("Y", "N", "N/A", "");
    }

    private static String randomCourseCode() {
        return random("UWAPM", "HEFF", "TEWF", "PLANF", "PHYSF");
    }

    /**
     * Adds "A", "2", or "A9" onto the end of the level at random.
     *
     * @param level The level to change, at random.
     * @return The given level with a post-fix appended sometimes.
     */
    private static String randomCourseLevel(String level) {
        if (randomPercent() % 4 != 0) {
            level += random("A", "2", "A9");
        }

        return level;
    }

    private static String randomRequirementCode() {
        return random("1", "2", "4", "12", "13", "TLA");
    }

    /**
     * Returns a random equivalency/challenge met value.
     *
     * @return "E", "C", or blank.
     */
    private static String randomEC() {
        return random("E", "C", "");
    }

    /**
     * Returns a String selected at random from a list of cities.
     *
     * @return A city name, never null.
     */
    private static String randomCity() {
        return random(
                "Victoria",
                "New Westminster",
                "Harrison Hot Springs",
                "Fort Saskatchewan",
                "Centreville-Wareham-Trinity"
        );
    }

    /**
     * Returns a String selected at random from a list of country codes.
     *
     * @return A country code, never null.
     */
    private static String randomCountryCode() {
        return random("CA", "CN", "HK", "GB", "YU");
    }

    /**
     * Returns a random selection from "A", "D", "S", or "T".
     *
     * @return A non-null, non-empty String.
     */
    private static String randomScholarshipType() {
        return random("A", "D", "S", "T");
    }

    /**
     * Returns a random selection from a list of names.
     *
     * @return A non-null, non-empty String.
     */
    private static String randomSchoolName() {
        return random(
                "'Na Aksa Gyilak'yoo",
                "SD 59 Strongstart BC Outreach Program #3",
                "Peter Skene Ogden Secondary School",
                "Yunesit'in Esgul",
                "Gansiwilaaks Distributed Learning School",
                "Moricetown Elementary & Secondary School",
                "Fraser Park Secondary Program (Open)",
                "The International High School @ VIU",
                "Adult Education SD#23",
                "Mount Elizabeth Middle/Secondary",
                "Encompass 10-12",
                "ASCEND Online Distributed Learning Prg."
        );
    }

    /**
     * Returns either an independent school group banner text string or an empty
     * string.
     *
     * @return A non-null String, possibly empty.
     */
    private static String randomTypeBanner() {
        return random("B.C. Independent Schools - Group 1", "");
    }

    private static String randomSchoolSignatory() {
        final String signatories[] = {
            "33", // Kristi
            "36", // Danae
            "93", // Taylor
            "08", // Shu
            "57", // Veronica
            "23", // Delia
        };

        return random(signatories);
    }

    private static String randomQueued() {
        return format("%02d:%02d:%02d",
                randomPercent() % 15,
                randomPercent() % 24,
                randomPercent() % 60);
    }

    public static String randomLastName() {
        return random(
                "Lee",
                "Featherstonehaugh",
                "Franc-ma\u00E7on",
                "Bourg dit Bellehumeur",
                "Keihanaikukauakahihulihe'ekahaunaele",
                "Wolfeschlegelsteinhausenbergerdorff, Sr."
        );
    }

    /**
     * Returns a random first name.
     *
     * @return
     */
    public static String randomFirstName() {
        return random(
                "Ai",
                "Bartholomew",
                "Marie-No\u00EBlle",
                "\u00C6milius"
        );
    }

    public static String randomMiddleName() {
        return random(
                "Hern\u00E1ndez",
                "L\u00F3pez",
                "\"Lokelani\""
        );
    }

    /**
     * Returns a random first and last name, no middle name.
     *
     * @return A non-null String instance.
     */
    public static String randomName() {
        return format("%s %s", randomFirstName(), randomLastName());
    }

    public static String randomFullName() {
        return format("%s %s %s", randomFirstName(), randomMiddleName(), randomLastName());
    }

    //<editor-fold defaultstate="collapsed" desc="Basic random data types.">
    /**
     * Returns a random integer from 0 - 100.
     *
     * @return A whole number up to 100.
     */
    public static int randomPercent() {
        return new Random().nextInt(101);
    }

    /**
     * Returns a random value from the given list.
     *
     * @param <E> The data type for the random list.
     * @param e The list of values to select from.
     * @return A random value from e.
     */
    public static <E> E random(final E... e) {
        final int index = new Random().nextInt(e.length);
        return e[index];
    }

    /**
     * Returns a random year, month, and day between 1950 and 2025.
     *
     * @return A date between 1950 and 2025.
     */
    public static Date randomDate() {
        final Calendar c = Calendar.getInstance();
        c.set(randomPercent() % 75 + MIN_SCHOOL_YEAR,
                randomPercent() % 12,
                randomDay()
        );

        return c.getTime();
    }

    /**
     * Returns a date for the given year and month, but with a random day.
     *
     * @param year The date's year.
     * @param month The date's month.
     * @return A date with a random day.
     */
    private static Date randomDay(int year, int month) {
        final Calendar c = Calendar.getInstance();
        c.set(year, month, randomDay());

        return c.getTime();
    }

    /**
     * Returns a random day of the month (from 1 to 27).
     *
     * @return A day of the month that is valid for any month (except September
     * 1752).
     */
    private static int randomDay() {
        return (randomPercent() % 27) + 1;
    }

    /**
     * Use a date, possibly new, possibly the same as the last call, so that the
     * entries can be grouped by date.
     *
     * @return A date instance, never null.
     */
    private static Date randomGroupableDate() {
        if (randomPercent() % 10 == 1 || JRDataSourceFactory.sessionDate == null) {
            JRDataSourceFactory.sessionDate = randomDate();
        }

        return JRDataSourceFactory.sessionDate;
    }

    /**
     * Returns a random session date in TRAX yyyyMM format.
     *
     * @return A random date suitable for grouping (by session).
     */
    private static String randomSessionDate() {
        final Date randomDate = randomGroupableDate();
        final DateFormat df = new SimpleDateFormat(DATE_TRAX_YM);
        final String result = df.format(randomDate);

        return result;
    }

    /**
     * Generates an amount with exactly 2 fractional digits less than 100.00.
     *
     * @return A non-null String formatted as currency.
     */
    public static Double randomMoney100() {
        return Math.random() * 100.00d;
    }

    /**
     * Returns an autoboxed boolean value.
     *
     * @return true or false, at random.
     */
    public static Boolean randomBoolean() {
        return randomPercent() % 2 == 0;
    }

    /**
     * Returns a random positive integer from 0 to 10,000.
     *
     * @return A number from 0 to 10,000.
     */
    public static Integer randomInteger() {
        return randomPercent() * 100;
    }

    public static Long randomLong() {
        return randomPercent() * 1000L;
    }

    /**
     * Returns a random positive BigDecimal from 0 to 100.
     *
     * @return A number from 0 to 100.
     */
    public static BigDecimal randomBigDecimal() {
        return BigDecimal.valueOf(randomPercent() + (randomPercent() / 100.0));
    }

    /**
     * Returns a random double.
     *
     * @return A number from 0 to 100.
     */
    public static Double randomDouble() {
        return (double) randomPercent();
    }

    private static String randomCredits() {
        return random("2", "2p", "(4)", "", "4");
    }

    /**
     * Maps a grade percentage to a grade letter. This is not meant to replicate
     * the correct behaviour for the Ministry, only to ensure that the report is
     * tested for strings of varying lengths.
     *
     * @param percentage The percentage to map to a letter grade.
     * @return "A+" through "F".
     */
    private static String calculateLetterGrade(final int percentage) {
        String result = "F";

        if (percentage > 95) {
            result = "A+";
        } else if (percentage > 90) {
            result = "A";
        } else if (percentage > 80) {
            result = "B";
        } else if (percentage > 75) {
            result = "C+";
        } else if (percentage > 70) {
            result = "C";
        } else if (percentage > 60) {
            result = "C-";
        } else if (percentage > 50) {
            result = "D";
        }

        return result;
    }

    /**
     * Increments the level such that groups form in numerical order. There must
     * be three blank rows for the four different levels on non-adult
     * transcripts.
     *
     * @param i Current transcript index (from 0 to size).
     * @param size Total number of transcript results to include on the report.
     * @return A grade level that corresponds to the index of a transcript
     * result.
     */
    private static String calculateLevel(final int i, final int size) {
        return "" + (10 + (int) (i / ((float) size / 3f)));
    }

    private static String randomPEN() {
        return random("123456780", "111111111", "000000000", "999999999");
    }

    private static List<AssessmentScore> createAssessmentScores(final RawScoreCategory category) {
        final List<AssessmentScore> result;

        switch (category) {
            case ONLINE:
                result = createOnlineAssessmentScores();
                break;
            case WRITTEN_RESPONSE:
                result = createWrittenAssessmentScores();
                break;
            case TASK:
                result = createByTaskAssessmentScores();
                break;
            case PART:
                result = createByPartAssessmentScores();
                break;
            default:
                result = createByPartAssessmentScores();
                break;
        }

        return result;
    }

    private static List<AssessmentScore> createOnlineAssessmentScores() {
        final List<AssessmentScore> result = new ArrayList<>(4);

        result.add(createAssessmentScore(PLAN_AND_DESIGN));
        result.add(createAssessmentScore(REASONED_ESTIMATES));
        result.add(createAssessmentScore(FAIR_SHARE));
        result.add(createAssessmentScore(MODEL));

        return result;
    }

    private static List<AssessmentScore> createWrittenAssessmentScores() {
        final List<AssessmentScore> result = new ArrayList<>(2);

        final AssessmentCode code1 = random(NO_RESPONSE, PLAN_AND_DESIGN, REASONED_ESTIMATES, FAIR_SHARE, MODEL);
        final AssessmentCode code2 = random(NO_RESPONSE, PLAN_AND_DESIGN, REASONED_ESTIMATES, FAIR_SHARE, MODEL);

        result.add(createAssessmentScore(code1));
        result.add(createAssessmentScore(code2));

        return result;
    }
    
    private static List<AssessmentScore> createByTaskAssessmentScores() {
        final List<AssessmentScore> result = new ArrayList<>(2);
        
        result.add(createAssessmentScore(COMPREHEND));
        result.add(createAssessmentScore(COMMUNICATE));
        
        return result;
    }
    
    private static List<AssessmentScore> createByPartAssessmentScores() {
        final List<AssessmentScore> result = new ArrayList<>(7);
        
        result.add(createAssessmentScore(PART_A));
        result.add(createAssessmentScore(SELECTED_RESPONSE_A));
        result.add(createAssessmentScore(GRAPHIC_ORGANIZER));
        result.add(createAssessmentScore(WRITTEN_RESPONSE_A));
        
        final AssessmentCode partB = random(PART_B_E, PART_B_I);
        result.add(createAssessmentScore(partB));
        result.add(createAssessmentScore(SELECTED_RESPONSE_B));
        result.add(createAssessmentScore(WRITTEN_RESPONSE_B));
              
        return result;
    }

    private static AssessmentScore createAssessmentScore(final AssessmentCode assessmentCode) {
        return new AssessmentScore.Builder()
                .withStudentScore(randomScore())
                .withMaximumScore(randomPercent() / 2)
                .withAssessmentCode(assessmentCode)
                .build();
    }

    /**
     * Returns -1 every once in a while to test "No Response" results.
     *
     * @return A random score between -1 and 50.
     */
    private static Integer randomScore() {
        return randomPercent() % 5 == 0 ? -1 : randomPercent() / 2;
    }
}
