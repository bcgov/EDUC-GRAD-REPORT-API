/* *********************************************************************
 *  Copyright (c) 2015, Ministry of Education, BC.
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
package ca.bc.gov.educ.grad.dto.impl;

import ca.bc.gov.educ.grad.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.model.graduation.GradProgram;
import ca.bc.gov.educ.grad.model.graduation.GraduationProgramCode;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 * Contains a graduation program code. The description is transcribed on the
 * transcripts by the report itself (based on the code).
 *
 * @author CGI Information Management Consultants Inc.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public final class GradProgramImpl extends AbstractDomainEntity
        implements GradProgram {

    private static final long serialVersionUID = 3L;

    private GraduationProgramCode code;

    public GradProgramImpl() {

    }

    public GradProgramImpl(GraduationProgramCode code) {
        setCode(code);
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
}
