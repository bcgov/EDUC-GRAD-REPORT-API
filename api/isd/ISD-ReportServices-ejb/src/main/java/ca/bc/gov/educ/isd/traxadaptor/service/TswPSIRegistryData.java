/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
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
package ca.bc.gov.educ.isd.traxadaptor.service;

import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.trax.db.TRAXData;
import ca.bc.gov.educ.isd.eis.trax.db.TSWRegistry;

import java.util.List;

/**
 * An interface for the PSI Registry EJB that it is tied to the ISD's PSI
 * Registry table in TRAX database. It exposes the services that the EJB
 * provides in a distributed environments. Specifically, the services of
 * retrieving, updating, and inserting.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TswPSIRegistryData extends TRAXData {

    /**
     * Finds a PSI Registry row(s) by the PSI code.
     *
     * @param psiCode Code that identify the PSI.
     * @return
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    public List<? extends TSWRegistry> findRegistryBy(String psiCode) throws EISException;

    /**
     * Returns all PSIs found in TRAX.
     *
     * @return
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    public List<? extends TSWRegistry> findPSIs() throws EISException;

    /**
     * Returns all PSIs found in TRAX.
     *
     * @return
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    public List<? extends TSWRegistry> findBCPSIs() throws EISException;

    /**
     * Returns all PSIs found in TRAX with name similar to the passed argument .
     *
     * @param name PSI name to search based upon
     * @return
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    public List<? extends TSWRegistry> findPSIsByName(String name) throws EISException;

    /**
     * Returns all non-BC Canadian PSIs found in TRAX.
     *
     * @return
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    public List<? extends TSWRegistry> findNonBCCAPSIs() throws EISException;

    /**
     * Returns all non-Canadian PSIs found in TRAX.
     *
     * @return
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    public List<? extends TSWRegistry> findNonCAPSIs() throws EISException;

    /**
     * Inserts a new PSI Registry in the TRAX table.
     *
     * @param newPSIResgistry object that represents the data to be inserted in
     * the database.
     * @throws EISException
     */
    public void insertNewRegistry(final TSWRegistry newPSIResgistry) throws EISException;

    /**
     * Updates an existing PSI Registry in the TRAX table.
     *
     * @param psiCode Code that identify the PSI to be updated.
     * @param registryDataToUpdate object that contains the data to update.
     * @return
     * @throws EISException
     */
    public Boolean updateRegistry(final String psiCode, final TSWRegistry registryDataToUpdate) throws EISException;

    /**
     * Deletes an existing PSI Registry from the TRAX table.
     *
     * @param psiCode Code that identify the PSI to be deleted.
     * @return
     * @throws EISException
     */
    public Boolean deleteRegistry(final String psiCode) throws EISException;

}
