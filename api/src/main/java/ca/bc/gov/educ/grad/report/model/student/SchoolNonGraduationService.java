/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: GradCertificateService.java 6312 2017-02-25 00:#$
 *  Date of Last Commit: $Date:: 2017-02-24 16:29:23 -0800 (Fri, 24 Feb 2017)  $
 *  Revision Number:     $Rev:: 6312                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.model.student;

import ca.bc.gov.educ.grad.report.model.common.BusinessService;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.school.SchoolNonGraduationReport;

import java.io.IOException;

/**
 * A student's graduation status.
 *
 * Provides information of the status of a student in graduation programs. This
 * includes access to graduation certificates.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SchoolNonGraduationService extends BusinessService {

    /**
     * Generate the School Distribution report. School Distribution are
     * generated as PDF for the current school
     * <p>
     * @return Report data for consumption by the GUI.
     * <p>
     * @throws DomainServiceException
     */
    SchoolNonGraduationReport buildSchoolNonGraduationReport() throws DomainServiceException, IOException;

}
