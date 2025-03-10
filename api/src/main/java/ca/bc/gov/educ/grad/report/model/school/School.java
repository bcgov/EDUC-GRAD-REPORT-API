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
 *  File:                $Id:: School.java 6312 2017-02-25 00:29:23Z DAJARVIS  $
 *  Date of Last Commit: $Date:: 2017-02-24 16:29:23 -0800 (Fri, 24 Feb 2017)  $
 *  Revision Number:     $Rev:: 6312                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.school;

import ca.bc.gov.educ.grad.report.model.district.District;

/**
 * Represents a school located within Canada.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface School extends District {

    /**
     * Returns the schoolId the Institute uses to uniquely identify this school.
     *
     * @return schoolId
     */
    String getSchoolId();

    /**
     * Returns the code the Ministry uses to uniquely identify this school.
     *
     * @return MINCODE ministry code
     */
    String getMinistryCode();

    /**
     * Returns a two-digit number that represents a school district's
     * superintendent's signature code. This is derived from the Ministry code.
     *
     * @return The 2nd and 3rd digits from the Ministry code.
     */
    String getSignatureCode();

    /**
     * Returns the independent school type indicator (anything other than a
     * space or empty means the school is independent).
     *
     * @return A non-null String, possibly empty.
     */
    String getTypeIndicator();

    /**
     * Returns true if this is an independent school.
     *
     * @return false The type indicator is blank (or null).
     */
    Boolean isIndependent();

    /**
     * Returns the text description for the independent school type.
     *
     * @return A non-null String, possibly empty.
     */
    String getTypeBanner();

    /**
     * Gets schlno.
     *
     * @return the schlno
     */
    String getSchlno() ;

    /**
     * Gets school category code.
     *
     * @return the school category code
     */
    String getSchoolCategoryCode();

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    String getPhoneNumber();

    /**
     * Gets dogwood elig.
     *
     * @return the dogwood elig
     */
    String getDogwoodElig();

    /**
     * Gets school stat elig.
     *
     * @return the school stat
     */
    SchoolStatistic getSchoolStatistic();
}
