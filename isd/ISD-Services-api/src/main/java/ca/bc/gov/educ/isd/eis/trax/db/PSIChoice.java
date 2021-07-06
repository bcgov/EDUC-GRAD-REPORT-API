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

import java.util.Date;

/**
 * Represents a single PSI that a student has chosen to express desire to
 * attend. This allows the system to determine what institution shall receive a
 * student's transcript.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PSIChoice extends TRAXData {

    /**
     * Gest the student number.
     *
     * @return the student number
     */
    String getStudNo();

    /**
     * Gets the PSI code.
     *
     * @return the PSI code.
     */
    String getPsiCode();

    /**
     * Gets the Year when the Choice was made.
     *
     * @return the year when the Choice was made.
     */
    String getPsiYear();

    /**
     * Gets the status of the PSI Choice object.
     *
     * @return the status of the PSI Choice object.
     */
    Character getPsiStatus();

    /**
     * Gets the creation/update Date of the object
     *
     * @return the creation date of the object.
     */
    Date getUpdateDt();

    String getPsiName();

    String getTransmissionMode();

    /**
     * Sets the status of the PSI Choice object
     *
     * @param psiStatus the new status of the PSI Choice object.
     * @deprecated Create an Admin interface.
     */
    void setPsiStatus(Character psiStatus);

}
