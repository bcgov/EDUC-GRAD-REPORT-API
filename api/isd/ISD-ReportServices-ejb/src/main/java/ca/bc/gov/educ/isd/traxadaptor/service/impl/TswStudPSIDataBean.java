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
 *  File:                TswStudPSIDataBean.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.traxadaptor.service.impl;

import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.trax.db.TSWStud;
import ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl.TswStudPSIEntity;
import ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl.TswStudPSIId;
import ca.bc.gov.educ.isd.traxadaptor.impl.TSWStudImpl;
import ca.bc.gov.educ.isd.traxadaptor.service.TswStudPSIData;
import ca.bc.gov.educ.isd.traxadaptor.utils.ExceptionUtilities;
import org.springframework.stereotype.Repository;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.eis.roles.Roles.FULFILLMENT_SERVICES_USER;
import static ca.bc.gov.educ.isd.eis.roles.Roles.TRAX_READ;

/**
 * This is an intermediate layer between the database entities and the unmanaged
 * data objects which are returned to the requesting services. It is a stateless
 * bean providing access to the container managed persistence. The EJB provides
 * methods to support the insertions and updates in PSI Choices table that exist
 * in ISD database.
 *
 * @author CGI Information Management Consultants Inc.
 */
@Repository
@DeclareRoles({TRAX_READ, FULFILLMENT_SERVICES_USER})
public class TswStudPSIDataBean implements TswStudPSIData, Serializable {

    // ------------------ CONSTANT(S) AND FINAL(S)
    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = TswStudPSIDataBean.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final String findStudByQuery
            = "SELECT "
            + "new " + TSWStudImpl.class.getCanonicalName() + "(i.tswStudPsiId.studNo, i.tswStudPsiId.psiCode, i.tswStudPsiId.psiYear, i.psiStatus) "
            + "FROM " + TswStudPSIEntity.class.getSimpleName() + " i "
            + "WHERE i.tswStudPsiId.studNo = ?1 ";

    private static final String findStudByQueryMutiple
            = "SELECT "
            + "new " + TSWStudImpl.class.getCanonicalName() + "(i.tswStudPsiId.studNo, i.tswStudPsiId.psiCode, i.tswStudPsiId.psiYear, i.psiStatus) "
            + "FROM " + TswStudPSIEntity.class.getSimpleName() + " i "
            + "WHERE i.tswStudPsiId.studNo = :studNo "
            + "AND i.tswStudPsiId.psiCode = :psiCode "
            + "AND i.tswStudPsiId.psiYear = :psiYear";

    // ------------------ CONSTRUCTOR(S)
    // ------------------ GETTER(S) AND SETTER(S)
    // ------------------ METHOD(S)
    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<? extends TSWStud> findChoiceBy(final String studNo) throws EISException {
        final String _m = "findChoiceBy(String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        List<TSWStudImpl> tswStud = null;

        LOG.exiting(CLASSNAME, _m);
        return tswStud;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<? extends TSWStud> findChoiceBy(final String studNo, final String psiCode, final String psiYear) throws EISException {
        final String _m = "findChoiceBy(String, String, String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (psiYear == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be null.");
            } else if (psiYear.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        List<TSWStudImpl> tswPsiChoices = null;

        LOG.exiting(CLASSNAME, _m);
        return tswPsiChoices;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public void insertNewChoice(final String studNo, final String psiCode, final String psiYear, final Character status) throws EISException {
        final String _m = "insertNewChoice(String, String, String, Character)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (psiYear == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be null.");
            } else if (psiYear.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        TswStudPSIEntity newTswStud = new TswStudPSIEntity(studNo, psiCode, psiYear, status);

        LOG.log(Level.WARNING, "Student PSI Object identified by student number = {0}, PSI code = {1}, Year = {2} and Status = {3} was inserted.", new Object[]{studNo, psiCode, psiYear, status});

        LOG.exiting(CLASSNAME, _m);
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public Boolean updateChoiceStatus(final String studNo, final String psiCode, final String psiYear, final Character status) throws EISException {
        final String _m = "updateChoiceStatus(String, String, String, Character)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (psiYear == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be null.");
            } else if (psiYear.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        boolean response = false;

        TswStudPSIId tswStudId = new TswStudPSIId(studNo, psiCode, psiYear);
        TswStudPSIEntity tswStudToUpdate = null;

        if (tswStudToUpdate != null) {
            LOG.log(Level.FINE, "Student PSI Object identified by student number = {0}, PSI code = {1},  and Year = {2} was found.", new Object[]{studNo, psiCode, psiYear});
            tswStudToUpdate.setPsiStatus(status);
            response = true;
            LOG.log(Level.FINE, "The status of the Student PSI object identified by Student Number = {0}, PSI code = {1},  and Year = {2} was updated to {3}.", new Object[]{studNo, psiCode, psiYear, status});
        } else {
            LOG.log(Level.WARNING, "Student PSI Object identified by student number = {0}, PSI code = {1},  and Year = {2} was not found.", new Object[]{studNo, psiCode, psiYear});
        }

        LOG.exiting(CLASSNAME, _m);
        return response;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public Boolean deleteChoice(final String studNo, final String psiCode, final String psiYear) throws EISException {
        final String _m = "deleteChoice(String, String, String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (studNo == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be null.");
            } else if (studNo.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The Student Number parameter should not be empty.");
            } else if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (psiYear == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be null.");
            } else if (psiYear.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI year parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        boolean response = false;

        TswStudPSIId tswStudId = new TswStudPSIId(studNo, psiCode, psiYear);
        TswStudPSIEntity tswStudToDelete = null;

        if (tswStudToDelete != null) {
            LOG.log(Level.FINE, "Student PSI Object identified by student number = {0}, PSI code = {1},  and Year = {2} was found.", new Object[]{studNo, psiCode, psiYear});
            response = true;
            LOG.log(Level.FINE, "The status of the Student PSI object identified by Student Number = {0}, PSI code = {1},  and Year = {2} was deleted.", new Object[]{studNo, psiCode, psiYear});
        } else {
            LOG.log(Level.WARNING, "Student PSI Object identified by student number = {0}, PSI code = {1},  and Year = {2} was not found.", new Object[]{studNo, psiCode, psiYear});
        }

        LOG.exiting(CLASSNAME, _m);
        return response;
    }
}
