/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 * 
 *  Revision Control Information
 *  File:                PSISelection.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.psiselections;

import java.util.Date;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PSISelection {

    /**
     * Gets the student PEN.
     *
     * @return the student number
     */
    String getPEN();

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

    /**
     * Name of the PSI.
     *
     * @return
     */
    String getPsiName();

    /**
     * The preferred transcript transmission mode of the PSI.
     *
     * @return
     */
    String getTransmissionMode();

}
