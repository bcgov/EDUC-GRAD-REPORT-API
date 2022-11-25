/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education and Child Care, BC.
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
package ca.bc.gov.educ.grad.report.service.impl;

import ca.bc.gov.educ.grad.report.dao.GradDataConvertionBean;
import ca.bc.gov.educ.grad.report.dto.impl.SchoolDistributionReportImpl;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.reports.GraduationReport;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.reports.ReportService;
import ca.bc.gov.educ.grad.report.model.school.SchoolDistributionReport;
import ca.bc.gov.educ.grad.report.model.school.SchoolDistributionService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.dto.impl.constants.Roles.STUDENT_CERTIFICATE_REPORT;
import static ca.bc.gov.educ.grad.report.model.common.Constants.DATE_ISO_8601_FULL;
import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.USER;
import static ca.bc.gov.educ.grad.report.model.reports.ReportFormat.PDF;
import static java.util.Locale.CANADA;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@Service
@DeclareRoles({STUDENT_CERTIFICATE_REPORT, USER})
public class SchoolDistributionServiceImpl extends GradReportServiceImpl
        implements SchoolDistributionService, Serializable {

    private static final long serialVersionUID = 2L;
    private static final String CLASSNAME = SchoolDistributionServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);
    private static final String REPORT_DATA_MISSING = "REPORT_DATA_MISSING";

    @Autowired
    private ReportService reportService;

    @Autowired
    GradDataConvertionBean gradDataConvertionBean;

    @RolesAllowed({STUDENT_CERTIFICATE_REPORT, USER})
    @Override
    public SchoolDistributionReport buildSchoolDistributionReport() throws DomainServiceException, IOException {
        final String methodName = "buildSchoolDistributionReport()";
        LOG.entering(CLASSNAME, methodName);

        GraduationReport graduationReport = getGraduationReport(methodName, List.of());

        LOG.exiting(CLASSNAME, methodName);
        return createSchoolDistributionReport(graduationReport);
    }

    /**
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private synchronized SchoolDistributionReport createSchoolDistributionReport(final GraduationReport graduationReport) throws DomainServiceException {

        final String methodName = "createSchoolDistributionReport(GraduationReport)";
        LOG.entering(CLASSNAME, methodName);

        SchoolDistributionReport report = null;
        try {

            String timestamp = new SimpleDateFormat(DATE_ISO_8601_FULL).format(new Date());
            final ReportDocument rptDoc = reportService.export(graduationReport);

            StringBuilder sb = new StringBuilder("school_distribution_");
            sb.append(CANADA.toLanguageTag());
            sb.append("_");
            sb.append(timestamp);
            sb.append(".");
            sb.append(PDF.getFilenameExtension());
            final String filename = graduationReport.getFilename();

            byte[] inData = rptDoc.asBytes();
            inData = ArrayUtils.nullToEmpty(inData);
            if (ArrayUtils.isEmpty(inData)) {
                String msg = "The generated report output is empty.";
                DomainServiceException dse = new DomainServiceException(null,
                        msg);
                LOG.throwing(CLASSNAME, methodName, dse);
                throw dse;
            }
            byte[] rptData = inData;

            // TODO: Use a constant for the name.
            report = new SchoolDistributionReportImpl(rptData, PDF, filename, createReportTypeName("School Distribution Report", CANADA));
        } catch (final IOException ex) {
            LOG.log(Level.SEVERE,
                    "Failed to generate the School Distribution report.", ex);
        }

        LOG.exiting(CLASSNAME, methodName);
        return report;
    }

    @Override
    GraduationReport createGraduationReport() {
        return reportService.createSchoolDistributionReport();
    }

}
