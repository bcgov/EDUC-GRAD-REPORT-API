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
 *  File:                GraduationData.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.transcript;

import ca.bc.gov.educ.isd.common.DomainEntity;

import java.util.Date;
import java.util.List;

/**
 * TODO: Rename to GraduationRequirements?
 *
 * This interface represents graduation data for a student used in the
 * Compilation of an XML transcript.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface GraduationData extends DomainEntity {

    /**
     * Returns true iff the student has graduated. When this returns false, the
     * graduation date will not contain valid data.
     *
     * @return false The student has not graduated.
     */
    boolean hasGraduated();

    /**
     * Get the date the student graduated. This will return the current date if
     * the user has not graduated.
     *
     * @return Date of graduation.
     */
    Date getGraduationDate();

    /**
     * Get the truncated date the student graduated in the form of yyyy/MM. This will return the current date if
     * the user has not graduated.
     *
     * @return Date of graduation.
     */
    String getTruncatedGraduationDate();

    /**
     * Get the honours flag.
     *
     * @return
     */
    Boolean getHonorsFlag();

    /**
     * Get the dogwood flag
     *
     * @return
     */
    Boolean getDogwoodFlag();

    /**
     * Get the total credits used for graduation
     *
     * @return
     */
    String getTotalCreditsUsedForGrad();

    /**
     * Get the list of program codes.
     *
     * @return
     */
    List<String> getProgramCodes();

    /**
     * Get the list of program code Names.
     *
     * @return
     */
    List<String> getProgramNames();

}
