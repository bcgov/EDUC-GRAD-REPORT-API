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

import ca.bc.gov.educ.isd.eis.trax.db.TSWStud;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

/**
 * A TRAX data object containing data from the TRAX database for the given
 * student id, year and PSI Code. The data pertains to student choices.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TSWStudImpl implements TSWStud, Serializable {

    // ------------------ CONSTANT(S) AND FINAL(S)
    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = TSWTxPSIImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    // ------------------ VARIABLE(S)
    private final String studNo;
    private final String psiCode;
    private final String psiYear;
    private Character psiStatus;
    private final Date dateEntered;

    // ------------------ CONSTRUCTOR(S)
    /**
     * Constructor that initializes the object with a primary key using the
     * given parameters.
     *
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param psiYear Year in which the choice was made.
     */
    public TSWStudImpl(final String studNo, final String psiCode, final String psiYear) {
        this.studNo = studNo;
        this.psiCode = psiCode;
        this.psiYear = psiYear;
        this.dateEntered = new Date();
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
    public TSWStudImpl(final String studNo, final String psiCode, final String psiYear, final Character psiStatus) {
        this.studNo = studNo;
        this.psiCode = psiCode;
        this.psiYear = psiYear;
        this.psiStatus = psiStatus;
        this.dateEntered = new Date();
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
        return psiStatus;
    }

    @Override
    public void setPsiStatus(Character psiStatus) {
        this.psiStatus = psiStatus;
    }

    @Override
    public Date getDateEntered() {
        return dateEntered;
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
        TSWStudImpl other = (TSWStudImpl) object;
        if (!this.studNo.equals(other.studNo)) {
            return false;
        }
        if (!this.psiCode.equals(other.psiCode)) {
            return false;
        }
        return this.psiYear.equals(other.psiYear);
    }
}
