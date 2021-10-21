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
package ca.bc.gov.educ.isd.adaptor.impl;

import ca.bc.gov.educ.isd.eis.trax.db.Scholarship;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A TRAX data object containing data from the TRAX database for the given
 * student PEN. The data is for a scholarship which a student has been awarded
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ScholarshipImpl implements Scholarship, Serializable {

    private static final long serialVersionUID = 2L;
    private static final String CLASSNAME = ScholarshipImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private String pen;

    // scholarship info
    private String scholarshipName = "";
    private Date scholarshipExpiry = new Date(0L);
    private String redeemedFlag = "";
    private Integer amount = 0;

    private Character code = ' ';
    private Integer awardYear = 0;

    public ScholarshipImpl() {
    }

    /**
     * Constructor method. Used by JPQL to create an unmanaged object with
     * fields queried from multiple managed entities.
     *
     * @param pen
     * @param awardCode
     * @param awardYear
     * @param awardCashDate
     * @param amount
     */
    public ScholarshipImpl(
            final String pen,
            final Character awardCode,
            final Integer awardYear,
            final Date awardCashDate,
            final Integer amount) {
        this.pen = pen;
        this.code = (awardCode == null ? ' ' : awardCode);
        this.awardYear = (awardYear == null) ? 0 : awardYear;

        try {
            final ScholarshipName scholarship
                    = ScholarshipName.getValue(awardCode, awardYear);

            this.scholarshipName = scholarship.toString();
            this.scholarshipExpiry = scholarship.toExpiryDate(awardYear);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            this.scholarshipName = "Unknown Scholarship Code: " + awardCode;
            this.scholarshipExpiry = null;
        }

        this.redeemedFlag = determineRedeemed(this.code, awardCashDate);
        this.amount = (amount == null ? 0 : amount);
    }

    @Override
    public String getPen() {
        return pen;
    }

    @Override
    public String getScholarshipName() {
        return scholarshipName;
    }

    @Override
    public Date getScholarshipExpiry() {
        return scholarshipExpiry;
    }

    @Override
    public String getRedeemedFlag() {
        return redeemedFlag;
    }

    @Override
    public Integer getAmount() {
        return amount;
    }

    @Override
    public Character getCode() {
        return this.code;
    }

    @Override
    public Integer getAwardYear() {
        return awardYear;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.pen != null ? this.pen.hashCode() : 0);
        hash = 47 * hash + (this.scholarshipName != null ? this.scholarshipName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ScholarshipImpl other = (ScholarshipImpl) obj;
        if ((this.pen == null) ? (other.pen != null) : !this.pen.equals(other.pen)) {
            return false;
        }
        return !((this.scholarshipName == null)
                ? (other.scholarshipName != null)
                : !this.scholarshipName.equals(other.scholarshipName));
    }

    /**
     * determine what the redeemed flag should be set to based on the award code
     * and the award cash date attributes. If the award code = 'A' then the
     * redeemed flag = "N/A" if award cash date is NULL or 0 then the redeemed
     * flag = "N" if the award cash date is > 0 then redeemed flag = "Y".
     *
     * @param code
     * @param cashDate
     * @return
     */
    private String determineRedeemed(final Character code, final Date cashDate) {
        String result;

        if (cashDate == null || cashDate.getTime() == 0) {
            result = "N";
        } else {
            result = "Y";
        }

        if (code.equals('A')) {
            result = "N/A";
        }

        return result;
    }
}
