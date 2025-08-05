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

import ca.bc.gov.educ.grad.report.model.reports.GraduationReport;
import jakarta.annotation.security.DeclareRoles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

import static ca.bc.gov.educ.grad.report.dto.impl.constants.Roles.STUDENT_TRANSCRIPT_REPORT;
import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.USER;
import static ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants.LOG_TRACE_ENTERING;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@Slf4j
@Service
@DeclareRoles({STUDENT_TRANSCRIPT_REPORT, USER})
public class DistrictDistributionYearEndNonGradCredentialsServiceImpl extends DistrictDistributionYearEndCredentialsServiceImpl implements Serializable {

    private static final long serialVersionUID = 2L;
    static final String CLASSNAME = DistrictDistributionYearEndNonGradCredentialsServiceImpl.class.getName();

    @Override
    GraduationReport createGraduationReport() {
        final String methodName = "createGraduationReport()";
        log.trace(LOG_TRACE_ENTERING, methodName);
        return reportService.createDistrictDistributionYearEndNonGradCredentialsReport();
    }
}
