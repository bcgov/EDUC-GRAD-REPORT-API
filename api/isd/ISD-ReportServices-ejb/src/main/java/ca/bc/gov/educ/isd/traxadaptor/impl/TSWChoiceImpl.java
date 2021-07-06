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
package ca.bc.gov.educ.isd.traxadaptor.impl;

import ca.bc.gov.educ.isd.eis.trax.db.PSIChoice;

import java.util.Date;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.nullSafe;

/**
 * A TRAX data object containing data from the TRAX database for the given
 * student id, year and PSI Code. The data pertains to student choices.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TSWChoiceImpl implements PSIChoice {

    // ------------------ CONSTANT(S) AND FINAL(S)
    private static final long serialVersionUID = 1L;

    // ------------------ VARIABLE(S)
    private final String studNo;
    private final String psiCode;
    private final String psiYear;
    private Character psiStatus;
    private final Date updateDt;
    private String psiName;
    private String transmissionMode;

    // ------------------ CONSTRUCTOR(S)
    /**
     * Constructor that initializes the object with a primary key using the
     * given parameters.
     *
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param psiYear Year in which the choice was made.
     */
    public TSWChoiceImpl(final String studNo, final String psiCode, final String psiYear) {
        this.studNo = studNo;
        this.psiCode = psiCode;
        this.psiYear = psiYear;
        this.updateDt = new Date();
    }

    /**
     * Constructor that initializes the object with a status and primary key
     * using the given parameters.
     *
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param psiYear Year in which the choice was made.
     * @param psiStatus Status of the PSI choice.
     */
    public TSWChoiceImpl(final String studNo, final String psiCode, final String psiYear, final Character psiStatus) {
        this.studNo = studNo;
        this.psiCode = psiCode;
        this.psiYear = psiYear;
        this.psiStatus = psiStatus;
        this.updateDt = new Date();
    }

    /**
     * Constructor that initializes the object with a status and primary key
     * using the given parameters.
     *
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param psiYear Year in which the choice was made.
     * @param psiStatus Status of the PSI choice.
     * @param psiName Name of the PSI
     * @param transmissionMode Transmission mode of the PSI (Mail, Electronic,
     * ...)
     */
    public TSWChoiceImpl(final String studNo, final String psiCode, final String psiYear, final Character psiStatus, final String psiName, final String transmissionMode) {
        this.studNo = studNo;
        this.psiCode = psiCode;
        this.psiYear = psiYear;
        this.psiStatus = psiStatus;
        this.updateDt = new Date();
        this.psiName = psiName;
        this.transmissionMode = transmissionMode;
    }

    // ------------------ GETTER(S) AND SETTER(S)
    @Override
    public String getStudNo() {
        return studNo;
    }

    @Override
    public String getPsiCode() {
        return psiCode;
    }

    @Override
    public String getPsiYear() {
        return psiYear;
    }

    @Override
    public Character getPsiStatus() {
        return nullSafe(psiStatus);
    }

    @Override
    public void setPsiStatus(Character psiStatus) {
        this.psiStatus = psiStatus;
    }

    @Override
    public Date getUpdateDt() {
        return updateDt;
    }

    @Override
    public String getPsiName() {
        return psiName;
    }

    @Override
    public String getTransmissionMode() {
        return transmissionMode;
    }

    // ------------------ METHOD(S)
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studNo != null ? studNo.hashCode() : 0);
        hash += (psiCode != null ? psiCode.hashCode() : 0);
        hash += (psiYear != null ? psiYear.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof TSWTxPSIImpl)) {
            return false;
        }
        TSWChoiceImpl other = (TSWChoiceImpl) object;
        if (!this.studNo.equals(other.studNo)) {
            return false;
        }
        if (!this.psiCode.equals(other.psiCode)) {
            return false;
        }
        return this.psiYear.equals(other.psiYear);
    }

}
