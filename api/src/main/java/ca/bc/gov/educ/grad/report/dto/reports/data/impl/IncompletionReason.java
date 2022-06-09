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
 *  File:                $Id:: IncompletionReason.java 5864 2017-01-18 21:34:2#$
 *  Date of Last Commit: $Date:: 2017-01-18 13:34:24 -0800 (Wed, 18 Jan 2017)  $
 *  Revision Number:     $Rev:: 5864                                           $
 *  Last Commit by:      $Author:: matalbot                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.data.impl;

import ca.bc.gov.educ.grad.report.dto.reports.data.BusinessEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents a reason a student did not meet graduation requirements.
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class IncompletionReason extends BusinessEntity {

    private static final long serialVersionUID = 5310354130937153855L;

    /**
     * The code for why a Student failed to graduate.
     */
    @XmlElement(name = "incompleteGradCode")
    private String code;

    /**
     * The description for why a Student failed to graduate.
     */
    private String description;

    /**
     * Default (empty) constructor.
     */
    public IncompletionReason() {
    }

    /**
     * Sets a codified reason for incomplete graduation.
     *
     * @param code Code that corresponds to the human-readable description.
     */
    protected void setCode(final String code) {
        this.code = code;
    }

    /**
     * Sets a descriptive reason for incomplete graduation.
     *
     * @param description Description that corresponds to the code.
     */
    protected void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns a codified reason for incomplete graduation.
     *
     * @return The reason the student has not yet graduated.
     */
    public String getCode() {
        return nullSafe(this.code);
    }

    /**
     * Returns a descriptive reason for incomplete graduation.
     *
     * @return The reason the student has not yet graduated.
     */
    public String getDescription() {
        return nullSafe(this.description);
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder
            extends BusinessEntity.Builder<IncompletionReason, Builder> {

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
        protected IncompletionReason createObject() {
            return new IncompletionReason();
        }

        /**
         * Sets code that indicates why the student failed to fulfill their
         * education requirements.
         *
         * @param code The codified reason why the Student has not graduated.
         * @return thisBuilder
         */
        public Builder withCode(final String code) {
            getObject().setCode(code);
            return thisBuilder();
        }

        /**
         * Sets code that indicates why the student failed to fulfill their
         * education requirements.
         *
         * @param description The human readable reason why the Student has not
         * graduated.
         * @return thisBuilder
         */
        public Builder withDescription(final String description) {
            getObject().setDescription(description);
            return thisBuilder();
        }
    }
}
