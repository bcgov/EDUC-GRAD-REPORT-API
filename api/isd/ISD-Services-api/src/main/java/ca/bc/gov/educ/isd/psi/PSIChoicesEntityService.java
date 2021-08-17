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
package ca.bc.gov.educ.isd.psi;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;
import java.util.Date;

/**
 * An interface for the PSIChoices EJB that it is tied to the ISD's PSIChoices
 * table in ISD database. It exposes the services that the EJB provides in a
 * distributed environments. Specifically, the services of retrieving, updating,
 * and inserting.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PSIChoicesEntityService extends CommonEntityService<PSIChoices, SearchResult> {

    /**
     * Write PSI Choices data if there is not another registry that shares the
     * information on the parameters.
     *
     * @param studNo Student number used to verify if the STs data should be
     * updated.
     * @param psiCode PSI code used to verify if the STs data should be updated.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    void writePSIChoices(final String studNo, final String psiCode) throws DomainServiceException;

    /**
     * Updates PSI Choices data if there is not another registry that shares the
     * information on the parameters.
     *
     * @param studNo Student number used to verify if the STs data should be
     * updated.
     * @param psiCode PSI code used to verify if the STs data should be updated.
     * @param processDate date when the TRAX process ran. STs registry is going
     * to be updated with this date.
     * @param psiStatus status assigned to TRAX registry. STs registry is going
     * to be updated with this status.
     * @throws DomainServiceException
     */
    void updatePSIChoices(final String studNo, final String psiCode, final Date processDate, final char psiStatus) throws DomainServiceException;

}
