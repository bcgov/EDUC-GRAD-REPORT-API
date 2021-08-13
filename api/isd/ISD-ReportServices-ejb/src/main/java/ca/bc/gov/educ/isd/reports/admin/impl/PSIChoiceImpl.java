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
package ca.bc.gov.educ.isd.reports.admin.impl;

import ca.bc.gov.educ.isd.reports.admin.data.PSIChoice;

import java.util.Date;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.psi.TransmissionMode.PAPER;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class PSIChoiceImpl implements PSIChoice {

    private static final long serialVersionUID = 5L;

    private static final String CLASSNAME = PSIChoiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private Date transmitted;
    private String name;
    private String code;
    private String transmission;
    private Integer tally;

    @Override
    public void setTransmitted(final Date transmitted) {
        this.transmitted = transmitted;
    }

    @Override
    public Date getTransmitted() {
        return this.transmitted;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setCode(final String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Defaults to PAPER's description if no value is provided.
     *
     * @param transmission Transmission mode for a PSI.
     */
    @Override
    public void setTransmission(String transmission) {
        if (transmission == null || "".equals(transmission)) {
            transmission = PAPER.getDescription();
        }

        this.transmission = transmission;
    }

    @Override
    public String getTransmission() {
        return this.transmission;
    }

    @Override
    public void setTally(final Integer tally) {
        this.tally = tally;
    }

    @Override
    public Integer getTally() {
        return this.tally;
    }
}
