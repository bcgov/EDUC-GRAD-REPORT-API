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

import ca.bc.gov.educ.grad.report.api.service.utils.JsonTransformer;
import ca.bc.gov.educ.grad.report.dao.GradDataConvertionBean;
import ca.bc.gov.educ.grad.report.dto.impl.SchoolNonGraduationReportImpl;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.reports.GraduationReport;
import ca.bc.gov.educ.grad.report.model.reports.ReportService;
import ca.bc.gov.educ.grad.report.model.school.SchoolNonGraduationReport;
import ca.bc.gov.educ.grad.report.model.student.SchoolNonGraduationService;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.dto.impl.constants.Roles.STUDENT_CERTIFICATE_REPORT;
import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.USER;
import static ca.bc.gov.educ.grad.report.model.reports.ReportFormat.PDF;
import static ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants.LOG_TRACE_ENTERING;
import static ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants.LOG_TRACE_EXITING;
import static java.util.Locale.CANADA;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@Slf4j
@Service
@DeclareRoles({STUDENT_CERTIFICATE_REPORT, USER})
public class SchoolNonGraduationServiceImpl extends GradReportServiceImpl
        implements SchoolNonGraduationService, Serializable {

    private static final long serialVersionUID = 2L;
    private static final String CLASSNAME = SchoolNonGraduationServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    @Autowired
    private ReportService reportService;

    @Autowired
    GradDataConvertionBean gradDataConvertionBean;

    @Autowired
    JsonTransformer jsonTransformer;

    @RolesAllowed({STUDENT_CERTIFICATE_REPORT, USER})
    @Override
    public SchoolNonGraduationReport buildSchoolNonGraduationReport() throws DomainServiceException, IOException {
        final String methodName = "buildSchoolNonGraduationReport()";
        log.trace(LOG_TRACE_ENTERING, methodName);

        GraduationReport nonGraduationReport = getGraduationReport(methodName, List.of("SCCP"));

        log.trace(LOG_TRACE_EXITING, methodName);
        return createSchoolNonGraduationReport(nonGraduationReport);
    }

    /**
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private synchronized SchoolNonGraduationReport createSchoolNonGraduationReport(
            final GraduationReport graduationReport) throws DomainServiceException {
        final String methodName = "createSchoolNonGraduationReport(GraduationReport)";
        log.trace(LOG_TRACE_ENTERING, methodName);

        SchoolNonGraduationReport report = null;
        try {

            byte[] rptData = getPdfReportAsBytes(graduationReport);

            report = new SchoolNonGraduationReportImpl(rptData, PDF, graduationReport.getFilename(), createReportTypeName("School Non Graduation Report", CANADA));
        } catch (final IOException ex) {
            LOG.log(Level.SEVERE,
                    "Failed to generate the School Distribution report: Message {0} payload {1}", new String[] {ex.getMessage(), jsonTransformer.marshall(graduationReport)});
        }

        log.trace(LOG_TRACE_EXITING, methodName);
        return report;
    }

    @Override
    GraduationReport createGraduationReport() {
        return reportService.createSchoolNonGraduationReport();
    }
}
