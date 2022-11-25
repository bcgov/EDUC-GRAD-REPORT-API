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

import ca.bc.gov.educ.grad.report.api.client.ReportData;
import ca.bc.gov.educ.grad.report.dao.GradDataConvertionBean;
import ca.bc.gov.educ.grad.report.dto.impl.StudentNonGradReportImpl;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.reports.GraduationReport;
import ca.bc.gov.educ.grad.report.model.reports.Parameters;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.reports.ReportService;
import ca.bc.gov.educ.grad.report.model.school.School;
import ca.bc.gov.educ.grad.report.model.student.Student;
import ca.bc.gov.educ.grad.report.model.student.StudentNonGradReport;
import ca.bc.gov.educ.grad.report.model.student.StudentNonGradService;
import ca.bc.gov.educ.grad.report.utils.TotalCounts;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
public class StudentNonGradServiceImpl extends GradReportServiceImpl
        implements StudentNonGradService, Serializable {

    private static final long serialVersionUID = 2L;
    private static final String CLASSNAME = StudentNonGradServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);
    private static final String REPORT_DATA_MISSING = "REPORT_DATA_MISSING";

    @Autowired
    private ReportService reportService;

    @Autowired
    GradDataConvertionBean gradDataConvertionBean;

    @RolesAllowed({STUDENT_CERTIFICATE_REPORT, USER})
    @Override
    public StudentNonGradReport buildStudentNonGradReport() throws DomainServiceException, IOException {
        final String methodName = "buildStudentNonGradReport()";
        LOG.entering(CLASSNAME, methodName);


        ReportData reportData = getReportData(methodName);

        LOG.log(Level.FINE,
                "Confirmed the user is a student and retrieved the PEN.");

        Parameters<String, Object> parameters = createParameters();

        // validate incoming data for reporting
        final Pair<List<Student>, TotalCounts> studentsResult = getStudents(reportData, List.of("SCCP")); //validated
        final List<Student> students = studentsResult.getFirst();
        final TotalCounts counts = studentsResult.getSecond();
        final School school = getSchool(reportData); //validated

        if(!students.isEmpty()) {
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(students);
            parameters.put("students", jrBeanCollectionDataSource);
            parameters.put("hasStudents", "true");
        }

        parameters.put("counts", counts);

        if (school != null) {
            parameters.put("school", school);
        }

        InputStream inputLogo = openImageResource("logo_" + reportData.getOrgCode().toLowerCase(Locale.ROOT) + ".svg");
        parameters.put("orgImage", inputLogo);

        parameters.put("reportNumber", reportData.getReportNumber());
        parameters.put("reportTitle", reportData.getReportTitle());
        parameters.put("reportSubTitle", reportData.getReportSubTitle());

        final StudentNonGradReport report = createReport(
                students, school, parameters, CANADA);


        LOG.exiting(CLASSNAME, methodName);
        return report;
    }

    /**
     * @param students
     * @param school
     * @param location
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private synchronized StudentNonGradReport createReport(
            final List<Student> students,
            final School school,
            final Parameters parameters,
            final Locale location) throws DomainServiceException {

        final String methodName = "createReport(Student, School, Locale)";
        LOG.entering(CLASSNAME, methodName);

        String timestamp = new SimpleDateFormat(DATE_ISO_8601_FULL).format(new Date());

        GraduationReport nonGradRequirementsReport = createGraduationReport();
        nonGradRequirementsReport.setLocale(location);
        nonGradRequirementsReport.setStudents(students);
        nonGradRequirementsReport.setSchool(school);

        if (parameters != null) {
            nonGradRequirementsReport.setParameters(parameters);
        }

        StudentNonGradReport report = null;
        try {
            final ReportDocument rptDoc = reportService.export(nonGradRequirementsReport);

            StringBuilder sb = new StringBuilder("student_nongrad_requirements_");
            sb.append(location.toLanguageTag());
            sb.append("_");
            sb.append(timestamp);
            sb.append(".");
            sb.append(PDF.getFilenameExtension());
            final String filename = nonGradRequirementsReport.getFilename();

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
            report = new StudentNonGradReportImpl(rptData, PDF, filename, createReportTypeName(location));
        } catch (final IOException ex) {
            LOG.log(Level.SEVERE,
                    "Failed to generate the provincial examination report.", ex);
        }

        LOG.exiting(CLASSNAME, methodName);
        return report;
    }

    private String createReportTypeName(
            final Locale locale) {
        final String reportTypeName
                = "Student NonGrad Requirements Report"
                + " "
                + locale.getISO3Language();
        return reportTypeName;
    }

    @Override
    GraduationReport createGraduationReport() {
        return reportService.createNonGradReport();
    }
}
