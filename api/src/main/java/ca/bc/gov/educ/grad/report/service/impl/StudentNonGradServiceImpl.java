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

import ca.bc.gov.educ.grad.report.dto.impl.StudentNonGradReportImpl;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.reports.GraduationReport;
import ca.bc.gov.educ.grad.report.model.student.StudentNonGradReport;
import ca.bc.gov.educ.grad.report.model.student.StudentNonGradService;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
@DeclareRoles({STUDENT_CERTIFICATE_REPORT, USER})
public class StudentNonGradServiceImpl extends GradReportServiceImpl
        implements StudentNonGradService, Serializable {

    private static final long serialVersionUID = 2L;
    private static final String CLASSNAME = StudentNonGradServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    @RolesAllowed({STUDENT_CERTIFICATE_REPORT, USER})
    @Override
    public StudentNonGradReport buildStudentNonGradReport() throws DomainServiceException, IOException {
        final String methodName = "buildStudentNonGradReport()";
        log.trace("Entering {}", methodName);
        GraduationReport graduationReport = getGraduationReport(methodName, List.of("SCCP"));

        log.trace("Exiting {}", methodName);

        return createStudentNonGradReport(graduationReport);
    }

    /**
     * @param graduationReport
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private synchronized StudentNonGradReport createStudentNonGradReport(
            final GraduationReport graduationReport) throws DomainServiceException {

        final String methodName = "createStudentNonGradReport(GraduationReport)";
        log.trace("Entering {}", methodName);

        StudentNonGradReport report = null;
        try {

            byte[] rptData = getPdfReportAsBytes(graduationReport, methodName, "student_nongrad_requirements_");
            report = new StudentNonGradReportImpl(rptData, PDF, graduationReport.getFilename(), createReportTypeName("Student NonGrad Report", CANADA));
        } catch (final IOException ex) {
            LOG.log(Level.SEVERE,
                    "Failed to generate the Student Non Graduation report: Message {0} payload {1}", new String[] {ex.getMessage(), jsonTransformer.marshall(graduationReport)});
        }

        log.trace("Exiting {}", methodName);
        return report;
    }

    @Override
    GraduationReport createGraduationReport() {
        return reportService.createStudentNonGradReport();
    }
}
