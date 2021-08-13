/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 * 
 *  Revision Control Information
 *  File:                PSISelectionService.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.psiselections;

import ca.bc.gov.educ.isd.common.BusinessService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import java.util.List;

/**
 * Service for handling student PSI Selections.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PSISelectionService extends BusinessService {

    /**
     * Writes the given information as a PSISelection with PSI_STATUS = 'A'.
     *
     * @param pen pen for the given student
     * @param psiCode code for the PSI
     * @param psiYear year for the selection
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    void writePSISelection(final String pen, final String psiCode, final String psiYear) throws DomainServiceException;

    /**
     * Writes the given information as a PSISelection with PSI_STATUS = 'A'.
     *
     * @param selection PSI selection to write
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    void writePSISelection(final PSISelection selection) throws DomainServiceException;

    /**
     * Returns all stored PSI selections for the passed PEN.
     *
     * @param pen student number of interest
     * @return List of matches
     * @throws DomainServiceException
     */
    List<PSISelection> readPSISelection(final String pen) throws DomainServiceException;

    /**
     * Deletes all PSI selections that match the passed criteria.
     *
     * @param pen student number
     * @param psiCode PSI code
     * @param psiYear year
     * @throws DomainServiceException
     */
    void deletePSISelection(final String pen, final String psiCode, final String psiYear) throws DomainServiceException;

}
