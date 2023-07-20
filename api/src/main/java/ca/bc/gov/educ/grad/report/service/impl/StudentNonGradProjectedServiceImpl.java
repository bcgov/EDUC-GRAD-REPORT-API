/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
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
import ca.bc.gov.educ.grad.report.dto.impl.StudentNonGradProjectedReportImpl;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.reports.GraduationReport;
import ca.bc.gov.educ.grad.report.model.reports.ReportService;
import ca.bc.gov.educ.grad.report.model.student.StudentNonGradProjectedReport;
import ca.bc.gov.educ.grad.report.model.student.StudentNonGradProjectedService;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
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
import static java.util.Locale.CANADA;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@Service
@DeclareRoles({STUDENT_CERTIFICATE_REPORT, USER})
public class StudentNonGradProjectedServiceImpl extends GradReportServiceImpl
        implements StudentNonGradProjectedService, Serializable {

    private static final long serialVersionUID = 2L;
    private static final String CLASSNAME = StudentNonGradProjectedServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    @Autowired
    private ReportService reportService;

    @Autowired
    GradDataConvertionBean gradDataConvertionBean;

    @Autowired
    JsonTransformer jsonTransformer;

    @RolesAllowed({STUDENT_CERTIFICATE_REPORT, USER})
    @Override
    public StudentNonGradProjectedReport buildStudentNonGradProjectedReport() throws DomainServiceException, IOException {
        final String methodName = "buildStudentNonGradProjectedReport()";
        LOG.entering(CLASSNAME, methodName);
        GraduationReport graduationReport = getGraduationReport(methodName, List.of("SCCP"));

        LOG.exiting(CLASSNAME, methodName);

        return createStudentNonGradProjectedReport(graduationReport);
    }

    /**
     * @param graduationReport
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private synchronized StudentNonGradProjectedReport createStudentNonGradProjectedReport(
            final GraduationReport graduationReport) throws DomainServiceException {

        final String methodName = "createStudentNonGradProjectedReport(GraduationReport)";
        LOG.entering(CLASSNAME, methodName);

        StudentNonGradProjectedReport report = null;
        try {

            byte[] rptData = getPdfReportAsBytes(graduationReport, methodName, "student_nongrad_projected_requirements_");

            // TODO: Use a constant for the name.
            report = new StudentNonGradProjectedReportImpl(rptData, PDF, graduationReport.getFilename(), createReportTypeName("Student NonGrad Projected Report", CANADA));
        } catch (final IOException ex) {
            LOG.log(Level.SEVERE,
                    "Failed to generate the Student Non Graduation Projected report: Message {0} payload {1}", new String[] {ex.getMessage(), jsonTransformer.marshall(graduationReport)});
        }

        LOG.exiting(CLASSNAME, methodName);
        return report;
    }

    @Override
    GraduationReport createGraduationReport() {
        return reportService.createStudentNonGradProjectedReport();
    }
}
