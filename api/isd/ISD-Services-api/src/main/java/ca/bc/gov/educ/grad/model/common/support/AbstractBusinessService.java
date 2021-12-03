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
package ca.bc.gov.educ.grad.model.common.support;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public abstract class AbstractBusinessService {

    private static final String CLASSNAME = AbstractBusinessService.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    /**
     * Exception Message for failed input or pre-condition verification.
     *
     * This constant in consumed by automated unit test code.
     */
    public static final String MSG_VERIFICATION_FAILED_INPUT = "Input and pre-condition verification failed.";

    protected static Integer lookupEnvInteger(final String envPath, final String name, final Integer defaultValue) {
        final String _m = "lookupEnvIngeter(String, String)";
        LOG.exiting(CLASSNAME, _m, new Object[]{envPath, name, defaultValue});

        Integer retValue;

        try {
            final String strValue = lookupEnvParam(envPath, name, defaultValue.toString());
            retValue = Integer.parseInt(strValue);
            LOG.finest("Looked up and parsed JNDI Environment integer entry.");
        } catch (final NumberFormatException nfe) {
            retValue = defaultValue;
            LOG.log(Level.WARNING, "JNDI Environment integer entry was not an integer, using default.", nfe);
        }
        LOG.log(Level.FINER, "Using JNDI Environment integer entry {0}", retValue);

        LOG.exiting(CLASSNAME, _m, retValue);
        return retValue;
    }

    protected static String lookupEnvParam(final String envPath, final String name, final String defaultValue) {
        final String _m = "lookupEnvParam(String)";
        LOG.entering(CLASSNAME, _m, new Object[]{envPath, name, defaultValue});

        assert name != null : "Environment Parameter name was null.";
        assert !name.isEmpty() : "Environment Parameter name was empty";
        assert defaultValue != null : "Environment Parameter default value was null.";
        assert !defaultValue.isEmpty() : "Environment Parameter default value was empty";

        String retValue;

        try {
            InitialContext iniCtx = new InitialContext();
            Context envCtx = (Context) iniCtx.lookup(envPath);
            retValue = (String) envCtx.lookup(name);
            LOG.log(Level.CONFIG, "JNDI Environment Entry {0}={1}", new Object[]{name, retValue});

        } catch (final NamingException ne) {
            LOG.log(Level.WARNING, "Could not find JNDI entry for {0} using default value {1}", new Object[]{envPath, defaultValue});
            LOG.log(Level.WARNING, ne.getMessage());
            retValue = defaultValue;
        }
        LOG.exiting(CLASSNAME, _m, retValue);
        return retValue;
    }

}
