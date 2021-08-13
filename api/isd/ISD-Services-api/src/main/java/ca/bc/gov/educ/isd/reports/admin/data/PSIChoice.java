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
 *  File:                $Id:: PSIChoice.java 4514 2016-10-20 18:38:12Z cprin#$
 *  Date of Last Commit: $Date:: 2016-10-20 11:38:12 -0700 (Thu, 20 Oct 2016) $
 *  Revision Number:     $Rev:: 4514                                          $
 *  Last Commit by:      $Author:: cprince                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.admin.data;

import java.util.Date;

/**
 * Defines the accessors for admin report data.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PSIChoice extends AdminReportDAO {

    /**
     * Returns the value for transmitted.
     *
     * @return Used to group the PSI Choices by month.
     */
    public Date getTransmitted();

    /**
     * Sets the value for transmitted.
     *
     * @param transmitted Used to group the PSI Choices by month.
     */
    void setTransmitted(Date transmitted);

    /**
     * Returns the value for name.
     *
     * @return Name of the Post-Secondary Institution (PSI).
     */
    String getName();

    /**
     * Sets the value for name.
     *
     * @param name Name of the Post-Secondary Institution (PSI).
     */
    void setName(String name);

    /**
     * Returns the value for code.
     *
     * @return PSI code.
     */
    String getCode();

    /**
     * Sets the value for code.
     *
     * @param code PSI code.
     */
    void setCode(String code);

    /**
     * Returns the value for transmission.
     *
     * @return How the transcripts were transmitted.
     */
    String getTransmission();

    /**
     * Sets the value for transmission.
     *
     * @param transmission How the transcripts were transmitted.
     */
    void setTransmission(String transmission);

    /**
     * Returns the value for tally.
     *
     * @return Total number of transcripts sent to the PSI.
     */
    public Integer getTally();

    /**
     * Sets the value for tally.
     *
     * @param tally Total number of transcripts sent to the PSI.
     */
    void setTally(Integer tally);
}
