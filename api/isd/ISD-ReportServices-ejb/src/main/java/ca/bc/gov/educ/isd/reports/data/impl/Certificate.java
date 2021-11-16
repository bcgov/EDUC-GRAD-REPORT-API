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
package ca.bc.gov.educ.isd.reports.data.impl;

import ca.bc.gov.educ.isd.codes.SignatureBlockType;
import ca.bc.gov.educ.isd.reports.data.BusinessEntity;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Represents information that is placed on a certificate report.
 *
 * @author CGI Information Management Consultants Inc.
 */
//@XmlAccessorType(XmlAccessType.FIELD)
public final class Certificate extends BusinessEntity {

    private static final long serialVersionUID = 2L;

    /**
     * Recipient.
     */
    private Student student;

    /**
     * Date the certificate was issued (default today).
     */
    private Date issued = new Date();

    /**
     * Signatures added to a certificate.
     */
    private Signatories signatories;

    private Map<String, SignatureBlockType> signatureBlockTypes;

    /**
     * Default (empty) constructor.
     */
    public Certificate() {
    }

    /**
     * Changes the issued date.
     *
     * @param issued When the certificate was issued.
     */
    public void setIssued(final Date issued) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(issued);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        this.issued = cal.getTime();
    }

    /**
     * Sets the student who was issued this certificate.
     *
     * @param student The certificate recipient.
     */
    public void setStudent(final Student student) {
        if (student != null) {
            this.student = student;
        }
    }

    public Map<String, SignatureBlockType> getSignatureBlockTypes() {
        return signatureBlockTypes;
    }

    public void setSignatureBlockTypes(Map<String, SignatureBlockType> signatureBlockTypes) {
        this.signatureBlockTypes = signatureBlockTypes;
    }

    /**
     * Provided so that subreports can also view all the fields (without having
     * to pass all the fields to each subreport individually via parameters).
     *
     * @return this
     */
    public Certificate getCertificate() {
        return this;
    }

    /**
     * Returns the date that the certificate was issued. This should probably be
     * removed in favour of P_REPORT_DATE.
     *
     * @return A valid date, possibly today's date, never null.
     */
    public Date getIssued() {
        return this.issued == null ? new Date() : this.issued;
    }

    /**
     * Returns an instance of the student that contains the person's name that
     * is printed on the certificate.
     *
     * @return The certificate recipient.
     */
    public Student getStudent() {
        return this.student;
    }

    /**
     * Returns the set of signatures to adorn the certificate.
     *
     * @return A number of signatures to add to the certificate.
     */
    public Signatories getSignatories() {
        return this.signatories;
    }

    /**
     * Changes the set of signatures that adorn the certificate.
     *
     * @param signatures A number of signatures that add to the certificate.
     */
    public void setSignatories(final Signatories signatures) {
        this.signatories = signatures;
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<Certificate, Builder> {

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
         * @return New Certificate instance.
         */
        @Override
        protected Certificate createObject() {
            return new Certificate();
        }

        /**
         * Sets the date the certificate was issued for the student.
         *
         * @param issued Certificate's issue date.
         * @return thisBuilder
         */
        public Builder withIssueDate(final Date issued) {
            getObject().setIssued(issued);
            return thisBuilder();
        }

        public Builder withSignatureBlockTypes(final Map<String, SignatureBlockType> signatureBlockTypes) {
            getObject().setSignatureBlockTypes(signatureBlockTypes);
            return thisBuilder();
        }

        /**
         * Builds the certificate.
         *
         * @param student Certificate's recipient.
         * @return thisBuilder
         */
        public Builder withStudent(final Student student) {
            getObject().setStudent(student);
            return thisBuilder();
        }

        /**
         * Builds the certificate.
         *
         * @param signatories Certificate's signatories.
         * @return thisBuilder
         */
        public Builder withSignatories(final Signatories signatories) {
            getObject().setSignatories(signatories);
            return thisBuilder();
        }
    }
}
