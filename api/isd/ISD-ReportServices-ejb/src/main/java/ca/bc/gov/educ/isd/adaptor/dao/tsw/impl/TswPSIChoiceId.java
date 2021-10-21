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
 *  File:                TswPSIChoicesId.java
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
 * PSI code and the year of the PSI Choice. This class is used as an embedded
 * primary key for {@code PSIChoice}.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TswPSIChoiceId implements Serializable {

    // ------------------ CONSTANT(S) AND FINAL(S)
    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = TswPSIChoiceId.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private String studNo;

    private String psiCode;

    private String psiYear;

    // ------------------ CONSTRUCTOR(S)
    /**
     * Empty constructor that provides a way to create an empty object.
     */
    public TswPSIChoiceId() {
    }

    /**
     * Constructor that initializes the object with a primary key using the
     * given parameters.
     *
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param psiYear Year in which the choice was made.
     */
    public TswPSIChoiceId(String studNo, String psiCode, String psiYear) {
        this.studNo = studNo;
        this.psiCode = psiCode;
        this.psiYear = psiYear;
    }

    // ------------------ GETTER(S) AND SETTER(S)
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

    /**
     * Gets the Year when the Choice was made.
     *
     * @return the year when the Choice was made.
     */
    public String getPsiYear() {
        return psiYear;
    }

    /**
     * Sets the year when the choice was made.
     *
     * @param psiYear the year when the choice was made.
     */
    public void setPsiYear(String psiYear) {
        this.psiYear = psiYear;
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
        if (!(object instanceof TswPSIChoiceId)) {
            return false;
        }
        final TswPSIChoiceId other = (TswPSIChoiceId) object;
        if (!this.studNo.equals(other.studNo)) {
            return false;
        }
        if (!this.psiCode.equals(other.psiCode)) {
            return false;
        }
        return this.psiYear.equals(other.psiYear);
    }

}
