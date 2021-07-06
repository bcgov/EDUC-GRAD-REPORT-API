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
import ca.bc.gov.educ.isd.eis.trax.db.TSWStud;

import java.util.List;

/**
 * An interface for the PSIChoices EJB that it is tied to the ISD's PSIChoices
 * table in TRAX database. It exposes the services that the EJB provides in a
 * distributed environments. Specifically, the services of retrieving, updating,
 * and inserting.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TswStudPSIData extends TRAXData {

    /**
     * Finds a PSI Choice by the student number, also called PEN.
     *
     * @param studNo Number that identify the student
     * @return A list of PSI choices that belong to the student.
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    public List<? extends TSWStud> findChoiceBy(final String studNo) throws EISException;

    /**
     * Finds a PSI Choice by its primary key. Which result in returning a unique
     * PSI registry.
     *
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param psiYear Year in which the choice was made.
     * @return A list of PSI choices that belong to the student, on a specific
     * year and for a specific PSI code.
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    public List<? extends TSWStud> findChoiceBy(final String studNo, final String psiCode, final String psiYear) throws EISException;

    /**
     * Inserts a new PSI choices in the TRAX table.
     *
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param psiYear Year in which the choice was made.
     * @param status Status of the PSI choice.
     * @throws EISException
     */
    public void insertNewChoice(final String studNo, final String psiCode, final String psiYear, final Character status) throws EISException;

    /**
     * Updates an existing PSI Choice in the TRAX table. It updates the Status
     * of the PSI Choice.
     *
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param psiYear Year in which the choice was made.
     * @param status Status of the PSI choice.
     * @return true successful.
     * @throws EISException
     */
    public Boolean updateChoiceStatus(final String studNo, final String psiCode, final String psiYear, final Character status) throws EISException;

    /**
     * Deletes an existing PSI Choice from the TRAX table. It updates the Status
     * of the PSI Choice.
     *
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param psiYear Year in which the choice was made.
     * @return true successful.
     * @throws EISException
     */
    public Boolean deleteChoice(final String studNo, final String psiCode, final String psiYear) throws EISException;
}
