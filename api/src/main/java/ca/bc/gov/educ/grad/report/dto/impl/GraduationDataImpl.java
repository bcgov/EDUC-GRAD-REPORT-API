/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education and Child Care, BC.
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
package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import ca.bc.gov.educ.grad.report.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.report.model.transcript.GraduationData;
import ca.bc.gov.educ.grad.report.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;

/**
 * This implementation contains graduation data that is used in the generation
 * of the PESC XML transcript. It is used in the AcademicAward section.
 *
 * @author CGI Information Management Consultants Inc.
 */

public class GraduationDataImpl extends AbstractDomainEntity
        implements GraduationData, Serializable {

    /**
     * null means that the student hasn't graduated.
     */
    private LocalDate graduationDate;
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
    @JsonFormat(pattern= ReportApiConstants.DEFAULT_DATE_FORMAT)
    public LocalDate getGraduationDate() {
        return this.graduationDate == null ? LocalDate.now() : this.graduationDate;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getTruncatedGraduationDate() {
        LocalDate result = getGraduationDate();
        if(result != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
            return sdf.format(DateUtils.toDate(result));
        }
        return null;
    }

    public String getFullGraduationDate() {
        LocalDate result = getGraduationDate();
        if(result != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(DateUtils.toDate(result));
        }
        return null;
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
