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
 *  File:                TswTxPsiData.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.traxadaptor.service;

import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.trax.db.TRAXData;
import ca.bc.gov.educ.isd.eis.trax.db.TSWTxPSI;

import java.util.Date;
import java.util.List;

/**
 * An interface for the TXPsi EJB that it is tied to the ISD's TX PSI table in
 * TRAX database. It exposes the services that the EJB provides in a distributed
 * environments. Specifically, the services of retrieving, updating, and
 * inserting.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TswTxPsiData extends TRAXData {

    /**
     * Finds a PSI Choice by the student number, also called PEN.
     *
     * @param studNo Number that identify the student
     * @return A list of PSI choices that belong to the student.
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    public List<? extends TSWTxPSI> findTxPSIBy(String studNo) throws EISException;

    /**
     * Finds a PSI Choice by its primary key. Which result in returning a unique
     * PSI registry.
     *
     * @param studNo Number that identify the student
     * @param txId TX id that identify the transaction
     * @param psiCode Code that identify the PSI.
     * @return A list of TX PSI that belong to the student, for an specific TX
     * id and for a specific PSI code.
     * @throws EISException
     */
    public List<? extends TSWTxPSI> findTxPSIBy(final String txId, final String studNo, final String psiCode) throws EISException;

    /**
     * Finds a PSI Choice which process date is later than the process date
     * passed, and unique index.
     *
     * @param studNo Number that identify the student
     * @param txId TX id that identify the transaction
     * @param psiCode Code that identify the PSI.
     * @param processDate Date in which TRAX processed the registry.
     * @return A list of TX PSI.
     * @throws EISException
     */
    List<? extends TSWTxPSI> findTxPSIBy(final String txId, final String studNo, final String psiCode, final Date processDate) throws EISException;

    /**
     * Inserts a new PSI choices in the TRAX table.
     *
     * @param txId TX id that identify the transaction
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param status Status of the PSI choice.
     * @throws EISException
     */
    public void insertNewTxPSI(final String txId, final String studNo, final String psiCode, final Character status) throws EISException;

    /**
     * Updates an existing PSI Choice in the TRAX table. It updates the Status
     * of the PSI Choice.
     *
     * @param txId TX id that identify the transaction
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @param status Status of the PSI choice.
     * @return
     * @throws EISException
     */
    public Boolean updateTxPSIStatus(final String txId, final String studNo, final String psiCode, final Character status) throws EISException;

    /**
     * Deletes an existing PSI Choice in the TRAX table.
     *
     * @param txId TX id that identify the transaction
     * @param studNo Number that identify the student
     * @param psiCode Code that identify the PSI.
     * @throws EISException
     */
    public void deleteTxPSI(final String txId, final String studNo, final String psiCode) throws EISException;
}
