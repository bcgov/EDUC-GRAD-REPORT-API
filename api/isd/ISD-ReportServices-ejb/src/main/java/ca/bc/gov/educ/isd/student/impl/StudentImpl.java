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
package ca.bc.gov.educ.isd.student.impl;

import ca.bc.gov.educ.isd.codes.SignatureBlockType;
import ca.bc.gov.educ.isd.common.party.address.PostalAddress;
import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.isd.student.PersonalEducationNumber;
import ca.bc.gov.educ.isd.student.Student;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class StudentImpl extends AbstractDomainEntity implements Student {

    private static final long serialVersionUID = 3L;

    private PersonalEducationNumber pen = null;
    private Date birthdate = new Date(0L);
    private PostalAddress address = new PostalAddressImpl();
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private String grade = "";
    private Character gender = ' ';
    private Date sccDate;
    private String mincodeGrad;
    private String englishCert;
    private String frenchCert;
    private String studStatus = "";
    private Map<String, SignatureBlockType> signatureBlockTypes;

    @Override
    @JsonDeserialize(as = PersonalEducationNumberObject.class)
    public PersonalEducationNumber getPen() {
        return pen;
    }

    @Override
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    @JsonProperty("address")
    @JsonDeserialize(as = PostalAddressImpl.class)
    public PostalAddress getCurrentMailingAddress() {
        return address;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String getGrade() {
        return grade;
    }

    @Override
    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public void setPen(final PersonalEducationNumber pen) {
        this.pen = pen;
    }

    public void setCurrentMailingAddress(final PostalAddress address) {
        this.address = address;
    }

    public void setBirthdate(final Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setGrade(final String grade) {
        this.grade = grade;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getSccDate() {
        return sccDate;
    }

    public void setSccDate(Date sccDate) {
        this.sccDate = sccDate;
    }

    public String getMincodeGrad() {
        return mincodeGrad;
    }

    public void setMincodeGrad(String mincodeGrad) {
        this.mincodeGrad = mincodeGrad;
    }

    public String getEnglishCert() {
        return englishCert;
    }

    public void setEnglishCert(String englishCert) {
        this.englishCert = englishCert;
    }

    public String getFrenchCert() {
        return frenchCert;
    }

    public void setFrenchCert(String frenchCert) {
        this.frenchCert = frenchCert;
    }

    @Override
    public String getStudStatus() {
        return studStatus;
    }

    public void setStudStatus(String studStatus) {
        this.studStatus = studStatus;
    }

    /**
     * Returns a new date to avoid the null pointer exception thrown in the
     * report service that created an XML transcript.
     *
     * @return
     */
    @Override
    public Date getCreatedOn() {
        return new Date();
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, SignatureBlockType> getSignatureBlockTypes() {
        return signatureBlockTypes;
    }

    public void setSignatureBlockTypes(Map<String, SignatureBlockType> signatureBlockTypes) {
        this.signatureBlockTypes = signatureBlockTypes;
    }
}
