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

import ca.bc.gov.educ.grad.report.dto.impl.SchoolDistributionReportImpl;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.reports.GraduationReport;
import ca.bc.gov.educ.grad.report.model.school.SchoolDistributionReport;
import ca.bc.gov.educ.grad.report.model.school.SchoolDistributionService;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

import static ca.bc.gov.educ.grad.report.dto.impl.constants.Roles.STUDENT_CERTIFICATE_REPORT;
import static ca.bc.gov.educ.grad.report.dto.impl.constants.Roles.STUDENT_TRANSCRIPT_REPORT;
import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.USER;
import static ca.bc.gov.educ.grad.report.model.reports.ReportFormat.PDF;
import static java.util.Locale.CANADA;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@Service
@DeclareRoles({STUDENT_TRANSCRIPT_REPORT, USER})
public class SchoolDistributionServiceImpl extends GradReportServiceImpl
        implements SchoolDistributionService, Serializable {

    private static final long serialVersionUID = 2L;
    static final String CLASSNAME = SchoolDistributionServiceImpl.class.getName();

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
    protected synchronized SchoolDistributionReport createSchoolDistributionReport(final GraduationReport graduationReport) throws DomainServiceException {

        final String methodName = "createSchoolDistributionReport(GraduationReport)";
        LOG.entering(CLASSNAME, methodName);

        SchoolDistributionReport report = null;
        try {

            byte[] rptData = getPdfReportAsBytes(graduationReport, methodName, "school_distribution_");
            report = new SchoolDistributionReportImpl(rptData, PDF, graduationReport.getFilename(), createReportTypeName("School Distribution Report", CANADA));
        } catch (final IOException ex) {
            LOG.log(Level.SEVERE,
                    "Failed to generate the School Distribution report: Message {0} payload {1}", new String[] {ex.getMessage(), jsonTransformer.marshall(graduationReport)});
        }

        LOG.exiting(CLASSNAME, methodName);
        return report;
    }

    @Override
    GraduationReport createGraduationReport() {
        final String methodName = "createGraduationReport()";
        LOG.entering(CLASSNAME, methodName);
        return reportService.createSchoolDistributionReport();
    }

}
