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
 *  File:                PSIRegistryEntityService.java
 *  Date of Last Commit: $Date:: 2017-07-11 14:23:11 -0700 (Tue, 11 Jul 2017)  $
 *  Revision Number:     $Rev:: 7691                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.psi;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;
import java.util.List;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PSIRegistryEntityService extends CommonEntityService<PSIRegistry, SearchResult> {

    /**
     * Returns all LIKE matches with the given passed name.
     *
     * @param name to be used in the LIKE search on PSI name
     * @return
     * @throws DomainServiceException
     */
    List<PSIRegistry> searchPSIByName(String name) throws DomainServiceException;

    /**
     * Searches in TRAX for the PSI based on the passed TRAX psiCode.
     *
     * @param code
     * @return
     * @throws DomainServiceException
     */
    PSIRegistry searchPSIByCode(String code) throws DomainServiceException;

    /**
     * Returns all BC PSIs.
     *
     * @return
     * @throws DomainServiceException
     */
    List<PSIRegistry> getAllBCPSIs() throws DomainServiceException;

    /**
     * Returns all non-BC Canadian PSIs.
     *
     * @return
     * @throws DomainServiceException
     */
    List<PSIRegistry> getCANonBCPSIs() throws DomainServiceException;

    /**
     * Returns all non-Canadian PSIs.
     *
     * @return
     * @throws DomainServiceException
     */
    List<PSIRegistry> getAllInternationalPSIs() throws DomainServiceException;

    /**
     * Returns all PSIs.
     *
     * @return
     * @throws DomainServiceException
     */
    List<PSIRegistry> getAllPSIs() throws DomainServiceException;

    /**
     * Deletes an existing PSI Registry from the ISD and TRAX table.
     *
     * @param psiCode Code that identifies the PSI Registry to delete.
     * @throws DomainServiceException
     */
    public void deleteRegistry(String psiCode) throws DomainServiceException;

    /**
     * Returns the transmission mode that corresponds to the PSI having the
     * given code.
     *
     * @param psiCode The PSI code of an active PSI that has various
     * transmission modes for student transcripts.
     * @return The transmission mode for the given PSI.
     * @throws DomainServiceException Could not find the PSI by its code.
     */
    TransmissionMode findTransmissionMode(String psiCode) throws DomainServiceException;

}
