/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: CertificateReportImpl.java 9296 2018-01-30 21:2#$
 *  Date of Last Commit: $Date:: 2018-01-30 13:26:17 -0800 (Tue, 30 Jan 2018)  $
 *  Revision Number:     $Rev:: 9296                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.dto.reports.impl;

import ca.bc.gov.educ.grad.report.dto.reports.data.adapter.BusinessEntityAdapter;
import ca.bc.gov.educ.grad.report.dto.reports.data.impl.Certificate;
import ca.bc.gov.educ.grad.report.dto.reports.data.impl.Signatories;
import ca.bc.gov.educ.grad.report.model.cert.CertificateSubType;
import ca.bc.gov.educ.grad.report.model.cert.CertificateType;
import ca.bc.gov.educ.grad.report.model.reports.CertificateReport;
import org.apache.commons.lang3.StringUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.model.cert.CertificateSubType.REPRINT;
import static ca.bc.gov.educ.grad.report.model.cert.CertificateType.SC;
import static java.lang.Boolean.FALSE;
import static java.util.Locale.CANADA_FRENCH;
import static java.util.Locale.ENGLISH;

/**
 * Represents information required to generate a certificate.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class CertificateReportImpl extends StudentReportImpl
        implements CertificateReport {

    private static final long serialVersionUID = 7L;

    private static final String CLASSNAME = CertificateReportImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    public static final String REPORT_NAME = "Certificate";

    private static final String P_REPORT_SUBTYPE = "P_REPORT_SUBTYPE";

    /**
     * Contains the student, school, issue date, and signature set.
     */
    private Certificate certificate;
    private CertificateType certificateType = SC;
    private CertificateSubType certificateSubType = REPRINT;

    private Boolean independentSchool = FALSE;
    private String schoolSignatureCode = "";

    /**
     * Constructs a new report using the default report template for
     * certificates. The default is an SCCP report.
     */
    public CertificateReportImpl() {
        super(REPORT_NAME);
    }

    /**
     * Returns the main object used to fill the report.
     *
     * @return this.certificate
     */
    @Override
    public Object getDataSource() {
        return this.certificate;
    }

    @Override
    public boolean isPreview() {
        return false;
    }

    /**
     * Sets the report date (the certificate's issue date), report type, and
     * report subtype.
     */
    @Override
    public void preprocessParameters() {
        super.preprocessParameters();

        // Redundant call (not used by the certificate report); needed to
        // succesfully run superclass's export method. The certificate report
        // extracts the issue date from the corresponding certificate instance
        // field. Eats a few CPU cycles, but otherwise harmless.
        setReportDate(getCertificate().getIssued());

        setParameter(P_REPORT_TYPE, getReportType());
        setParameter(P_REPORT_SUBTYPE, getReportSubtype());

        processSignatories();
    }

    /**
     * Called to set the signatories associated with the certificate. This will
     * apply school signatures to a certificate based on the logic inside the
     * applySchoolSignatory method.
     *
     * TODO: Move logic out of report services.
     */
    private void processSignatories() {
        final Signatories signatories = new Signatories();
        signatories.setSchoolSignatory(getSchoolSignatureCode());
        LOG.log(Level.FINE, "School signatory code is <{0}>.",
                signatories.getSchoolSignatory());

        getCertificate().setSignatories(signatories);
    }

    /**
     * Sets the information to write on the certificate.
     *
     * @param certificate Certificate information to adapt for this report.
     */
    @Override
    public void setCertificate(
            final ca.bc.gov.educ.grad.report.model.cert.Certificate certificate) {
        if(!certificate.getCertStyle().equalsIgnoreCase("Blank"))
            ensureValidStudent("setCertificate");

        this.certificate = BusinessEntityAdapter.adapt(certificate);
        this.certificate.setStudent(getStudent());
    }

    /**
     * Sets the school and consequently signing authority for the school.
     *
     * @param school The school with a mincode used to derive the signature
     * code.
     */
    @Override
    public void setSchool(
            final ca.bc.gov.educ.grad.report.model.school.School school,
            final String logoCode) {
        final String methodName = "setSchool";
        LOG.entering(CLASSNAME, methodName);

        super.setSchool(school, logoCode);
        ensureValidCertificate("setSchool");
        setIndependentSchool(school.isIndependent());
        setSchoolSignatureCode(school.getSignatureCode());

        LOG.exiting(CLASSNAME, methodName);
    }

    /**
     * Helper method.
     *
     * @param method The name of the method that cannot be called prior to
     * calling setCertificate.
     */
    protected final void ensureValidCertificate(final String method) {
        validate(getCertificate(), "setCertificate", method);
    }

    /**
     * Returns the main type of certificate to generate.
     *
     * @return The certificate report type as a String, never null, never empty.
     */
    private String getReportType() {
        return this.certificateType.toString();
    }

    /**
     * Sets the report type name from the enumeration.
     *
     * @param certificateType The enumerated type that represents the
     * certificate type to generate.
     */
    @Override
    public void setReportType(
            final CertificateType certificateType) {
        this.certificateType = certificateType;
    }

    /**
     * Returns the subtype of certificate to generate.
     *
     * @return The certificate report subtype as a String, never null, possibly
     * empty.
     */
    private String getReportSubtype() {
        return this.certificateSubType.toString();
    }

    /**
     * Sets the report subtype name from the enumeration.
     *
     * @param certificateSubtype The enumerated type that represents the
     * certificate subtype to generate.
     */
    @Override
    public void setReportSubtype(
            final CertificateSubType certificateSubtype) {
        this.certificateSubType = certificateSubtype;
    }

    /**
     * Returns certificate information to put on the report (e.g., the issue
     * date, student name, school).
     *
     * @return The certificate for reporting.
     */
    private Certificate getCertificate() {
        return this.certificate;
    }

    /**
     * Returns the portion of the file name to add the start of the filename,
     * before the suffix.
     *
     * @return The name hyphenated with the report type.
     */
    @Override
    protected String getFilenameSuffix() {
        final String language = "-" + getLocale().getLanguage().toLowerCase(ENGLISH);
        final String type = "-" + getReportType();
        String subtype = getReportSubtype();

        subtype = "-" + (subtype.isEmpty() ? "default" : subtype);

        return language + type + subtype;
    }

    private boolean isSCCProgram() {
        return isCertificateType(CertificateType.SC);
    }

    private boolean isFrenchProgram() {
        // Returns true for "S" or "F" certificate codes.
        return getLocale() == CANADA_FRENCH;
    }

    private boolean isCertificateType(final CertificateType ct) {
        return getCertificateType() == ct;
    }

    private boolean isCertificateSubtype(final CertificateSubType ct) {
        return getCertificateSubType() == ct;
    }

    private CertificateType getCertificateType() {
        return this.certificateType;
    }

    private CertificateSubType getCertificateSubType() {
        return this.certificateSubType;
    }

    private Boolean isIndependentSchool() {
        return this.independentSchool;
    }

    private void setIndependentSchool(final Boolean independentSchool) {
        this.independentSchool = independentSchool;
    }

    private String getSchoolSignatureCode() {
        return StringUtils.startsWithIgnoreCase(certificateType.getReportName(), "AI")
                || StringUtils.startsWithIgnoreCase(certificateType.getReportName(), "EI")
                || StringUtils.startsWithIgnoreCase(certificateType.getReportName(), "SCI") ? "INDEP" : this.schoolSignatureCode;
    }

    private void setSchoolSignatureCode(final String schoolSignatureCode) {
        this.schoolSignatureCode = schoolSignatureCode;
    }

    /**
     * Returns a description of this report (name, media type, etc.).
     *
     * @return The report description.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(256);
        sb.append(super.toString());
        sb.append("; signature code: <");
        sb.append(getSchoolSignatureCode());
        sb.append(">; certificate type: <");
        sb.append(getCertificateType());
        sb.append(">; certificate subtype: <");
        sb.append(getCertificateSubType());
        sb.append(">");

        return sb.toString();
    }
}
