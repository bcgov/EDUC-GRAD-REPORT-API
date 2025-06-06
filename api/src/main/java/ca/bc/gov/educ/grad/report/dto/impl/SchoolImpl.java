/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
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

import ca.bc.gov.educ.grad.report.model.school.School;
import ca.bc.gov.educ.grad.report.model.school.SchoolStatistic;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;

/**
 * Represents information about a school that is used on a report.
 *
 * @author CGI Information Management Consultants Inc.
 */
@JsonSubTypes({
        @JsonSubTypes.Type(value = PostalAddressImpl.class)
})
public class SchoolImpl extends DistrictImpl implements School {

    private static final long serialVersionUID = 4L;

    private String schoolId = "";
    private String mincode = "";
    private String schlno = "";
    private String schoolCategoryCode = "";
    private String name = "";
    private String typeIndicator = "";
    private String typeBanner = "";
    private String signatureCode = "";
    private String phoneNumber = "";
    private String dogwoodElig = "";
    private SchoolStatistic schoolStatistic;

    public void setMincode(final String mincode) {
        this.mincode = mincode;
    }

    @Override
    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @JsonProperty("minCode")
    public String getMincode() {
        return mincode;
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

    public void setSchoolStatistic(SchoolStatistic schoolStatistic) {
        this.schoolStatistic = schoolStatistic;
    }

    @Override
    public String getMinistryCode() {
        return this.mincode;
    }

    @Override
    @JsonProperty("schoolName")
    public String getName() {
        return this.name;
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

    @Override
    public SchoolStatistic getSchoolStatistic() {
        return schoolStatistic;
    }

    public void setDogwoodElig(String dogwoodElig) {
        this.dogwoodElig = dogwoodElig;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "SchoolImpl{" +
                "mincode='" + mincode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
