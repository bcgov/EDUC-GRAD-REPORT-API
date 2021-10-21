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
 *  File:                TswTxPsiId.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.adaptor.dao.tsw.impl;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Embeddable JPA Entity composite primary key consisting of Student number, the
 * PSI code and the TX Id of the TX PSI. This class is used as an embedded
 * primary key for {@code TswTxPsiEntity}.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TswTxPsiId implements Serializable {

    // ------------------ CONSTANT(S) AND FINAL(S)
    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = TswTxPsiId.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    // ------------------ VARIABLE(S)

    private String txId;

    private String studNo;

    private String psiCode;

    // ------------------ CONSTRUCTOR(S)
    /**
     * Empty constructor that provides a way to create an empty object.
     */
    public TswTxPsiId() {
    }

    /**
     * Constructor that initializes the object with a primary key using the
     * given parameters.
     *
     * @param txId TX id that identify the transaction
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     */
    public TswTxPsiId(String txId, String studNo, String psiCode) {
        this.studNo = studNo;
        this.psiCode = psiCode;
        this.txId = txId;
    }

    // ------------------ GETTER(S) AND SETTER(S)
    /**
     * Gets the TX Id.
     *
     * @return the TX Id.
     */
    public String getTxId() {
        return txId;
    }

    /**
     * Sets the TX Id.
     *
     * @param txId the TX Id that replaces the old one (if any).
     */
    public void setTxId(String txId) {
        this.txId = txId;
    }

    /**
     * Gest the student number.
     *
     * @return the student number
     */
    public String getStudNo() {
        return studNo;
    }

    /**
     * Sets the Student number.
     *
     * @param studNo the student number.
     */
    public void setStudNo(String studNo) {
        this.studNo = studNo;
    }

    /**
     * Gets the PSI code.
     *
     * @return the PSI code.
     */
    public String getPsiCode() {
        return psiCode;
    }

    /**
     * Sets the PSI code.
     *
     * @param psiCode the new PSI Code.
     */
    public void setPsiCode(String psiCode) {
        this.psiCode = psiCode;
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
        if (!(object instanceof TswTxPsiId)) {
            return false;
        }
        final TswTxPsiId other = (TswTxPsiId) object;
        if (!this.studNo.equals(other.studNo)) {
            return false;
        } else if (!this.psiCode.equals(other.psiCode)) {
            return false;
        } else if (!this.txId.equals(other.txId)) {
            return false;
        }
        return true;
    }
}
