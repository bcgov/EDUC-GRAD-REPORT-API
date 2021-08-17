/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: ReportService.java 12336 2019-12-13 19:30:20Z c#$
 *  Date of Last Commit: $Date:: 2019-12-13 11:30:20 -0800 (Fri, 13 Dec 2019)  $
 *  Revision Number:     $Rev:: 12336                                          $
 *  Last Commit by:      $Author:: cfunk                                       $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.reports;

import ca.bc.gov.educ.isd.assessment.LiteracyAssessmentReport;
import ca.bc.gov.educ.isd.assessment.NumeracyAssessmentReport;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.Receipt;
import ca.bc.gov.educ.isd.transcript.ParameterPredicate;

import java.util.List;

/**
 * Responsible for providing a mechanism to obtain reports.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ReportService extends ReportExportService {

    /**
     * Creates a transcript of grades report instance that can be exported into
     * a final report document. The calling class is responsible for setting the
     * required fields on the report instance returned from this method.
     *
     * @return A non-null report instance that must be populated with data.
     */
    TranscriptReport createTranscriptReport();

    /**
     * Creates a provincial examination report instance that can be exported
     * into a final report document. The calling class is responsible for
     * setting the required fields on the report instance returned from this
     * method.
     *
     * @return A non-null report instance that must be populated with data.
     */
    ProvincialExaminationReport createProvincialExaminationReport();

    /**
     * Creates a numeracy assessment report instance that can be exported into a
     * final report document. The calling class is responsible for setting the
     * required fields on the report instance returned from this method.
     *
     * @return A non-null report instance that must be populated with data.
     */
    NumeracyAssessmentReport createNumeracyAssessmentReport();

    /**
     * Creates a literacy assessment report instance that can be exported into a
     * final report document. The calling class is responsible for setting the
     * required fields on the report instance returned from this method.
     *
     * @return A non-null report instance that must be populated with data.
     */
    LiteracyAssessmentReport createLiteracyAssessmentReport();

    /**
     * Creates a scholarship report instance that can be exported into a final
     * report document. The calling class is responsible for setting the
     * required fields on the report instance returned from this method.
     *
     * @return A non-null report instance that must be populated with data.
     */
    ScholarshipReport createScholarshipReport();

    /**
     * Creates a packing slip report instance that can be exported into a final
     * report document. The calling class is responsible for setting the
     * required fields on the report instance returned from this method.
     *
     * @return A non-null report instance that must be populated with data.
     */
    PackingSlipReport createPackingSlipReport();

    /**
     * Creates a certificate report instance that can be exported into a final
     * report document. The calling class is responsible for setting the
     * required fields on the report instance returned from this method.
     *
     * @return A non-null report instance that must be populated with data.
     */
    CertificateReport createCertificateReport();

    /**
     * Creates a receipt report instance that can be exported into a final
     * report document. The calling class must set the required fields on the
     * report instance returned from this method.
     *
     * @param receipts The non-null, but possibly empty, list of receipts to
     * include in the report.
     * @return A non-null report instance that must be populated with data.
     */
    ReceiptReport createReceiptReport(List<Receipt> receipts);

    /**
     * Creates a ReportDocument initialized with the contents of the passed byte
     * array.
     *
     * @param bytes The byte content of an existing report document.
     * @return A non-null instance with the given array as a primitive via
     * asBytes.
     */
    ReportDocument createReportDocument(byte[] bytes);

    /**
     * Allows the report to filter its parameter map prior to processing. This
     * is used, for example, when the XmlExporter creates an XML document using
     * values from a parameter map that start with a specific prefix. Without
     * the predicate, there would be no way to differentiate between the
     * parameters required for general report export and parameters required for
     * creating XML elements (document fragments). The predicate acts on the
     * parameter keys.
     *
     * @return The filter for report parameter key/value pairs.
     */
    ParameterPredicate createParameterPredicate();

    /**
     * TODO: How are parameters used?
     *
     * @return How are parameters used?
     */
    Parameters createParameters();

    /**
     * Creates an achievement  instance that can be exported into
     * a final report document. The calling class is responsible for setting the
     * required fields on the report instance returned from this method.
     *
     * @return A non-null report instance that must be populated with data.
     */
    AchievementReport createAchievementReport();
}
