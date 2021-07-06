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
 *  File:                $Id:: StudentAdmin.java 8289 2017-09-26 23:04:07Z CGO#$
 *  Date of Last Commit: $Date:: 2017-09-26 16:04:07 -0700 (Tue, 26 Sep 2017)  $
 *  Revision Number:     $Rev:: 8289                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.student;

import ca.bc.gov.educ.isd.common.party.address.MailingAddress;
import ca.bc.gov.educ.isd.grad.GraduationProgramCode;
import java.util.Calendar;

/**
 * Provides a number of writable methods that allows implementations to populate
 * student instances with data. These data-infused instances can be used to
 * search for students based on the filled criteria. This is primarily used by
 * unit tests to find student instances without having to find students by PEN,
 * thereby eliminating a dependency on a particular database and data that may
 * change (i.e., the PEN).
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentAdmin extends Student {

    /**
     * @param firstName The student's given name.
     */
    void setFirstName(String firstName);

    /**
     * @param middleName The student's middle name(s).
     */
    void setMiddleName(String middleName);

    /**
     * @param lastName The student's surname.
     */
    void setLastName(String lastName);

    /**
     * @param birthdate Date the student was born.
     */
    void setBirthdate(Calendar birthdate);

    /**
     * @param mailingAddress Address1, address2, city, prov code, postal, and
     * cntry_code.
     */
    void setAddress(MailingAddress mailingAddress);

    /**
     * Sets year and month that the student graduated (from high school).
     *
     * @param graduationDate Date a certificate was issued for graduation.
     */
    void setGraduationDate(Calendar graduationDate);

    /**
     * Sets year and month that the student graduated (from SCC program).
     *
     * @param sccDate SCCP graduation date.
     */
    void setSccDate(Calendar sccDate);

    /**
     * @param grade Student's current grade.
     */
    void setGrade(String grade);

    /**
     * Set the graduation program associated with the student.
     *
     * @param graduationProgramCode Student's graduation program code.
     */
    void setGraduationProgramCode(GraduationProgramCode graduationProgramCode);

    /**
     * Set the code reference for the student's current secondary school.
     *
     * @param mincode The ministry's reference code.
     */
    void setMincode(String mincode);

    /**
     * Sets school mincode from which the student graduated.
     *
     * @param mincode A school's ministry code.
     */
    void setGradMincode(String mincode);

    /**
     * Sets one or more flags that correspond to various student states. If the
     * flags conflict, the last flag passed will be used.
     *
     * @param flags List of flags to use that determine the student state.
     */
    void setFlags(StudentFlag... flags);
}
