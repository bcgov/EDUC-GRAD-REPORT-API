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
package ca.bc.gov.educ.isd.grad;

import ca.bc.gov.educ.isd.common.BusinessProcessException;
import ca.bc.gov.educ.isd.common.BusinessReport;
import ca.bc.gov.educ.isd.common.BusinessService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.party.Identifier;
import ca.bc.gov.educ.isd.common.party.address.Address;
import ca.bc.gov.educ.isd.student.PersonalEducationNumber;

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
     * Indicates if the user has graduated (in BC).
     *
     * @param pen The personal education number for the student.
     * @return true if the user has a graduation date in the
     */
    Boolean checkGrad(PersonalEducationNumber pen);

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

    String sendReport(String orderXRef, Identifier partyId, Address addr)
            throws BusinessProcessException, DomainServiceException;

    String checkDelivery(String refNo) throws DomainServiceException;

    List<String> listTrackingNo(String orderXRef) throws DomainServiceException;

}
