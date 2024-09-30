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

import ca.bc.gov.educ.grad.report.dto.impl.StudentGradProjectedReportImpl;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.reports.GraduationReport;
import ca.bc.gov.educ.grad.report.model.student.StudentGradProjectedReport;
import ca.bc.gov.educ.grad.report.model.student.StudentGradProjectedService;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
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
public class StudentGradProjectedServiceImpl extends GradReportServiceImpl
        implements StudentGradProjectedService, Serializable {

    private static final long serialVersionUID = 2L;
    private static final String CLASSNAME = StudentGradProjectedServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    @RolesAllowed({STUDENT_CERTIFICATE_REPORT, USER})
    @Override
    public StudentGradProjectedReport buildStudentGradProjectedReport() throws DomainServiceException, IOException {
        final String methodName = "buildStudentGradProjectedReport()";
        LOG.entering(CLASSNAME, methodName);
        GraduationReport graduationReport = getGraduationReport(methodName, List.of("SCCP"));

        LOG.exiting(CLASSNAME, methodName);

        return createStudentGradProjectedReport(graduationReport);
    }

    /**
     * @param graduationReport
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private synchronized StudentGradProjectedReport createStudentGradProjectedReport(
            final GraduationReport graduationReport) throws DomainServiceException {

        final String methodName = "createStudentGradProjectedReport(GraduationReport)";
        LOG.entering(CLASSNAME, methodName);

        StudentGradProjectedReport report = null;
        try {

            byte[] rptData = getPdfReportAsBytes(graduationReport, methodName, "student_grad_projected_requirements_");
            report = new StudentGradProjectedReportImpl(rptData, PDF, graduationReport.getFilename(), createReportTypeName("Student Grad Projected Report", CANADA));
        } catch (final IOException ex) {
            LOG.log(Level.SEVERE,
                    "Failed to generate the Student Graduation Projected report: Message {0} payload {1}", new String[] {ex.getMessage(), jsonTransformer.marshall(graduationReport)});
        }

        LOG.exiting(CLASSNAME, methodName);
        return report;
    }

    @Override
    GraduationReport createGraduationReport() {
        return reportService.createStudentGradProjectedReport();
    }
}
