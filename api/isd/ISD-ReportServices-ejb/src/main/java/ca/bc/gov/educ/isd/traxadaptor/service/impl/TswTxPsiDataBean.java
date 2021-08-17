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
 *  File:                TswTxPsiDataBean.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.traxadaptor.service.impl;

import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.trax.db.TSWTxPSI;
import ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl.TswTxPsiEntity;
import ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl.TswTxPsiId;
import ca.bc.gov.educ.isd.traxadaptor.impl.TSWTxPSIImpl;
import ca.bc.gov.educ.isd.traxadaptor.service.TswTxPsiData;
import ca.bc.gov.educ.isd.traxadaptor.utils.ExceptionUtilities;
import org.springframework.stereotype.Repository;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.eis.roles.Roles.FULFILLMENT_SERVICES_USER;
import static ca.bc.gov.educ.isd.eis.roles.Roles.TRAX_READ;

/**
 * This is an intermediate layer between the database entities and the unmanaged
 * data objects which are returned to the requesting services. It is a stateless
 * bean providing access to the container managed persistence. The EJB provides
 * methods to support the insertions and updates in TX PSI table that exist in
 * TRAX database.
 *
 * @author CGI Information Management Consultants Inc.
 */
@Repository
@DeclareRoles({TRAX_READ})
public class TswTxPsiDataBean implements TswTxPsiData, Serializable {

    private static final long serialVersionUID = 1L;

    private static final String CLASSNAME = TswTxPsiDataBean.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final String findChoiceByQuery
            = "SELECT "
            + "new ca.bc.gov.educ.isd.traxadaptor.impl.TSWTxPSIImpl(i.tswTxPsiId.txId, i.tswTxPsiId.studNo, i.tswTxPsiId.psiCode, i.psiStatus) "
            + "FROM TswTxPsiEntity i "
            + "WHERE i.tswTxPsiId.studNo = ?1 ";

    private static final String findChoiceByQueryMutiple
            = "SELECT "
            + "new ca.bc.gov.educ.isd.traxadaptor.impl.TSWTxPSIImpl(i.tswTxPsiId.txId, i.tswTxPsiId.studNo, i.tswTxPsiId.psiCode, i.psiStatus) "
            + "FROM TswTxPsiEntity i "
            + "WHERE i.tswTxPsiId.txId = :txId "
            + "AND i.tswTxPsiId.studNo = :studNo "
            + "AND i.tswTxPsiId.psiCode = :psiCode";

    private static final String findChoiceByDateMutiple
            = "SELECT "
            + "new ca.bc.gov.educ.isd.traxadaptor.impl.TSWTxPSIImpl(i.tswTxPsiId.txId, i.tswTxPsiId.studNo, i.tswTxPsiId.psiCode, i.psiStatus) "
            + "FROM TswTxPsiEntity i "
            + "WHERE i.tswTxPsiId.txId = :txId "
            + "AND i.tswTxPsiId.studNo = :studNo "
            + "AND i.tswTxPsiId.psiCode = :psiCode "
            + "AND i.processDate >= :prcssDate";

