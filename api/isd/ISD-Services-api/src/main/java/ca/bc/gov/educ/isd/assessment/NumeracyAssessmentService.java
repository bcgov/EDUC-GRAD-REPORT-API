/*
 * *********************************************************************
 *  Copyright (c) 2018, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: NumeracyAssessmentService.java 11041 2018-07-31#$
 *  Date of Last Commit: $Date:: 2018-07-31 12:18:43 -0700 (Tue, 31 Jul 2018)  $
 *  Revision Number:     $Rev:: 11041                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.assessment;

import ca.bc.gov.educ.isd.common.BusinessReport;
import ca.bc.gov.educ.isd.common.BusinessService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.reports.ReportFormat;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface NumeracyAssessmentService extends BusinessService {

    /**
     * Generate the numeracy assessment report.
     *
     * @param sessionDate School year student was evaluated.
     * @param assessmentTypeCode Literacy or numeracy (e.g., "NME").
     * @param format HTML or PDF.
     *
     * @return Report data for consumption by the GUI.
     *
     * @throws DomainServiceException
     */
    BusinessReport buildReport(
            String sessionDate,
            AssessmentCourseCode assessmentTypeCode,
            ReportFormat format) throws DomainServiceException;
}
