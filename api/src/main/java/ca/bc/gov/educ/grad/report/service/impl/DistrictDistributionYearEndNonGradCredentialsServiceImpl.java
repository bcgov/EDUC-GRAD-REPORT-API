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
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.district.District;
import ca.bc.gov.educ.grad.report.model.reports.GraduationReport;
import ca.bc.gov.educ.grad.report.model.reports.Parameters;
import ca.bc.gov.educ.grad.report.model.reports.ReportService;
import ca.bc.gov.educ.grad.report.model.school.School;
import ca.bc.gov.educ.grad.report.model.school.SchoolDistributionReport;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.dto.impl.constants.Roles.STUDENT_CERTIFICATE_REPORT;
import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.USER;
import static java.util.Locale.CANADA;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@Service
@DeclareRoles({STUDENT_CERTIFICATE_REPORT, USER})
public class DistrictDistributionYearEndNonGradCredentialsServiceImpl extends SchoolDistributionServiceImpl implements Serializable {

    private static final long serialVersionUID = 2L;
    private static final String CLASSNAME = SchoolDistributionServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    @Autowired
    private ReportService reportService;

    @RolesAllowed({STUDENT_CERTIFICATE_REPORT, USER})
    @Override
    public SchoolDistributionReport buildSchoolDistributionReport() throws DomainServiceException, IOException {
        final String methodName = "buildSchoolDistributionReport()";
        LOG.entering(CLASSNAME, methodName);

        GraduationReport graduationReport = getGraduationReport(methodName, List.of());

        LOG.exiting(CLASSNAME, methodName);
        return createSchoolDistributionReport(graduationReport);
    }

    @Override
    GraduationReport getGraduationReport(String methodName, List<String> excludePrograms) throws IOException {
        Parameters<String, Object> parameters = createParameters();

        ReportData reportData = getReportData(methodName);

        LOG.log(Level.FINE,
                "Confirmed the district data exists and continue.");

        // validate incoming data for reporting
        final District district = getSchool(reportData);

        if (district != null) {
            parameters.put("district", district);
        }

        final List<School> schools = getSchools(reportData);
        sortSchools(schools);

        if(!schools.isEmpty()) {
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(schools);
            parameters.put("schools", jrBeanCollectionDataSource);
            parameters.put("hasSchools", "true");
        }

        addReportLogo(parameters, reportData);

        parameters.put("reportNumber", reportData.getReportNumber());
        parameters.put("reportTitle", reportData.getReportTitle());
        parameters.put("reportSubTitle", reportData.getReportSubTitle());

        GraduationReport graduationReport = createGraduationReport();
        graduationReport.setLocale(CANADA);
        graduationReport.setSchools(schools);
        graduationReport.setDistrict(district);
        graduationReport.setParameters(parameters);

        return graduationReport;
    }

    @Override
    GraduationReport createGraduationReport() {
        return reportService.createDistrictDistributionYearEndNonGradCredentialsReport();
    }

    private void sortSchools(List<School> schools) {
        schools.sort(Comparator.comparing(School::getName));
    }
}