    private final String PEN_FORMAT = "%-10s";

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<? extends TSWTxPSI> findTxPSIBy(String studNo) throws EISException {
        final String _m = "findTxPSIBy(String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The TX ID parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The TX ID parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        final List<TSWTxPSIImpl> tswTxPSI = null;

        LOG.exiting(CLASSNAME, _m);
        return tswTxPSI;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<? extends TSWTxPSI> findTxPSIBy(final String txId, final String studNo, final String psiCode) throws EISException {
        final String _m = "findTxPSIBy(String, String, String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (txId == null) {
                ex = ExceptionUtilities.setupRuntimeException("The txId parameter should not be null.");
            } else if (txId.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The txId parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        final List<TSWTxPSIImpl> tswTxPSI = null;

        LOG.exiting(CLASSNAME, _m);
        return tswTxPSI;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<? extends TSWTxPSI> findTxPSIBy(final String txId, final String studNo, final String psiCode, final Date processDate) throws EISException {
        final String _m = "findTxPSIBy(String, String, String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (txId == null) {
                ex = ExceptionUtilities.setupRuntimeException("The txId parameter should not be null.");
            } else if (txId.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The txId parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        List<TSWTxPSIImpl> tswTxPSI = null;

        if (tswTxPSI == null || tswTxPSI.isEmpty()) {
            String newPen = String.format(PEN_FORMAT, studNo);
            tswTxPSI = null;
        }
        LOG.log(Level.FINE, "Found {0} TWS TX PSI for [txId ={1}, studNo={2}, psiCode={3}, processDate={4}].",
                new Object[]{tswTxPSI.size(), txId, studNo, psiCode, processDate});

        LOG.exiting(CLASSNAME, _m);
        return tswTxPSI;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public void insertNewTxPSI(final String txId, final String studNo, final String psiCode, final Character status) throws EISException {
        final String _m = "insertNewTxPSI(String, String, String, Character)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (txId == null) {
                ex = ExceptionUtilities.setupRuntimeException("The txId parameter should not be null.");
            } else if (txId.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The txId parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        TswTxPsiEntity newTxPSI = new TswTxPsiEntity(txId, studNo, psiCode, status);

        LOG.log(Level.WARNING, "TX Object identified by student number = {0}, PSI code = {1}, Id  = {2} and Status = {3} was inserted.", new Object[]{studNo, psiCode, txId, status});

        LOG.exiting(CLASSNAME, _m);
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    // This is not working, the fix is in branch ISD-TRAXAdaptor-ejb/branches/ST-1235
    public Boolean updateTxPSIStatus(final String txId, final String studNo, final String psiCode, final Character status) throws EISException {
        final String _m = "updateTxPSIStatus(String, String, String, Character)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (txId == null) {
                ex = ExceptionUtilities.setupRuntimeException("The txId parameter should not be null.");
            } else if (txId.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The txId parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (status == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Status parameter should not be null.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        boolean response = false;

        final List<? extends TSWTxPSI> tswTxPSIList = findTxPSIBy(txId, studNo, psiCode);

        if (tswTxPSIList != null) {
            for (final TSWTxPSI item : tswTxPSIList) {
                if (item.getPsiStatus() != null
                        && item.getPsiStatus() != 'P') {
                    LOG.log(Level.FINE, "TX Object identified by student number = {0}, PSI code = {1},  and Id = {2} was found.", new Object[]{studNo, psiCode, txId});

                    item.setPsiStatus(status);
                    response = true;
                    LOG.log(Level.FINE, "The status of the object identified by Student Number = {0}, PSI code = {1},  and Id = {2} was updated to {3}.", new Object[]{studNo, psiCode, txId, status});
                }
            }
        } else {
            LOG.log(Level.WARNING, "TX Object identified by student number = {0}, PSI code = {1},  Id = {2} and status={3} was not found.", new Object[]{studNo, psiCode, txId, status});
        }

        LOG.exiting(CLASSNAME, _m);
        return response;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public void deleteTxPSI(final String txId, final String studNo, final String psiCode) throws EISException {
        final String _m = "deleteTxPSI(String, String, String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (txId == null) {
                ex = ExceptionUtilities.setupRuntimeException("The txId parameter should not be null.");
            } else if (txId.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The txId parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI code parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        TswTxPsiId tswTxPSIId = new TswTxPsiId(txId, studNo, psiCode);
        TswTxPsiEntity tswTxPSIToDelete = null;

        if (tswTxPSIToDelete
                != null) {
            LOG.log(Level.FINE, "TX Object identified by student number = {0}, PSI code = {1},  and Id = {2} was found.", new Object[]{studNo, psiCode, txId});
            LOG.log(Level.FINE, "The status of the object identified by Student Number = {0}, PSI code = {1},  and Id = {2} was deleted.", new Object[]{studNo, psiCode, txId});
        } else {
            LOG.log(Level.WARNING, "TX Object identified by student number = {0}, PSI code = {1},  and Id = {2} was not found.", new Object[]{studNo, psiCode, txId});
        }

        LOG.exiting(CLASSNAME, _m);
    }

}
