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
package ca.bc.gov.educ.isd.eis.trax.db;

import java.io.Serializable;

/**
 * Represents the tab school entity attributes used by StudentDemographicImpl
 * and ScholarshipStudentImpl.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TabSchool extends Serializable {

    String getMincode();

    String getSchlName();

    String getAddress1();

    String getAddress2();

    String getCity();

    String getProvCode();

    String getPostal();

    /**
     * Returns the school's district signature number. This is used when old
     * schools are amalgamated and their signatories become obsolete. If
     * present, this value is used for the school signatory, regardless of the
     * student's MINCODE or MINCODE_GRAD values.
     *
     * @return The empty string or a valid school district number (never null).
     */
    String getSignatureDistno();

    Character getXcriptElig();

    String getPhone();

    /**
     * Helps determine whether this is an independent school or not. An
     * non-independent school returns a space character. Any other character
     * denotes an independent school, which has a corresponding banner type.
     *
     * @return A value to indicate the school type (independent or not).
     */
    Character getSchlIndType();

    Character getDogwoodElig();
}
