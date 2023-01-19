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

import ca.bc.gov.educ.grad.report.api.client.ReportData;
import ca.bc.gov.educ.grad.report.dao.GradDataConvertionBean;
import ca.bc.gov.educ.grad.report.dto.impl.SchoolLabelReportImpl;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.reports.GraduationReport;
import ca.bc.gov.educ.grad.report.model.reports.Parameters;
import ca.bc.gov.educ.grad.report.model.reports.ReportService;
import ca.bc.gov.educ.grad.report.model.school.School;
import ca.bc.gov.educ.grad.report.model.school.SchoolLabelReport;
import ca.bc.gov.educ.grad.report.model.school.SchoolLabelService;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
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
public class SchoolLabelServiceImpl extends GradReportServiceImpl
        implements SchoolLabelService, Serializable {

    private static final long serialVersionUID = 2L;
    private static final String CLASSNAME = SchoolLabelServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    @Autowired
    private ReportService reportService;

    @Autowired
    GradDataConvertionBean gradDataConvertionBean;

    @RolesAllowed({STUDENT_CERTIFICATE_REPORT, USER})
    @Override
    public SchoolLabelReport buildSchoolLabelReport() throws DomainServiceException, IOException {
        final String methodName = "buildSchoolLabelReport()";
        LOG.entering(CLASSNAME, methodName);

        GraduationReport graduationReport = getGraduationReport(methodName, List.of());

        LOG.exiting(CLASSNAME, methodName);
        return createSchoolLabelReport(graduationReport);
    }

    GraduationReport getGraduationReport(String methodName, List<String> excludePrograms) throws IOException {
        Parameters<String, Object> parameters = createParameters();

        ReportData reportData = getReportData(methodName);

        LOG.log(Level.FINE,
                "Confirmed the user is a student and retrieved the PEN.");

        final List<School> schools = getSchools(reportData);

        if(!schools.isEmpty()) {
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(schools);
            parameters.put("schools", jrBeanCollectionDataSource);
            parameters.put("hasSchools", "true");
        }

        InputStream inputLogo = openImageResource("logo_" + reportData.getOrgCode().toLowerCase(Locale.ROOT) + ".svg");
        parameters.put("orgImage", inputLogo);

        parameters.put("reportNumber", reportData.getReportNumber());

        GraduationReport graduationReport = createGraduationReport();
        graduationReport.setLocale(CANADA);
        graduationReport.setSchools(schools);
        graduationReport.setParameters(parameters);

        return graduationReport;
    }

    /**
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private synchronized SchoolLabelReport createSchoolLabelReport(final GraduationReport graduationReport) throws DomainServiceException {

        final String methodName = "createSchoolLabelReport(GraduationReport)";
        LOG.entering(CLASSNAME, methodName);

        SchoolLabelReport report = null;
        try {

            byte[] rptData = getPdfReportAsBytes(graduationReport, methodName, "school_label_");

            // TODO: Use a constant for the name.
            report = new SchoolLabelReportImpl(rptData, PDF, graduationReport.getFilename(), createReportTypeName("School Label Report", CANADA));
        } catch (final IOException ex) {
            LOG.log(Level.SEVERE,
                    "Failed to generate the School Label report.", ex);
        }

        LOG.exiting(CLASSNAME, methodName);
        return report;
    }

    @Override
    GraduationReport createGraduationReport() {
        return reportService.createSchoolLabelReport();
    }

}
