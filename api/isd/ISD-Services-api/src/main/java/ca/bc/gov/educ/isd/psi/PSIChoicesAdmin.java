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
 *  File:                PSIChoicesAdmin.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.psi;

import java.util.Date;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PSIChoicesAdmin extends PSIChoices {

    void setId(final Long id);

    void setStudNo(final String studNo);

    void setPsiCode(final String psiCode);

    void setProcessDate(final Date processDate);

    void setStatus(final Character status);

    void setSyncDate(Date syncDate);
}
