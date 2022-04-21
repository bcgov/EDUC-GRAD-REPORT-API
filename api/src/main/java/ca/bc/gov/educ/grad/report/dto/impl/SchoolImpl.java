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
package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.common.party.address.PostalAddress;
import ca.bc.gov.educ.grad.report.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.report.model.school.School;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Represents information about a school that is used on a report.
 *
 * @author CGI Information Management Consultants Inc.
 */

public class SchoolImpl extends AbstractDomainEntity implements School {

    private static final long serialVersionUID = 4L;

    private PostalAddress address = new PostalAddressImpl();
    private String mincode = "";
    private String distno = "";
    private String schlno = "";
    private String schoolCategoryCode = "";
    private String name = "";
    private String typeIndicator = "";
    private String typeBanner = "";
    private String signatureCode = "";
    private String phoneNumber = "";
    private String dogwoodElig = "";

    public void setAddress(final PostalAddress address) {
        this.address = address;
    }

    public void setMincode(final String mincode) {
        this.mincode = mincode;
    }

    public PostalAddress getAddress() {
        return address;
    }

    public String getMincode() {
        return mincode;
    }

    public String getDistno() {
        return distno;
    }

    public void setDistno(String distno) {
        this.distno = distno;
    }

    public String getSchlno() {
        return schlno;
    }

    public void setSchlno(String schlno) {
        this.schlno = schlno;
    }

    public String getSchoolCategoryCode() {
        return schoolCategoryCode;
    }

    public void setSchoolCategoryCode(String schoolCategoryCode) {
        this.schoolCategoryCode = schoolCategoryCode;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setTypeIndicator(final String typeIndicator) {
        this.typeIndicator = typeIndicator;
    }

    public void setTypeBanner(final String typeBanner) {
        this.typeBanner = typeBanner;
    }

    public void setSignatureCode(final String code) {
        this.signatureCode = code;
    }

    @Override
    public String getMinistryCode() {
        return this.mincode;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    @JsonProperty("address")
    @JsonDeserialize(as = PostalAddressImpl.class)
    public PostalAddress getPostalAddress() {
        return this.address;
    }

    @Override
    public String getTypeIndicator() {
        return this.typeIndicator;
    }

    /**
     * Returns true iff the type indicator is not empty.
     *
     * @return false The type indicator contains an independent school code.
     */
    @Override
    public Boolean isIndependent() {
        return !getTypeIndicator().isEmpty();
    }

    @Override
    public String getTypeBanner() {
        return this.typeBanner;
    }

    @Override
    public String getSignatureCode() {
        return this.signatureCode;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDogwoodElig() {
        return dogwoodElig;
    }

    public void setDogwoodElig(String dogwoodElig) {
        this.dogwoodElig = dogwoodElig;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
