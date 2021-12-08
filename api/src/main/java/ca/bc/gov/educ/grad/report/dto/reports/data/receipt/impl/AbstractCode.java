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
package ca.bc.gov.educ.grad.report.dto.reports.data.receipt.impl;

import ca.bc.gov.educ.grad.report.dto.reports.impl.AbstractReportDomainEntity;
import ca.bc.gov.educ.grad.report.model.codes.Code;
import ca.bc.gov.educ.grad.report.model.codes.CodeSet;

/**
 * Isolates common attributes for payment status and payment method.
 *
 * @author CGI Information Management Consultants Inc.
 */
public abstract class AbstractCode extends AbstractReportDomainEntity implements Code {

    private static final long serialVersionUID = 2L;

    private String name;
    private String description;

    /**
     * Constructs with a description that will be the same as the given code
     * name.
     *
     * @param name The code's name.
     */
    public AbstractCode(final String name) {
        setName(name);
    }

    /**
     * Used to associate a code with a human-readable description.
     *
     * @param name The code's name.
     * @param description The associated descriptive text.
     */
    public AbstractCode(final String name, final String description) {
        this(name);
        setDescription(description);
    }

    /**
     * Returns the name of the code, which is typically an abbreviation that
     * corresponds to the description text.
     *
     * @return The code's abbreviated form.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Called by the constructor to set the code name.
     *
     * @param name The code abbreviation, never null.
     */
    private void setName(final String name) {
        this.name = trimSafe(name);
    }

    /**
     * Returns the human-readable description for this code. If the description
     * has not been set, this will return the code.
     *
     * @return The code or human-readable text, never null.
     */
    @Override
    public String getDescription() {
        return this.description == null ? getName() : this.description;
    }

    /**
     * If the description is set to null, the code's name is returned for the
     * description instead of human-readable text. This ensures that the
     * description will always return valid (non-null) and informative data.
     *
     * @param description The human-readable text, null, or empty.
     */
    private void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Not supported.
     *
     * @return Not used.
     */
    @Override
    public CodeSet getCodeSet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Not supported.
     *
     * @return Not used.
     */
    @Override
    public String getCodeHex() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Not supported.
     *
     * @return Not used.
     */
    @Override
    public String getCodeText() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Not supported.
     *
     * @return Not used.
     */
    @Override
    public String getAbsoluteName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Not supported.
     *
     * @return Not used.
     */
    @Override
    public String getStatus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Not supported.
     *
     * @return Not used.
     */
    @Override
    public Boolean isActive() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Not supported.
     *
     * @return Not used.
     */
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
