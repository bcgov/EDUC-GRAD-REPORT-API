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
package ca.bc.gov.educ.grad.report.dto.reports.data.impl;

import ca.bc.gov.educ.grad.report.dto.reports.data.BusinessEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Represents a school, including its name, address, and agency.
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public final class School extends BusinessEntity {

    private static final long serialVersionUID = 1L;

    /**
     * MINCODE.
     */
    private String ministryCode;

    /**
     * School name.
     */
    private String name;

    /**
     * Non-space indicates an independent school.
     */
    private String typeIndicator;

    /**
     * E.g., "B.C. Independent Schools - Group 1"
     */
    private String typeBanner;

    /**
     * Mailing address.
     */
    private PostalAddress address;

    /**
     * Organisation that oversees the schools in various districts.
     */
    private DistrictOrganisation districtOrganisation;

    /**
     * Default (empty) constructor.
     */
    public School() {
    }

    /**
     * Returns a combination of a 3-digit district code and 5-digit school code.
     * In TRAX, this is called the MINCODE.
     *
     * @return An eight-digit code or 0, but never null.
     */
    public String getMinistryCode() {
        return nullSafe(this.ministryCode);
    }

    /**
     * Returns the school name. The name might be title case, lower case, or
     * upper case. The calling client must format the name as required.
     *
     * @return A non-null string that contains the school name.
     */
    public String getName() {
        return nullSafe(this.name);
    }

    /**
     * Returns the school type indicator used to determine independent schools
     * and therefore whether to use the "independent" signature.
     *
     * @return A non-null string, possibly empty.
     */
    public String getTypeIndicator() {
        return nullSafe(this.typeIndicator);
    }

    /**
     * Returns the school type banner displayed above the mailing address.
     *
     * @return A non-null string, possibly empty.
     */
    public String getTypeBanner() {
        return nullSafe(this.typeBanner);
    }

    /**
     * Returns the school's mailing address.
     *
     * @return An address instance with street, postal code, etc.
     */
    public PostalAddress getAddress() {
        return this.address == null ? createAddress() : this.address;
    }

    /**
     * Returns the school's operational district. This is typically either the
     * Ministry of Education ("BC") or Department of Education ("YU").
     *
     * @return A district responsible for managing the school.
     */
    public DistrictOrganisation getDistrictOrganisation() {
        return this.districtOrganisation == null ? createDistrictOrganisation()
                : this.districtOrganisation;
    }

    /**
     * Creates a new address with mock data.
     *
     * @return An address instance with default field values.
     */
    protected PostalAddress createAddress() {
        return new PostalAddress();
    }

    /**
     * Called when no district organisation has been populated.
     *
     * @return A new DistrictOrganisation instance with mock data.
     */
    protected DistrictOrganisation createDistrictOrganisation() {
        return new DistrictOrganisation();
    }

    /**
     * Used by the builder to set the ministry code.
     *
     * @param ministryCode Passed in by the builder.
     */
    protected void setMinistryCode(final String ministryCode) {
        this.ministryCode = ministryCode;
    }

    /**
     * Used by the builder to set the school name.
     *
     * @param name Passed in by the builder.
     */
    protected void setName(final String name) {
        this.name = name;
    }

    /**
     * Changes the school type indicator.
     *
     * @param typeIndicator The new code.
     */
    public void setTypeIndicator(final String typeIndicator) {
        this.typeIndicator = typeIndicator;
    }

    /**
     * Changes the school type banner displayed above the mailing address.
     *
     * @param typeBanner The new text.
     */
    public void setTypeBanner(final String typeBanner) {
        this.typeBanner = typeBanner;
    }

    /**
     * Used by the builder to set the school address.
     *
     * @param address Passed in by the builder.
     */
    protected void setAddress(final PostalAddress address) {
        this.address = address;
    }

    /**
     * Used by the builder to set the district organisation.
     *
     * @param districtOrganisation Passed in by the builder.
     */
    protected void setDistrictOrganisation(
            final DistrictOrganisation districtOrganisation) {
        this.districtOrganisation = districtOrganisation;
    }

    /**
     * Returns a human-readable version of the school details.
     *
     * @return A string with the name, ministry code, district organisation, and
     * address concatenated together.
     */
    @Override
    public String toString() {
        return getName() + " (" + getMinistryCode() + ") -- "
                + getDistrictOrganisation() + " -- " + getAddress();
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<School, Builder> {

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
         * @return New School instance.
         */
        @Override
        protected School createObject() {
            return new School();
        }

        /**
         * Sets the ministry code. This is a unique identifier for the school.
         *
         * @param ministryCode The code supplied by the Ministry.
         * @return thisBuilder
         */
        public Builder withMinistryCode(final String ministryCode) {
            getObject().setMinistryCode(ministryCode);
            return thisBuilder();
        }

        /**
         * Convenience method to set the Ministry code for the school using an
         * integer value.
         *
         * @param ministryCode The Ministry code to set.
         * @return thisBuilder
         */
        public Builder withMinistryCode(final Integer ministryCode) {
            final String mincode = nullSafe(ministryCode).toString();
            return withMinistryCode(mincode);
        }

        /**
         * Sets the school name. The corresponding get accessor does not change
         * the case of the school name. Clients of this class should ensure that
         * the school case name is consistent (e.g., by calling toUpperCase() on
         * the result from getName()).
         *
         * @param name The full name of the school.
         * @return thisBuilder
         */
        public Builder withName(final String name) {
            getObject().setName(name);
            return thisBuilder();
        }

        /**
         * Sets the independent school type indicator.
         *
         * @param typeIndicator The school type banner.
         * @return thisBuilder
         */
        public Builder withTypeIndicator(final String typeIndicator) {
            getObject().setTypeIndicator(typeIndicator);
            return thisBuilder();
        }

        /**
         * Sets the school type banner.
         *
         * @param typeBanner The school type banner.
         * @return thisBuilder
         */
        public Builder withTypeBanner(final String typeBanner) {
            getObject().setTypeBanner(typeBanner);
            return thisBuilder();
        }

        /**
         * Sets the mailing address.
         *
         * @param address The mailing address.
         * @return thisBuilder
         */
        public Builder withAddress(final PostalAddress address) {
            getObject().setAddress(address);
            return thisBuilder();
        }

        /**
         * Sets the district organisation responsible for managing the school
         * (e.g., Ministry of Education, BC).
         *
         * @param districtOrganisation
         * @return thisBuilder
         */
        public Builder withDistrictOrganisation(final DistrictOrganisation districtOrganisation) {
            getObject().setDistrictOrganisation(districtOrganisation);
            return thisBuilder();
        }
    }
}
