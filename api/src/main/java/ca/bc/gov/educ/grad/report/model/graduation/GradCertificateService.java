/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
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
package ca.bc.gov.educ.grad.report.model.graduation;

import ca.bc.gov.educ.grad.report.model.common.BusinessReport;
import ca.bc.gov.educ.grad.report.model.common.BusinessService;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;

import java.util.List;

/**
 * A student's graduation status.
 *
 * Provides information of the status of a student in graduation programs. This
 * includes access to graduation certificates.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface GradCertificateService extends BusinessService {

    /**
     * Generate the student grad certificate report. Grad Certificates are
     * generated as PDF for the current user when certificates are ordered. If
     * the current user is not a student, then a DomainServiceException is
     * thrown. If the student has not graduated then a DomainServiceException is
     * thrown. If there is an error generating the report then a
     * DomainServiceException is thrown.
     * <p>
     * @return Report data for consumption by the GUI.
     * <p>
     * @throws DomainServiceException
     */
    List<BusinessReport> buildReport() throws DomainServiceException;

    /**
     * Generate the blank grad certificate report. Grad Certificates are
     * generated as PDF for the current user when certificates are ordered.
     * <p>
     * @return Report data for consumption by the GUI.
     * <p>
     * @throws DomainServiceException
     */
    List<BusinessReport> buildBlankReport() throws DomainServiceException;

}
