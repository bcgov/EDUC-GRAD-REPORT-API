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
package ca.bc.gov.educ.isd.traxadaptor.service.impl;

import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.trax.db.PSIChoice;
import ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl.TswPSIChoiceEntity;
import ca.bc.gov.educ.isd.traxadaptor.impl.TSWChoiceImpl;
import ca.bc.gov.educ.isd.traxadaptor.service.TswPSIChoiceData;
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
 * methods to support the insertions and updates in PSI Choices table that exist
 * in TRAX database.
 *
 *
 * @author CGI Information Management Consultants Inc.
 */
@Repository
@DeclareRoles({TRAX_READ, FULFILLMENT_SERVICES_USER})
public class TswPSIChoiceDataBean implements TswPSIChoiceData, Serializable {

    // ------------------ CONSTANT(S) AND FINAL(S)
    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = TswPSIChoiceDataBean.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final String findChoiceByQuery
            = "SELECT "
            + "new " + TSWChoiceImpl.class.getCanonicalName() + "(i.tswChoiceId.studNo, i.tswChoiceId.psiCode, i.tswChoiceId.psiYear, i.status, i.psiName, i.transmissionMode) "
            + "FROM " + TswPSIChoiceEntity.class.getSimpleName() + " i "
            + "WHERE i.tswChoiceId.studNo = ?1 ";

    private static final String findChoiceByBetweenDate
            = "SELECT "
            + "new " + TSWChoiceImpl.class.getCanonicalName() + "(i.tswChoiceId.studNo, i.tswChoiceId.psiCode, i.tswChoiceId.psiYear, i.status, i.psiName, i.transmissionMode) "
            + "FROM " + TswPSIChoiceEntity.class.getSimpleName() + " i "
            + "WHERE i.updateDt between ?1 and ?2 ";

    private static final String findChoiceByQueryMutiple
            = "SELECT "
            + "new " + TSWChoiceImpl.class.getCanonicalName() + "(i.tswChoiceId.studNo, i.tswChoiceId.psiCode, i.tswChoiceId.psiYear, i.status, i.psiName, i.transmissionMode) "
            + "FROM " + TswPSIChoiceEntity.class.getSimpleName() + " i "
            + "WHERE i.tswChoiceId.studNo = :studNo "
            + "AND i.tswChoiceId.psiCode = :psiCode "
            + "AND i.tswChoiceId.psiYear = :psiYear";

    // ------------------ CONSTRUCTOR(S)
    // ------------------ GETTER(S) AND SETTER(S)
    // ------------------ METHOD(S)
    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<PSIChoice> findChoiceBy(final String studNo) throws EISException {
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

        List<PSIChoice> tswPsiChoices = null;

        LOG.exiting(CLASSNAME, _m);
        return tswPsiChoices;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<PSIChoice> findChoiceBy(final String studNo, final String psiCode, final String psiYear) throws EISException {
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

        List<PSIChoice> tswPsiChoices = null;

        LOG.exiting(CLASSNAME, _m);
        return tswPsiChoices;
    }

    @SuppressWarnings("unchecked")
    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<PSIChoice> findChoiceBy(final Date startDate, final Date endDate) throws EISException {
        final String _m = "findChoiceBy(Date, Date)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (startDate == null) {
                ex = ExceptionUtilities.setupRuntimeException("The start date should not be null.");
            } else if (endDate == null) {
                ex = ExceptionUtilities.setupRuntimeException("The end date should not be null.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        final List<PSIChoice> tswPsiChoices = null;

        LOG.exiting(CLASSNAME, _m);
        return tswPsiChoices;
    }
}
