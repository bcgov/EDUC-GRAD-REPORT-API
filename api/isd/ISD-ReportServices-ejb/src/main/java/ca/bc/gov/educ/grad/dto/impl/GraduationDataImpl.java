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
 *  File:                GraduationDataImpl.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.dto.impl;

import ca.bc.gov.educ.grad.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.model.transcript.GraduationData;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Boolean.FALSE;

/**
 * This implementation contains graduation data that is used in the generation
 * of the PESC XML transcript. It is used in the AcademicAward section.
 *
 * @author CGI Information Management Consultants Inc.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class GraduationDataImpl extends AbstractDomainEntity
        implements GraduationData, Serializable {

    /**
     * null means that the student hasn't graduated.
     */
    private Date graduationDate;
    private Boolean honorsFlag = FALSE;
    private Boolean dogwoodFlag = FALSE;
    private List<String> programCodes = new ArrayList<>();
    private List<String> programNames = new ArrayList<>();
    private String totalCreditsUsedForGrad = "";

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasGraduated() {
        return this.graduationDate != null;
    }

    @Override
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getGraduationDate() {
        return this.graduationDate == null ? new Date() : this.graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getTruncatedGraduationDate() {
        Date result = getGraduationDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
        return sdf.format(result);
    }

    public void setDogwoodFlag(Boolean dogwoodFlag) {
        this.dogwoodFlag = dogwoodFlag;
    }

    @Override
    public Boolean getDogwoodFlag() {
        return this.dogwoodFlag;
    }

    @Override
    public List<String> getProgramCodes() {
        return programCodes;
    }

    public void setProgramCodes(List<String> programCodes) {
        this.programCodes = programCodes;
    }

    @Override
    public String getTotalCreditsUsedForGrad() {
        return totalCreditsUsedForGrad;
    }

    public void setTotalCreditsUsedForGrad(String totalCreditsUsedForGrad) {
        this.totalCreditsUsedForGrad = totalCreditsUsedForGrad;
    }

    @Override
    public Boolean getHonorsFlag() {
        return this.honorsFlag;
    }

    public void setHonorsFlag(Boolean honorsFlag) {
        this.honorsFlag = honorsFlag;
    }

    @Override
    public List<String> getProgramNames() {
        return programNames;
    }

    public void setProgramNames(List<String> programNames) {
        this.programNames = programNames;
    }
}
