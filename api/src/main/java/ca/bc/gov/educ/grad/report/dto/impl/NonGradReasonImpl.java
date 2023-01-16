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
 *  File:                NonGradReasonImpl.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.report.model.graduation.NonGradReason;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Represents a predefined reason why graduation requirements were not met. This
 * holds a code and a text reason.
 * <p>
 * @author CGI Information Management Consultants Inc.
 */

public class NonGradReasonImpl extends AbstractDomainEntity
        implements NonGradReason, Serializable {

    private static final long serialVersionUID = 3L;

    private String code;
    private String description;

    public NonGradReasonImpl() {}

    @Override
    public String getCode() {
        return StringUtils.startsWith(code, "!") ? "" : code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
