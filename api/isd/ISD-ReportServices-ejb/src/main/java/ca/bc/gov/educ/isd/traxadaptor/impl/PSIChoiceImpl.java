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

/**
 * A ISD data object containing data from the ISD database for the given student
 * id, year and PSI Code. The data pertains to a single student PSI choice for
 * sending their transcript (application).
 *
 * @author CGI Information Management Consultants Inc.
 */
public class PSIChoiceImpl implements PSIChoice {

    // ------------------ CONSTANT(S) AND FINAL(S)
    private static final long serialVersionUID = 2L;

    // ------------------ VARIABLE(S)
    private final String studNo;
    private final String psiCode;
    private final String psiYear;
    private Character status;
    private Date updateDt = new Date();

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
    public PSIChoiceImpl(final String studNo, final String psiCode, final String psiYear) {
        this.studNo = trimSafe(studNo);
        this.psiCode = trimSafe(psiCode);
        this.psiYear = trimSafe(psiYear);
    }

    /**
     * Constructor that initializes the object with a status and primary key
     * using the given parameters.
     *
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param psiYear Year in which the choice was made.
     * @param status Status of the PSI choice.
     */
    public PSIChoiceImpl(final String studNo, final String psiCode, final String psiYear, final Character status) {
        this(studNo, psiCode, psiYear);
        this.status = status;
    }

    /**
     * Constructor that initializes the object with a status and primary key
     * using the given parameters.
     *
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param psiYear Year in which the choice was made.
     * @param status Status of the PSI choice.
     * @param psiName
     * @param transmissionMode
     */
    public PSIChoiceImpl(final String studNo, final String psiCode, final String psiYear, final Character status, final String psiName, final String transmissionMode) {
        this(studNo, psiCode, psiYear, status);
        this.psiName = trimSafe(psiName);
        this.transmissionMode = trimSafe(transmissionMode);
    }

    /**
     * Constructor that initializes the object with an update date, status and
     * primary key using the given parameters.
     *
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param psiYear Year in which the choice was made.
     * @param status Status of the PSI choice.
     * @param updateDt when the object was created.
     */
    public PSIChoiceImpl(final String studNo, final String psiCode, final String psiYear, final Character status, final Date updateDt) {
        this(studNo, psiCode, psiYear, status);
        this.updateDt = updateDt;
    }

    /**
     * Constructor that initializes the object with an update date, status and
     * primary key using the given parameters.
     *
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param psiYear Year in which the choice was made.
     * @param status Status of the PSI choice.
     * @param updateDt when the object was created.
     * @param psiName
     * @param transmissionMode
     */
    public PSIChoiceImpl(final String studNo, final String psiCode, final String psiYear, final Character status, final Date updateDt, final String psiName, final String transmissionMode) {
        this.studNo = studNo;
        this.psiCode = psiCode;
        this.psiYear = psiYear;
        this.status = status;
        this.updateDt = updateDt;
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
        return status;
    }

    @Override
    public void setPsiStatus(final Character status) {
        this.status = status;
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

    /**
     * Returns a valid string reference.
     *
     * @param s The string to trim, if not null.
     * @return A non-null String without extraneous leading or trailing spaces.
     */
    private String trimSafe(final String s) {
        return s == null ? "" : s.trim();
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
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof PSIChoiceImpl)) {
            return false;
        }
        final PSIChoiceImpl other = (PSIChoiceImpl) object;
        if (!this.studNo.equals(other.studNo)) {
            return false;
        }
        if (!this.psiCode.equals(other.psiCode)) {
            return false;
        }
        return this.psiYear.equals(other.psiYear);
    }
}
