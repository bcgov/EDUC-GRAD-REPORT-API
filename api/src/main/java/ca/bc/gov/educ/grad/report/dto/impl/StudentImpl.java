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

import ca.bc.gov.educ.grad.report.model.common.SignatureBlockType;
import ca.bc.gov.educ.grad.report.model.common.party.address.PostalAddress;
import ca.bc.gov.educ.grad.report.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.report.model.graduation.NonGradReason;
import ca.bc.gov.educ.grad.report.model.graduation.OtherProgram;
import ca.bc.gov.educ.grad.report.model.student.PersonalEducationNumber;
import ca.bc.gov.educ.grad.report.model.student.Student;
import ca.bc.gov.educ.grad.report.model.transcript.GraduationData;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */

public class StudentImpl extends AbstractDomainEntity implements Student {

    private static final long serialVersionUID = 3L;

    private PersonalEducationNumber pen = null;
    private Date birthdate = new Date(0L);
    private PostalAddress address = new PostalAddressImpl();
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private String grade = "";
    private String gender = "";
    private Date sccDate;
    private String mincodeGrad;
    private String englishCert;
    private String frenchCert;
    private String studStatus = "";
    private String gradProgram = "";
    private String gradReqYear = "";
    private Map<String, SignatureBlockType> signatureBlockTypes;

    private String localId = "";
    private String hasOtherProgram = "";
    private Date lastUpdateDate;
    private Date certificateDistributionDate;
    private List<OtherProgram> otherProgramParticipation = new ArrayList<>();
    private List<NonGradReason> nonGradReasons = new ArrayList<>();

    private GraduationData graduationData;

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
        return firstName == null ? "" : firstName;
    }

    @Override
    public String getLastName() {
        return lastName == null ? "" : lastName;
    }

    @Override
    public String getMiddleName() {
        return middleName == null ? "" : middleName;
    }

    @Override
    public String getFullName() {
        return (getLastName().trim() + (isBlank() ? "" : ", " ) + getFirstName().trim() + " " + getMiddleName().trim()).toUpperCase();
    }

    @Override
    public String getGrade() {
        return grade;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public String getGradProgram() {
        return gradProgram;
    }

    @Override
    public String getGradProgramYear() {
        return StringUtils.substringBefore(getGradReqYear(), "-");
    }

    @Override
    public String getGradReqYear() {
        return gradReqYear;
    }

    @Override
    public String getLocalId() {
        return localId;
    }

    @Override
    public String getHasOtherProgram() {
        return hasOtherProgram;
    }

    @Override
    public List<OtherProgram> getOtherProgramParticipation() {
        return otherProgramParticipation;
    }

    @Override
    public List<NonGradReason> getNonGradReasons() {
        return nonGradReasons == null ? Collections.emptyList() : nonGradReasons;
    }

    @Override
    public String getNonGradReasonsString() {
        return getNonGradReasons().stream()
                .map(n -> String.valueOf(n.toString()))
                .collect(Collectors.joining("\n", "", ""));
    }

    public void setGender(String gender) {
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

    public void setLocalId(String localId) {
        this.localId = localId;
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

    public void setGradProgram(String gradProgram) {
        this.gradProgram = gradProgram;
    }

    public void setGradReqYear(String gradReqYear) {
        this.gradReqYear = gradReqYear;
    }

    public void setHasOtherProgram(String hasOtherProgram) {
        this.hasOtherProgram = hasOtherProgram;
    }

    public void setOtherProgramParticipation(List<OtherProgram> otherProgramParticipation) {
        this.otherProgramParticipation = otherProgramParticipation;
    }

    public void setNonGradReasons(List<NonGradReason> nonGradReasons) {
        this.nonGradReasons = nonGradReasons;
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

    public JRBeanCollectionDataSource getOtherProgramParticipationdataSource() {
        return new JRBeanCollectionDataSource(otherProgramParticipation, false);
    }

    public GraduationData getGraduationData() {
        return graduationData;
    }

    public void setGraduationData(GraduationData graduationData) {
        this.graduationData = graduationData;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getCertificateDistributionDate() {
        return certificateDistributionDate;
    }

    public void setCertificateDistributionDate(Date certificateDistributionDate) {
        this.certificateDistributionDate = certificateDistributionDate;
    }

    private boolean isBlank() {
        return !ca.bc.gov.educ.grad.report.model.common.support.StringUtils.StringUtilsIsNotBlank(getFirstName() + getLastName());
    }
}
