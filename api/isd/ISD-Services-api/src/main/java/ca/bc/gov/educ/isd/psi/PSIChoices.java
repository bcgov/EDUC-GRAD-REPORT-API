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
 *  File:                PSIChoices.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.psi;

import ca.bc.gov.educ.isd.common.DomainEntity;
import java.util.Date;

/**
 * Represents a list of post-secondary institutions that a user has elected to
 * send their transcripts to.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PSIChoices extends DomainEntity {

    /**
     * FIXME: This should return a student interface instance instead.
     *
     * @return
     */
    String getStudNo();

    /**
     * FIXME: This should return a PSI instance, which would obviate the need
     * for the PSIRegistryServiceBean.
     *
     * @return
     */
    String getPsiCode();

    Date getProcessDate();

    Character getStatus();

    Date getSyncDate();
}
