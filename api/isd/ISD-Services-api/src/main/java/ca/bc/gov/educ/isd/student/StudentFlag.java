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
 *  File:                $Id:: StudentFlag.java 8289 2017-09-26 23:04:07Z CGOM#$
 *  Date of Last Commit: $Date:: 2017-09-26 16:04:07 -0700 (Tue, 26 Sep 2017)  $
 *  Revision Number:     $Rev:: 8289                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.student;

/**
 * Isolates various boolean settings from the application. This is analogous to
 * a logical layer separated from the physical layer. Logically, the code uses,
 * for example, a TERMINATED flag, which translates physically as character code
 * 'T'. From the perspective of a unit test, the character code is an
 * implementation detail that needn't be exposed.
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum StudentFlag {

    /**
     * Use to set the Dogwood flag.
     */
    STUDENT_DOGWOOD,
    /**
     * Use to set the student graduated with honours flag.
     */
    STUDENT_HONOURS,
    /**
     * Use to set the student status to 'A' for active.
     */
    STUDENT_STATUS_ACTIVE,
    /**
     * Use to set the student status to 'D' for deceased.
     */
    STUDENT_STATUS_DECEASED,
    /**
     * Use to set the student status to 'M' for merged.
     */
    STUDENT_STATUS_MERGED,
    /**
     * Use to set the student status to 'T' for terminated.
     */
    STUDENT_STATUS_TERMINATED,
    /**
     * Use to set the student eligibility for an English certificate
     * (ENGLISH_CERT = 'E').
     */
    STUDENT_CERTIFICATE_ENGLISH,
    /**
     * Use to set the student eligibility for a French immersion certificate
     * (FRENCH_CERT = 'F').
     */
    STUDENT_CERTIFICATE_FRENCH,
    /**
     * Use to set the student eligibility for a Francophone certificate
     * (FRENCH_CERT = 'S').
     */
    STUDENT_CERTIFICATE_FRANCOPHONE,
}
