/* *********************************************************************
 *  Copyright (c) 2015, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        GradProgramImpl.java
 *  Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015)  $
 *  Revision Number:     $Rev:: 36                                             $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.report.model.graduation.GradProgram;
import ca.bc.gov.educ.grad.report.model.graduation.GraduationProgramCode;

/**
 * Contains a graduation program code. The description is transcribed on the
 * transcripts by the report itself (based on the code).
 *
 * @author CGI Information Management Consultants Inc.
 */

public final class GradProgramImpl extends AbstractDomainEntity
        implements GradProgram {

    private static final long serialVersionUID = 3L;

    private GraduationProgramCode code;
    private String programCode;
    private String programName;
    private String expiryDate;

    public GradProgramImpl() {

    }

    public GradProgramImpl(GraduationProgramCode code, String expiryDate) {
        setCode(code);
        setExpiryDate(expiryDate);
    }

    @Override
    public GraduationProgramCode getCode() {
        return this.code;
    }

    @Override
    public void setCode(final GraduationProgramCode code) {
        this.code = code;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCode() {
        this.code = GraduationProgramCode.valueFrom(programCode, programName);
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    @Override
    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public void setExpiryDate(String value) {
        this.expiryDate = value;
    }
}
