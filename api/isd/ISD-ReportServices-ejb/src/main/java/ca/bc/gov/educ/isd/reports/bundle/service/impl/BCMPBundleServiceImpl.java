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
package ca.bc.gov.educ.isd.reports.bundle.service.impl;

import ca.bc.gov.educ.isd.common.BusinessReport;
import ca.bc.gov.educ.isd.reports.CertificateType;
import ca.bc.gov.educ.isd.reports.Report;
import ca.bc.gov.educ.isd.reports.ReportDocument;
import ca.bc.gov.educ.isd.reports.bundle.decorator.CertificateOrderTypeImpl;
import ca.bc.gov.educ.isd.reports.bundle.decorator.TranscriptOrderTypeImpl;
import ca.bc.gov.educ.isd.reports.bundle.model.DocumentBundleImpl;
import ca.bc.gov.educ.isd.reports.bundle.model.Roles;
import ca.bc.gov.educ.isd.reports.bundle.service.BCMPBundleService;
import ca.bc.gov.educ.isd.reports.bundle.service.DocumentBundle;
import ca.bc.gov.educ.isd.reports.bundle.service.OrderType;
import ca.bc.gov.educ.isd.reports.jasper.impl.JasperReportImpl;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.common.support.impl.Roles.FULFILLMENT_SERVICES_USER;
import static ca.bc.gov.educ.isd.reports.impl.constants.Roles.USER_REPORTS_EXPORT;

/**
 * Responsible for bundling PDFs into print-ready mailing packets according to
 * the requirements set out by BC Mail Plus (BCMP).
 *
 * @author CGI Information Management Consultants Inc.
 */

@Service
@DeclareRoles({
    Roles.USER_BCMP_SERVICE,
    Roles.USER_BCMP_APPEND,
    Roles.USER_BCMP_BUNDLE,
    Roles.USER_BCMP_ENUMERATE,
    Roles.USER_BCMP_XPIF, FULFILLMENT_SERVICES_USER})
public class BCMPBundleServiceImpl implements BCMPBundleService {

    private static final String CLASSNAME = BCMPBundleServiceImpl.class.getName();
    protected static transient final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final long serialVersionUID = 3L;

    /**
     * Default (empty) constructor.
     */
    public BCMPBundleServiceImpl() {
    }

    /**
     * TODO: Create a common superclass for this class and ReportServiceImpl so
     * that the export method is common between them.
     *
     * @throws IOException
     * @inheritDoc
     */
    @Override
    @RolesAllowed({Roles.USER_BCMP_BUNDLE, USER_REPORTS_EXPORT, FULFILLMENT_SERVICES_USER})
    public ReportDocument export(final Report report) throws IOException {
        final JasperReportImpl jasperReport = new JasperReportImpl(report);

        return jasperReport.export();
    }

    /**
     * Creates empty document bundle to be populated with report documents. This
     * must be the first call using the service to create a bundle for BC Mail
     * Plus.
     *
     * @return A new DocumentBundle implementation.
     */
    @Override
    @RolesAllowed({Roles.USER_BCMP_BUNDLE, FULFILLMENT_SERVICES_USER})
    public DocumentBundle createDocumentBundle(final OrderType orderType) {
        return new DocumentBundleImpl(orderType, generateUUID64());
    }

    /**
     * Append more documents to a bundled document This should be the second
     * call when using the service to create a bundle for BC Mail Plus.
     *
     * @param bundle Target document.
     * @param report Append to bundle.
     * @throws IOException Could not append the report to the bundle.
     */
    @Override
    @RolesAllowed({Roles.USER_BCMP_APPEND, FULFILLMENT_SERVICES_USER})
    public DocumentBundle appendReportDocument(
            final DocumentBundle bundle,
            final ReportDocument report) throws IOException {
        bundle.append(report);
        return bundle;
    }

    /**
     * Append multiple documents to a bundle. This should be the second call
     * when using the service to create a bundle for BC Mail Plus.
     *
     * @param bundle Target document.
     * @param reports A list of documents to be appended to the bundle.
     * @throws IOException Could not append the report to the bundle.
     */
    @Override
    @RolesAllowed({Roles.USER_BCMP_APPEND, FULFILLMENT_SERVICES_USER})
    public DocumentBundle appendReportDocument(
            final DocumentBundle bundle,
            final List<ReportDocument> reports) throws IOException {
        bundle.appendReportDocument(reports);
        return bundle;
    }

