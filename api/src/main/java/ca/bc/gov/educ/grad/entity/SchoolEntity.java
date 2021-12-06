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
package ca.bc.gov.educ.grad.entity;

import ca.bc.gov.educ.grad.model.school.SchoolMaster;

import static ca.bc.gov.educ.grad.model.common.support.VerifyUtils.nullSafe;
import static ca.bc.gov.educ.grad.model.common.support.VerifyUtils.trimSafe;

/**
 * An auto generated entity for the TAB_SCHOOL_VW view which is a view created
 * in the ISD database and linked to the TRAX database tab_school table using
 * the database link. JPA access is on attributes directly. The attributes in
 * this list are used to fulfill the data requests for the Transcript, XML
 * Transcript, Student Demographics, Grad Certificate, and Scholarship services.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class SchoolEntity implements SchoolMaster {

    private static final long serialVersionUID = 2L;

    private String mincode;
    private String schlName;
    private String address1;
    private String address2;
    private String city;
    private String provCode;
    private String postal;
    private String signatureDistno;
    private Character xcriptElig;
    private String phone;
    private Character schlIndType;
    private Character dogwoodElig;

    public SchoolEntity() {
    }

    public SchoolEntity(
            String mincode,
            String schlName,
            String address1,
            String address2,
            String city,
            String provCode,
            String postal,
            String signatureDistno,
            Character xcriptElig,
            String phone,
            Character schlIndType,
            Character dogwoodElig
    ) {
        this.mincode = mincode;
        this.schlName = schlName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.provCode = provCode;
        this.postal = postal;
        this.signatureDistno = signatureDistno;
        this.xcriptElig = xcriptElig;
        this.phone = phone;
        this.schlIndType = schlIndType;
        this.dogwoodElig = dogwoodElig;
    }

    @Override
    public String getMincode() {
        return this.mincode;
    }

    @Override
    public String getSchlName() {
        return this.schlName;
    }

    @Override
    public String getAddress1() {
        return this.address1;
    }

    @Override
    public String getAddress2() {
        return this.address2;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    @Override
    public String getProvCode() {
        return this.provCode;
    }

    @Override
    public String getPostal() {
        return this.postal;
    }

    /**
     * @return The school district signature code, or an empty string, never
     * null.
     * @inheritDoc
     */
    @Override
    public String getSignatureDistno() {
        return trimSafe(this.signatureDistno);
    }

    @Override
    public Character getXcriptElig() {
        return this.xcriptElig;
    }

    @Override
    public String getPhone() {
        return this.phone;
    }

    /**
     * Helps determine whether this is an independent school or not. An
     * non-independent school returns a space character. Any other character
     * denotes an independent school, which has a corresponding banner type.
     *
     * @return A value to indicate the school type (independent or not).
     */
    @Override
    public Character getSchlIndType() {
        return nullSafe(this.schlIndType);
    }

    @Override
    public Character getDogwoodElig() {
        return this.dogwoodElig;
    }

}
