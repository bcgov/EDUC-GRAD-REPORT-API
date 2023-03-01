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
 *  File:                $Id:: GraduationProgram.java 9753 2018-03-23 22:21:32#$
 *  Date of Last Commit: $Date:: 2018-03-23 15:21:32 -0700 (Fri, 23 Mar 2018)  $
 *  Revision Number:     $Rev:: 9753                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.data.impl;

import ca.bc.gov.educ.grad.report.dto.reports.data.BusinessEntity;
import ca.bc.gov.educ.grad.report.model.graduation.GraduationProgramCode;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

import static ca.bc.gov.educ.grad.report.model.graduation.GraduationProgramCode.PROGRAM_1996;
import static ca.bc.gov.educ.grad.report.model.graduation.GraduationProgramCode.valueFrom;

/**
 * Represents information about a student's graduation program.
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class GraduationProgram extends BusinessEntity implements Serializable {

    private static final long serialVersionUID = 4L;

    /**
     * Code that corresponds to the program description.
     */
    @XmlElement(name = "code")
    private GraduationProgramCode code;

    /**
     * Description that corresponds to the program code. Used in an XML
     * transcript.
     */
    @XmlElement(name = "academicAwardTitle")
    private String description;

    /**
     * Default (empty) constructor.
     */
    public GraduationProgram() {
    }

    /**
     * Changes the graduation program code and the program description. The
     * description is changed so that the JAXB can use reflection to obtain
     * academicAwardTitle.
     *
     * @param code The graduation program code.
     */
    protected void setCode(final String code, final String description) {
        this.code = valueFrom(code, description);

        // Forces TRAX graduation program codes of 1996 to be 1995 so that
        // the transcript description is correct.
        if (PROGRAM_1996.isCode(code)) {
            this.code = PROGRAM_1996;
        }

        this.description = StringUtils.trimToNull(description) == null ? this.code.getDescription() : description;
    }

    /**
     * Returns the code associated with the graduation program description.
     *
     * @return A student's graduation program code, or null if not yet set.
     */
    public GraduationProgramCode getCode() {
        return this.code;
    }

    /**
     * Returns the description associated with the graduation program code. If
     * there is no code assigned, this will return the empty string.
     *
     * @return A non-null string (e.g., "School Completion Certificate
     * Program").
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the number of credits required to graduate.
     *
     * @return A whole number.
     */
    public int getCredits() {
        final GraduationProgramCode programCode = getCode();

        return programCode == null ? 0 : programCode.getCredits();
    }

    /**
     * Answers whether the given code matches the graduation program code.
     *
     * @param code The code to compare against.
     * @return true The codes match.
     */
    public boolean isCode(final String code) {
        final GraduationProgramCode programCode = getCode();

        return programCode == null ? false : programCode.isCode(code);
    }

    public boolean isAdult() {
        final GraduationProgramCode programCode = getCode();

        return programCode == null ? false : programCode.isAdult();
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder
            extends BusinessEntity.Builder<GraduationProgram, Builder> {

        /**
         * Returns the builder used to construct outer class instances.
         *
         * @return this
         */
        @Override
        protected Builder thisBuilder() {
            return this;
        }

        /**
         * Returns an outer class instance without attributes initialized.
         *
         * @return New GraduationProgram instance.
         */
        @Override
        protected GraduationProgram createObject() {
            return new GraduationProgram();
        }

        /**
         * Sets the graduation program code.
         *
         * @param code Code that uniquely identifies this graduation program.
         * @return thisBuilder
         */
        public Builder withCode(final String code, final String description) {
            getObject().setCode(code, description);
            return thisBuilder();
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "code=" + code + ", description=" + description + '}';
    }
}
