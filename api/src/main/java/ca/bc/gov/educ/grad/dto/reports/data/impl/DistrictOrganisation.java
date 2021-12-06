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
package ca.bc.gov.educ.grad.dto.reports.data.impl;

import ca.bc.gov.educ.grad.dto.reports.data.BusinessEntity;

/**
 * Represents the district organisation that operates a particular school.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class DistrictOrganisation extends BusinessEntity {

    private static final long serialVersionUID = -6769620495960490377L;

    private static final String NAME_SUFFIX = " of Education";
    private static final String NAME_BC = "Ministry" + NAME_SUFFIX;
    private static final String NAME_YU = "Department" + NAME_SUFFIX;

    private static final String DEFAULT_LOGO_CODE = "BC";

    public static final String LOGO_CODE_BC = DEFAULT_LOGO_CODE;
    public static final String LOGO_CODE_YUKON = "YU";

    /**
     * Logo code also represents the district organisation name.
     */
    private String logoCode = DEFAULT_LOGO_CODE;

    /**
     * Creates a new districtOrganisation with a default logo code.
     */
    public DistrictOrganisation() {
    }

    /**
     * Changes the district logo code that identifies the logo for this
     * organisation.
     *
     * @param logoCode The district code for the organisation.
     */
    public void setLogoCode(final String logoCode) {
        // Since the logo code has a default, defend against nulls.
        if (logoCode != null) {
            this.logoCode = logoCode;
        }
    }

    /**
     * Returns the code that uniquely identifies the agency's logo. Note that
     * this does not return standard Canadian abbreviations for Provinces and
     * Territories due to the TRAX data used to populate the class attribute.
     *
     * @return Either "BC" or "YU", or the empty string, never null.
     */
    public String getLogoCode() {
        return this.logoCode;
    }

    /**
     * Answers whether the logo code matches the given logo code.
     *
     * @param code The logo code to compare against this object's logo code.
     * @return True when the logo codes match.
     */
    public boolean isLogoCode(final String code) {
        return getLogoCode().equalsIgnoreCase(code);
    }

    /**
     * Returns the full name for the organisation that operates the district.
     *
     * @return "Ministry of Education" by default.
     */
    public String getName() {
        // TODO: Get this from a code set.
        return isLogoCode(LOGO_CODE_YUKON) ? NAME_YU : NAME_BC;
    }

    /**
     * Returns the name concatenated with the logo code in parenthesis.
     *
     * @return The name and logo code.
     */
    @Override
    public String toString() {
        return getName() + " (" + getLogoCode() + ")";
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<DistrictOrganisation, Builder> {

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
         * @return New district organisation instance.
         */
        @Override
        protected DistrictOrganisation createObject() {
            return new DistrictOrganisation();
        }

        /**
         * Sets the code that indicates what logo to use for the reports.
         *
         * @param logoCode "BC" or "YU"
         * @return thisBuilder
         */
        public Builder withLogoCode(final String logoCode) {
            getObject().setLogoCode(logoCode);
            return thisBuilder();
        }
    }
}
