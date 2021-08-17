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
package ca.bc.gov.educ.isd.eis.common;

/**
 * Constant database constants for TRAX columns.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class DatabaseConstants {

    // TRAX Common
    public static final String COL_AUDIT_UPDATED_DATE = "UPDATE_DT";
    public static final String COL_STATUS = "STATUS";

    // TRAX Address
    public static final String COL_ADDRESS_LINE_1 = "ADDRESS1";
    public static final String COL_ADDRESS_LINE_2 = "ADDRESS2";
    public static final String COL_ADDRESS_CITY = "CITY";
    public static final String COL_ADDRESS_REGION = "PROV_CODE";
    public static final String COL_ADDRESS_POSTAL_CODE = "POSTAL";

    // TRAX School
    public static final String COL_MINCODE = "MINCODE";
    public static final String COL_DISTRICT_NUMBER = "DISTNO";
    public static final String COL_SCHOOL_NUMBER = "SCHLNO";
    public static final String COL_SCHOOL_CATEGORY_CODE = "SCHOOL_CATEGORY_CODE";
    public static final String COL_SCHOOL_NAME = "SCHL_NAME";
    public static final String COL_SCHOOL_SIGNATURE_DISTRICT_NUMBER = "SIGNATURE_DISTNO";
    public static final String COL_SCHOOL_TRANSCRIPT_ELIGIBILITY_FLAG = "XCRIPT_ELIG";
    public static final String COL_ADDRESS_PHONE = "PHONE";
    public static final String COL_SCHOOL_INDICATOR_TYPE = "SCHL_IND_TYPE";
    public static final String COL_SCHOOL_DOGWOOD_ELIGIBILITY_FLAG = "DOGWOOD_ELIG";
    public static final String COL_SCHOOL_LOGO = "LOGO_TYPE";

    // TRAX Student
    public static final String COL_STUDENT_NUMBER = "STUD_NO";
    public static final String COL_STUDENT_NAME_FIRST = "STUD_GIVEN";
    public static final String COL_STUDENT_NAME_MIDDLE = "STUD_MIDDLE";
    public static final String COL_STUDENT_NAME_LAST = "STUD_SURNAME";
    public static final String COL_STUDENT_BIRTHDATE = "STUD_BIRTH";
    public static final String COL_ADDRESS_COUNTRY_CODE = "CNTRY_CODE";
    public static final String COL_STUDENT_STATUS = "STUD_STATUS";
    public static final String COL_STUDENT_GRADE = "STUD_GRADE";
    public static final String COL_STUDENT_GENDER = "STUD_GENDER";
    public static final String COL_STUDENT_TRUE_PEN = "STUD_TRUE_NO";
    public static final String COL_GRADUATION_DATE = "GRAD_DATE";
    public static final String COL_GRADUATION_REQUIREMENT_YEAR = "GRAD_REQT_YEAR";
    public static final String COL_GRADUATION_FLAG = "GRAD_FLAG";
    public static final String COL_GRADUATION_MESSAGE = "GRAD_MSG_TXT";
    public static final String COL_HONOUR_FLAG = "HONOUR_FLAG";
    public static final String COL_DOGWOOD_FLAG = "DOGWOOD_FLAG";
    public static final String COL_SCHOOL_COMPLETION_CERTIFICATE_DATE = "SCC_DATE";
    public static final String COL_MINCODE_GRADUATION = "MINCODE_GRAD";
    public static final String COL_PROGRAM_CODE_1 = "PRGM_CODE";
    public static final String COL_PROGRAM_CODE_2 = "PRGM_CODE2";
    public static final String COL_PROGRAM_CODE_3 = "PRGM_CODE3";
    public static final String COL_PROGRAM_CODE_4 = "PRGM_CODE4";
    public static final String COL_PROGRAM_CODE_5 = "PRGM_CODE5";
    public static final String COL_CERTIFICATE_ENGLISH = "ENGLISH_CERT";
    public static final String COL_CERTIFICATE_FRENCH = "FRENCH_CERT";

    // TRAX Student Demographics
    public static final String COL_STUDENT_DEMOG_NAME_FIRST = "FIRST_NAME";
    public static final String COL_STUDENT_DEMOG_NAME_MIDDLE = "MIDDLE_NAME";
    public static final String COL_STUDENT_DEMOG_NAME_LAST = "LAST_NAME";
    public static final String COL_STUDENT_DEMOG_BIRTHDATE = "BIRTHDATE";
    public static final String COL_STUDENT_DEMOG_STATUS = "CURRENT_FORMER_FLAG";

    // TRAX Provincial Exam
    public static final String COL_GRADUATION_STATUS = "USED_FOR_GRAD";

    // TRAX Course and Transcript
    public static final String COL_COURSE_CODE = "CRSE_CODE";
    public static final String COL_COURSE_LEVEL = "CRSE_LEVEL";
    public static final String COL_COURSE_SESSION = "CRSE_SESSION";

    public static final String COL_COURSE_NAME = "COURSE_NAME";
    public static final String COL_COURSE_PK_SESSION = "COURSE_SESSION";
    public static final String COL_COURSE_SCHOOL_PERCENT = "SCHOOL_PCT";
    public static final String COL_COURSE_BEST_SCHOOL_PERCENT = "BEST_SCHOOL_PCT";
    public static final String COL_COURSE_EXAM_PERCENT = "EXAM_PCT";
    public static final String COL_COURSE_BEST_EXAM_PERCENT = "BEST_EXAM_PCT";
    public static final String COL_COURSE_FINAL_PERCENT = "FINAL_PCT";
    public static final String COL_COURSE_FINAL_LETTER_GRADE = "FINAL_LG";
    public static final String COL_COURSE_CREDITS = "NUM_CREDITS";
    public static final String COL_COURSE_INTERIM_MARK = "INTERIM_MARK";

    public static final String COL_COURSE_INTERIM_PERCENT = "INTERIM_PCT";
    public static final String COL_COURSE_INTERIM_LETTER_GRADE = "INTERIM_LG";

    // TRAX Transcript
    public static final String COL_TRANSCRIPT_COURSE_TYPE = "CRSE_TYPE";
    public static final String COL_TRANSCRIPT_REPORT_COURSE_TYPE = "RPT_CRS_TYPE";
    public static final String COL_TRANSCRIPT_FOUNDATION_REQUIREMENT = "FOUNDATION_REQ";
    public static final String COL_TRANSCRIPT_SPECIAL_CASE = "SPECIAL_CASE";
    public static final String COL_TRANSCRIPT_RELATED_CRSE = "RELATED_CRSE";
    public static final String COL_TRANSCRIPT_RELATED_LEVEL = "RELATED_LEVEL";

    // TRAX Scholarship
    public static final String COL_AWARD_CODE = "AWARD_CODE";
    public static final String COL_AWARD_YEAR = "AWARD_YEAR";
    public static final String COL_AWARD_CASH_DATE = "AWARD_CASH_DATE";
    public static final String COL_AWARD_AMOUNT = "AWARD_AMT";

    // TRAX Student PSI
    public static final String COL_PSI_CODE = "PSI_CODE";
    public static final String COL_PSI_NAME = "PSI_NAME";
    public static final String COL_PSI_YEAR = "PSI_YEAR";
    public static final String COL_PSI_STATUS = "PSI_STATUS";
    public static final String COL_PSI_DATE_ENTERED = "DATE_ENTERED";
    public static final String COL_PSI_TRANSMISSION_MODE = "TRANSMISSION_MODE";
    public static final String COL_PSI_PROCESS_DATE = "PROCESS_DATE";

    // TRAX TX
    public static final String COL_PSI_TX_ID = "TX_ID";

    // TRAX tables and views
    public static final String ENTITY_STUDENT_MASTER = "STUDENT_MASTER_VW";
    public static final String ENTITY_SCHOOL_MASTER = "SCHOOL_MASTER_VW";
    public static final String ENTITY_SCHOOL_TABLE = "TAB_SCHOOL_VW";
    public static final String ENTITY_PROVINCIAL_EXAM = "PROV_EXAM_VW";
    public static final String ENTITY_STUDENT_TRANSCRIPT_COURSES = "STUD_XCRSE_VW";
    public static final String ENTITY_STUDENT_SCHOLARSHIP = "STUDENT_SCHOLARSHIP_VW";
    public static final String ENTITY_STUDENT_EXAM = "TSW_MAILER_EXAM_VW";
    public static final String ENTITY_STUDENT_TRANSCRIPT_RESULTS = "TSW_TRAN_COURSE_VW";
    public static final String ENTITY_STUDENT_TRANSCRIPT_COURSE = "STS_TRAN_CRSE_VW";
    public static final String ENTITY_STUDENT_ASSESSMENT = "TSW_MAILER_ASSMT_DTL_VW";
    public static final String ENTITY_STUDENT_TRANSCRIPT_DEMOGRAPHICS = "TSW_TRAN_DEMOG_VW";
    public static final String ENTITY_STUDENT_PSI = "TSW_STUD_PSI_VW";
    public static final String ENTITY_STUDENT_PSI_CHOICES = "TSW_PSI_CHOICES_VW";
    public static final String ENTITY_STUDENT_TRANSCRIPT_PSI = "TSW_TX_PSI_VW";

    private DatabaseConstants() {
    }
}
