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
 *  File:                Scholarship.java
 *  Date of Last Commit: $Date::                   $
 *  Revision Number:     $Rev::                    $
 *  Last Commit by:      $Author::                 $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.trax.db;

import java.util.Date;

/**
 * Defines the public methods to access the Scholarship object containing
 * information on the scholarships which the student received
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ScholarshipStudent extends TRAXData {

    /**
     * get the PEN which the data corresponds to
     *
     * @return PEN
     */
    String getPen();

    /**
     * Get the date that the student was born.
     *
     * @return Student's birthdate.
     */
    Date getBirthdate();

    /**
     * get the student's first name
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
     * get the student's last name
     *
     * @return last name
     */
    String getLastName();

    /**
     * get the student's address field #1.
     *
     * @return first student address field
     */
    String getStudentAddress1();

    /**
     * get the student's address field #2.
     *
     * @return second student address field
     */
    String getStudentAddress2();

    /**
     * get the student's address city.
     *
     * @return address city
     */
    String getStudentCity();

    /**
     * get the student's address province name.
     *
     * @return address province
     */
    String getStudentProv();

    /**
     * get the student address postal code.
     *
     * @return postal code
     */
    String getStudentPostalCode();

    /**
     * get the secondary school name.
     *
     * @return school name
     */
    String getSchoolName();

    /**
     * get the secondary school street address.
     *
     * @return school street address
     */
    String getSchoolStreet();

    /**
     * get the secondary school city.
     *
     * @return school city
     */
    String getSchoolCity();

    /**
     * get the secondary school province name.
     *
     * @return province
     */
    String getSchoolProv();

    /**
     * get the secondary school postal code.
     *
     * @return postal code
     */
    String getSchoolPostalCode();

    /**
     * get ministry code for the school.
     *
     * @return school mincode
     */
    String getMincode();

    /**
     * get the second line of the school street address.
     *
     * @return school street address
     */
    String getSchoolStreet2();
}
