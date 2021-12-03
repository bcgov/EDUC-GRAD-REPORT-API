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
package ca.bc.gov.educ.grad.model.student;

import java.util.Date;

/**
 * TODO: Extend from Student.
 *
 * TODO: Remove duplication with respect to StudentDemographic.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentDetails {

    /**
     * get the PEN which the data corresponds to.
     *
     * @return PEN
     */
    String getPen();

    /**
     * get the student's first name.
     *
     * @return first name
     */
    String getFirstName();

    /**
     * get the student's middle name.
     *
     * @return middle name
     */
    String getMiddleName();

    /**
     * get the student's last name.
     *
     * @return last name
     */
    String getLastName();

    /**
     * get the student's date of birth.
     *
     * @return date of birth
     */
    Date getBirthDate();

    /**
     * get student status.
     *
     * @return status
     */
    Character getStatus();

    /**
     * get the student's current grade level.
     *
     * @return grade
     */
    String getGrade();

    /**
     * get the graduation program associated with the student and used to
     * determine the student's graduating requirements.
     *
     * @return Graduation program code
     */
    String getGradProgram();

    /**
     * get the student certificate category.
     *
     * @return SCCP or Dogwood
     */
    String getCertificateCategory();

    /**
     * Is the student eligible for transcripts.
     *
     * @return True if student can send/order transcripts
     */
    Boolean isTranscriptEligible();

    /**
     * is the student permitted to order a graduation certificate.
     *
     * @return True if student can order a graduating certificate
     */
    Boolean isPrintCertEligible();

    /**
     * Is the student a former student or a current student.
     *
     * @return F for former or C for current
     */
    Character getStudentType();

    /**
     * get the code reference for the student's secondary school.
     *
     * @return ministry reference code
     */
    String getMincode();

    /**
     * get the French certificate type for printing.
     *
     * @return 'S' or 'F' or blank
     */
    String getFrenchCertificate();

    /**
     * get the English certificate type for printing.
     *
     * @return 'E' or blank
     */
    String getEnglishCertificate();

    /**
     * get the school category code.
     *
     * @return
     */
    String getSchoolCategory();

    /**
     * Returns the school type indicator, which can be used to determine whether
     * the school is independent.
     *
     * @return Empty string means non-independent, otherwise an independent
     * school; this will not return null.
     */
    String getSchoolTypeIndicator();

    /**
     * get the student's graduating school.
     *
     * @return school name
     */
    String getSchoolName();

    /**
     * Get the School Transcript printing eligibility flag.
     *
     * @return eligibility flag.
     */
    String getTransEligibilityFlag();

    /**
     * get the month and year the student graduated.
     *
     * @return certificate date format yyyyMM
     */
    Date getCertificateDate();

    /**
     * get the flashed digital signature reference.
     *
     * @return certificate signature
     */
    String getCertificateSignature();

    /**
     * get the English certificate type. There are 4 Dogwood types and 1 SCCP
     * type. If there is not an English certificate available this will be
     * empty.
     *
     * @return
     */
    String getEnCertType();

    /**
     * FIXME: Use PaperType enumeration.
     *
     * get the BC Mail media colour/type. These are predefined indicators which
     * correspond to the type of certificate.
     *
     * @return one of YEDR, YEDB, YED2
     */
    String getEnCertMedia();

    /**
     * get the French certificate type. There are 2 Dogwood types. If there is
     * not a French certificate available this will be empty.
     *
     * @return
     */
    String getFrCertType();

    /**
     * FIXME: Use PaperType enumeration.
     *
     * get the BC Mail media colour/type. These are predefined indicators which
     * correspond to the type of certificate.
     *
     * @return YEDR, YEDB
     */
    String getFrCertMedia();

    /**
     *
     * @return
     */
    Date getSccDate();

    /**
     * get the mincode of the school from which the student graduated.
     *
     * @return
     */
    String getGradMincode();

    /**
     * get the Dogwood flag
     *
     * @return
     */
    String getDogwoodFlag();

    /**
     * get the True PEN
     *
     * @return
     */
    String getTruePen();

    /**
     * Gets the flag to identify if the option "Send Interim and Final Marks
     * when they become available" should be render.
     *
     * @return
     */
    Boolean getShowSendInterim();

    /**
     * Gets the flag to identify if the option "Send Final Marks when they
     * become available" should be render.
     *
     * @return
     */
    Boolean getShowSendFinalMarks();

    /**
     * Gets the flag to identify if the option "Send my transcript
     * electronically now and send updates when they become available" should be
     * render.
     *
     * @return
     */
    Boolean getShowSendTransNow();

    /**
     * Gets the flag to identify if the student has a status error.
     *
     * @return
     */
    Boolean getStudentStatusError();

    /**
     * Gets that flag to identify if the student type is a SCCP
     *
     * @return
     */
    Boolean getIsSCCP();

    /**
     * Gets that flag to identify if the student type is a Adult. This is used
     * to select the Catalogue item.
     *
     * @return
     */
    Boolean getIsAdult();

    /**
     * Answers true if the student status is 'D'.
     *
     * @return false The student status is not 'D'.
     */
    Boolean isDeceased();

    /**
     * Answers true if the student status is 'C'.
     *
     * @return false The student status is not 'C'.
     */
    Boolean isCurrentStudent();
}
