/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: CertificateReport.java 4514 2016-10-20 18:38:12#$
 *  Date of Last Commit: $Date:: 2016-10-20 11:38:12 -0700 (Thu, 20 Oct 2016)  $
 *  Revision Number:     $Rev:: 4514                                           $
 *  Last Commit by:      $Author:: cprince                                     $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.reports;

import ca.bc.gov.educ.isd.cert.Certificate;

/**
 * Represents information required to generate a certificate for a student.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface CertificateReport extends StudentReport {

    /**
     * Sets the type of report to generate. This value is passed into the report
     * as the P_REPORT_TYPE parameter.
     *
     * @param crt Non-null certificate type.
     */
    void setReportType(CertificateType crt);

    /**
     * Sets the report subtype to generate. This value is passed into the report
     * as the P_REPORT_SUBTYPE parameter.
     *
     * @param crs Will be INDEPENDENT, FRANCOPHONE, or not set (DEFAULT).
     */
    void setReportSubtype(CertificateSubType crs);

    /**
     * Sets the certificate information to present on the report.
     *
     * @param certificate Contains issue date.
     */
    void setCertificate(Certificate certificate);
}
