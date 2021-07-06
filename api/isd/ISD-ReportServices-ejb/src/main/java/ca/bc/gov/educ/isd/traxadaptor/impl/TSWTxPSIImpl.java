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

import ca.bc.gov.educ.isd.eis.trax.db.TSWTxPSI;

import java.util.Date;
import java.util.logging.Logger;

/**
 * A TRAX data object containing data from the TRAX database for the given
 * student id, tx id and PSI Code. The data pertains to TX PSI.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TSWTxPSIImpl implements TSWTxPSI {

    // ------------------ CONSTANT(S) AND FINAL(S)
    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = TSWTxPSIImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    // ------------------ VARIABLE(S)
    private final String txId;
    private final String studNo;
    private final String psiCode;
    private Character psiStatus;
    private final Date processDate;

    // ------------------ CONSTRUCTOR(S)
    /**
     * Constructor that initializes the object with a primary key using the
     * given parameters.
     *
     * @param txId TX id that identify the transaction
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     */
    public TSWTxPSIImpl(final String txId, final String studNo, final String psiCode) {
        this.txId = txId;
        this.studNo = studNo;
        this.psiCode = psiCode;
        this.processDate = new Date();
    }

    /**
     * Constructor that initializes the object with a status and primary key
     * using the given parameters.
     *
     * @param txId TX id that identify the transaction
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param psiStatus Status of the TX PSI.
     */
    public TSWTxPSIImpl(final String txId, final String studNo, final String psiCode, final Character psiStatus) {
        this.txId = txId;
        this.studNo = studNo;
        this.psiCode = psiCode;
        this.psiStatus = psiStatus;
        this.processDate = new Date();
    }

    // ------------------ GETTER(S) AND SETTER(S)
    @Override
    public String getTxId() {
        return txId;
    }

    @Override
    public String getStudNo() {
        return studNo;
    }

    @Override
    public String getPsiCode() {
        return psiCode;
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
    public Date getProcessDate() {
        return processDate;
    }

    // ------------------ METHOD(S)
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studNo != null ? studNo.hashCode() : 0);
        hash += (psiCode != null ? psiCode.hashCode() : 0);
        hash += (txId != null ? txId.hashCode() : 0);
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
        TSWTxPSIImpl other = (TSWTxPSIImpl) object;
        if (!this.studNo.equals(other.studNo)) {
            return false;
        }
        if (!this.psiCode.equals(other.psiCode)) {
            return false;
        }
        return this.txId.equals(other.txId);
    }
}
