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
package ca.bc.gov.educ.grad.model.student;

import ca.bc.gov.educ.grad.model.school.SchoolMaster;

import java.util.Date;

/**
 * Defines the public methods which allow access to the StudentDemographic
 * object containing information on a student.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentDemographic {

    /**
     * Returns the PEN which the data corresponds to.
     *
     * @return PEN
     */
    String getPen();

    /**
     * Returns the student's first name.
     *
     * @return first name
     */
    String getFirstName();

    /**
     * Returns the student's middle name.
     *
     * @return middle name
     */
    String getMiddleName();

    /**
     * Returns the student's last name.
     *
     * @return last name
     */
    String getLastName();

    /**
     * Returns the student's date of birth.
     *
     * @return date of birth
     */
    Date getBirthDate();

    /**
     * Returns the student status.
     *
     * @return status
     */
    Character getStatus();

    /**
     * Returns the student's current grade level.
     *
     * @return grade
     */
    String getGrade();

    /**
     * Returns the graduation program associated with the student and used to
     * determine the student's graduating requirements. The programs are
     * differentiated by year, but may have non-numeric names (e.g., "SCCP").
     *
     * @return A non-null string representing the graduation program code.
     */
    String getGradProgram();

    /**
     * Returns the student certificate category.
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
     * Returns the 3-digit school district code used for signature lookups.
     *
     * @return A 3-digit string, or empty, but never null.
     */
    String getSchoolSignatureDistrictNumber();

    /**
     * Returns the Ministry code for the school from which the student
     * graduated.
     *
     * @return Ministry's graduation school code.
     */
    String getGradMincode();

    /**
     * Returns the Ministry code for the student's secondary school.
     *
     * @return Ministry school code
     */
    String getMincode();

    /**
     * Returns the French certificate type for printing.
     *
     * @return 'S' or 'F' or blank
     */
    String getFrenchCertificate();

    /**
     * Returns the English certificate type for printing.
     *
     * @return 'E' or blank
     */
    String getEnglishCertificate();

    /**
     * get the student's graduating school.
     *
     * @return school name
     */
    String getSchoolName();

    /**
     * Get the student's graduating school.
     *
     * @return Graduating school.
     */
    SchoolMaster getSchool();

    /**
     * Returns the School Transcript printing eligibility flag.
     *
     * @return eligibility flag.
     */
    String getTransEligibilityFlag();

    /**
     * Returns the month and year the student graduated.
     *
     * @return certificate date format yyyyMM
     */
    Date getCertificateDate();

    /**
     * Returns the flashed digital signature reference.
     *
     * @return certificate signature
     */
    String getCertificateSignature();

    /**
     * Returns the English certificate type. There are 4 Dogwood types and 1
     * SCCP type. If there is not an English certificate available this will be
     * empty.
     *
     * @return
     */
    String getEnCertType();

    /**
     * FIXME: Use PaperType enumeration.
     *
     * Returns the BC Mail media colour/type. These are predefined indicators
     * which correspond to the type of certificate.
     *
     * @return one of YEDR, YEDB, YED2
     */
    String getEnCertMedia();

    /**
     * Returns the French certificate type. There are 2 Dogwood types. If there
     * is not a French certificate available this will be empty.
     *
     * @return
     */
    String getFrCertType();

    /**
     * FIXME: Use PaperType enumeration.
     *
     * Returns the BC Mail media colour/type. These are predefined indicators
     * which correspond to the type of certificate.
     *
     * @return YEDR, YEDB
     */
    String getFrCertMedia();

    /**
     * @return
     */
    Date getSccDate();

    /**
     * Returns the Dogwood eligibility flag
     *
     * @return
     */
    String getDogwoodFlag();

    /**
     * Returns the indicator used to determine whether this is an independent
     * school. A non-empty value indicates this is an independent school.
     *
     * @return An empty String or a value of 1 through 6, never null.
     */
    String getSchoolTypeIndicator();

    /**
     * Returns the school category code.
     *
     * @return The school category.
     */
    String getSchoolCategory();

    /**
     * Returns that flag to identify if the student type is a Adult. This is
     * used to select the Catalogue item.
     *
     * @return true The student was enrolled in an adult program.
     */
    boolean isAdultProgram();
}
