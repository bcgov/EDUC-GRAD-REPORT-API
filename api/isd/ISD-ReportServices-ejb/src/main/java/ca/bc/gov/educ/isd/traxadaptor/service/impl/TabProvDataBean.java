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
import ca.bc.gov.educ.isd.eis.trax.db.TabProvince;
import ca.bc.gov.educ.isd.traxadaptor.service.TabProvData;
import ca.bc.gov.educ.isd.traxadaptor.utils.ExceptionUtilities;
import org.springframework.stereotype.Repository;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.eis.roles.Roles.FULFILLMENT_SERVICES_USER;
import static ca.bc.gov.educ.isd.eis.roles.Roles.TRAX_READ;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@Repository
@DeclareRoles({TRAX_READ, FULFILLMENT_SERVICES_USER})
public class TabProvDataBean implements TabProvData {

    // ------------------ CONSTANT(S) AND FINAL(S)
    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = TswTxPsiDataBean.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final String all
            = "SELECT new ca.bc.gov.educ.isd.traxadaptor.impl.TabProvinceImpl("
            + "t.tabProvEntityPK.provCode, "
            + "t.tabProvEntityPK.provName, "
            + "t.tabProvEntityPK.cntryCode) "
            + " FROM TabProvEntity t";

    private static final String search
            = "SELECT new ca.bc.gov.educ.isd.traxadaptor.impl.TabProvinceImpl("
            + "t.tabProvEntityPK.provCode, "
            + "t.tabProvEntityPK.provName, "
            + "t.tabProvEntityPK.cntryCode) "
            + " FROM TabProvEntity t "
            + " WHERE t.tabProvEntityPK.provCode = :provCode "
            + " AND t.tabProvEntityPK.provName = :provName "
            + " AND t.tabProvEntityPK.cntryCode = :cntryCode";

    private static final String findByProvinceCode
            = "SELECT new ca.bc.gov.educ.isd.traxadaptor.impl.TabProvinceImpl("
            + "t.tabProvEntityPK.provCode, "
            + "t.tabProvEntityPK.provName, "
            + "t.tabProvEntityPK.cntryCode) "
            + " FROM TabProvEntity t "
            + " WHERE t.tabProvEntityPK.provCode = :provCode";

    private static final String findByProvinceName
            = "SELECT new ca.bc.gov.educ.isd.traxadaptor.impl.TabProvinceImpl("
            + "t.tabProvEntityPK.provCode, "
            + "t.tabProvEntityPK.provName, "
            + "t.tabProvEntityPK.cntryCode) "
            + " FROM TabProvEntity t"
            + " WHERE t.tabProvEntityPK.provName = :provName";

    private static final String findCountryProvinces
            = "SELECT new ca.bc.gov.educ.isd.traxadaptor.impl.TabProvinceImpl("
            + "t.tabProvEntityPK.provCode, "
            + "t.tabProvEntityPK.provName, "
            + "t.tabProvEntityPK.cntryCode) "
            + "FROM TabProvEntity t "
            + "WHERE t.tabProvEntityPK.cntryCode = :cntryCode";

    // ------------------ CONSTRUCTOR(S)
    // ------------------ GETTER(S) AND SETTER(S)
    // ------------------ METHOD(S)
    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TabProvince> findProvinceByCode(String provCode) throws EISException {
        final String _m = "findProvinceByCode(String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (provCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("Province code must not be null.");
            } else if (provCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("Province code must not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        List<TabProvince> results = (List<TabProvince>) null;

        LOG.exiting(CLASSNAME, _m);
        return results;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TabProvince> findProvinceByName(String provName) throws EISException {
        final String _m = "findProvinceByCode(String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (provName == null) {
                ex = ExceptionUtilities.setupRuntimeException("Province name must not be null.");
            } else if (provName.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("Province name must not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        List<TabProvince> results = (List<TabProvince>) null;

        LOG.exiting(CLASSNAME, _m);
        return results;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TabProvince> findCountryProvinces(String cntryCode) throws EISException {
        final String _m = "findProvinceByCode(String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (cntryCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("Country code must not be null.");
            } else if (cntryCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("Country code must not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        List<TabProvince> results = (List<TabProvince>) null;

        LOG.exiting(CLASSNAME, _m);
        return results;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TabProvince> findAll() {
        final String _m = "findProvinceByCode(String)";
        LOG.entering(CLASSNAME, _m);

        List<TabProvince> results = (List<TabProvince>) null;

        LOG.exiting(CLASSNAME, _m);
        return results;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TabProvince> search(String provCode, String provName, String cntryCode) throws EISException {
        final String _m = "findProvinceByCode(String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (provCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("Province code must not be null.");
            } else if (provCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("Province code must not be empty.");
            } else if (provName == null) {
                ex = ExceptionUtilities.setupRuntimeException("Province name must not be null.");
            } else if (provName.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("Province name must not be empty.");
            } else if (cntryCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("Country code must not be null.");
            } else if (cntryCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("Country code must not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        List<TabProvince> results = (List<TabProvince>) null;

        LOG.exiting(CLASSNAME, _m);
        return results;
    }

}
