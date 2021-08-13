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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.scholarship.impl;

import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.isd.scholarship.Scholarship;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ca.bc.gov.educ.isd.common.Constants.DATE_YEAR;

/**
 * A student scholarship.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ScholarshipImpl extends AbstractDomainEntity
        implements Scholarship, Serializable, Comparable<Scholarship> {

    private static final long serialVersionUID = 1L;

    private String name = "";
    private Date expiry = new Date(0L);
    private String redeemed = "";
    private Integer amount = 0;
    private String code = "";
    private Date awardYear = new Date(0L);

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Date getExpiry() {
        return this.expiry;
    }

    @Override
    public String getRedeemed() {
        return this.redeemed;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }

    /**
     * set the scholarship name. This should only be set from TRAX as TRAX
     * adaptor * applies business logic to derive this name.
     * <p>
     * <p>
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set date when the scholarship expires. This should only be set from TRAX
     * as * TRAX adaptor applies business logic to derive this date.
     * <p>
     * @param expiry
     */
    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    /**
     * set a flag indicating if the scholarship has been redeemed. This should
     * only be set from TRAX as TRAX adaptor applies business logic to derive.
     * <p>
     * @param redeemed Y, N, N/A
     */
    public void setRedeemed(String redeemed) {
        this.redeemed = redeemed;
    }

    /**
     * set the amount of the scholarship.
     * <p>
     * @param amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(Scholarship o) {
        SimpleDateFormat yearFormat = new SimpleDateFormat(DATE_YEAR);
        String thisYear = yearFormat.format(awardYear);
        String otherYear = yearFormat.format(o.getYearAwarded());
        int yearComp = thisYear.compareTo(otherYear);

        String thisCode = String.valueOf(this.code);
        String otherCode = String.valueOf(o.getCode());
        int codeComp = thisCode.compareTo(otherCode);

        return (yearComp != 0 ? yearComp : codeComp);
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public Date getYearAwarded() {
        return this.awardYear;
    }

    public void setYearAwarded(Date year) {
        this.awardYear = year;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