    @Override
    @RolesAllowed({Roles.USER_BCMP_APPEND, FULFILLMENT_SERVICES_USER})
    public DocumentBundle appendBusinessReport(DocumentBundle bundle, BusinessReport report) throws IOException {
        bundle.append(report);
        return bundle;
    }

    @Override
    @RolesAllowed({Roles.USER_BCMP_APPEND, FULFILLMENT_SERVICES_USER})
    public DocumentBundle appendBusinessReport(DocumentBundle bundle, List<BusinessReport> reports) throws IOException {
        bundle.appendBusinessReport(reports);
        return bundle;
    }

    /**
     * Overlay page numbers on every other page.
     *
     * @param bundle Target document.
     * @throws IOException Could not overlay page numbers.
     */
    @Override
    @RolesAllowed({Roles.USER_BCMP_ENUMERATE, FULFILLMENT_SERVICES_USER})
    public DocumentBundle enumeratePages(final DocumentBundle bundle)
            throws IOException {
        bundle.enumeratePages();
        return bundle;
    }

    /**
     * Apply a paper type selection to the bundle. This tells BC Mail Plus what
     * type of paper to use when printing the document. For transcripts, the
     * default paper type is used. For certificates, the type depends on the
     * certificate report type (e.g., Adult) and subtype (e.g., Independent).
     *
     * @param bundle The document bundle to update.
     * @return
     * @throws IOException Could not set the paper type.
     */
    @Override
    @RolesAllowed({Roles.USER_BCMP_XPIF, FULFILLMENT_SERVICES_USER})
    public DocumentBundle xpif(final DocumentBundle bundle)
            throws IOException {
        bundle.xpif();
        return bundle;
    }

    /**
     * Creates a default paper type that is used for transcripts.
     *
     * @return A paper type for printing transcripts.
     */
    @Override
    @RolesAllowed({Roles.USER_BCMP_TRANSCRIPT_ORDER_TYPE, FULFILLMENT_SERVICES_USER})
    public OrderType createTranscriptOrderType() {
        return new TranscriptOrderTypeImpl();
    }

    /**
     * Creates an order type that is used for certificates.
     *
     * @param certificateType The certificate type that dictates the paper to
     * use when printing via the order type.
     * @return An order type for printing certificates.
     */
    @Override
    @RolesAllowed({Roles.USER_BCMP_CERTIFICATE_ORDER_TYPE, FULFILLMENT_SERVICES_USER})
    public OrderType createCertificateOrderType(final CertificateType certificateType) {
        return new CertificateOrderTypeImpl(certificateType);
    }

    /**
     *
     * @param documentBundle
     * @return
     * @throws IOException
     * @inheritDoc
     */
    @Override
    @RolesAllowed({FULFILLMENT_SERVICES_USER})
    public DocumentBundle decorate(DocumentBundle documentBundle) throws IOException {
        final String _m = "decorate(DocumentBundle)";
        LOG.entering(CLASSNAME, _m);

        // Adorn the finished bundle with page numbers.
        documentBundle = enumeratePages(documentBundle);
        LOG.log(Level.FINE, "Enumerated pages on bundle.");

        // Adding the XPIF inserts an XML header into the PDF, which renders
        // the PDF unreadable by most PDF readers.
        documentBundle = xpif(documentBundle);
        LOG.log(Level.FINE, "Bundle size: {0} bytes", documentBundle.asBytes().length);

        LOG.exiting(CLASSNAME, _m);
        return documentBundle;
    }

    /**
     * Generates a random universally unique identifier.
     *
     * @return A 16-byte UUID encoded in Base 64, never null.
     * @deprecated Move into AbstractDomainEntity and eliminate this call from
     * unit tests. This method should be private, however there are some test
     * cases calling it. Those tests should do object creation using the Create
     * service method.
     */
    private String generateUUID64() {
        final String _m = "generateUUID64()";
        LOG.entering(CLASSNAME, _m);

        final UUID uuid = UUID.randomUUID();
        final ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());

        final String retValue = Base64.encodeBase64URLSafeString(bb.array());
        LOG.finer("Generated Base 64 encoded UUID.");

        assert retValue != null : "Post Condition Failed - Generated a Base 64 encoded UUID, but ended up with NULL!";

        LOG.exiting(CLASSNAME, _m, retValue);
        return retValue;
    }
}
