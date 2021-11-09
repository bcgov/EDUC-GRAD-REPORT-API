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
 *  File:                ExceptionUtilities.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.adaptor.utils;

import ca.bc.gov.educ.isd.eis.EISException;

import java.util.logging.Logger;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ExceptionUtilities {

    // ------------------ CONSTANT(S) AND FINAL(S)
    private static final String CLASSNAME = ExceptionUtilities.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    public static final String LOG_FINE_VALIDATION_DONE = "The parameter validation has finished.";
    private static final String PARAMETER_VALIDATION_EXCEPTION = "Parameter validation failed.";

    /**
     * Wraps a RuntimeException in a EISException, and throws it. It is meant to
     * simplify the parameter validation process.
     *
     * @param ex runtime exception that is going to be wrapped by the
     * EISException.
     * @param methodName method's name that throws the exception.
     * @throws EISException acts as a wrapper for other exception.
     */
    public static void parameterValidationException(final RuntimeException ex, final String methodName) throws EISException {
        final String _m = "parameterValidationException(RuntimeException, String)";
        LOG.entering(CLASSNAME, _m);

        if (ex != null) {
            throw wrappingException(methodName, ex, PARAMETER_VALIDATION_EXCEPTION);
        }

        LOG.exiting(CLASSNAME, _m);
    }

    /**
     * Acts a EISException Factory wrapping other exception inside of it. The
     * main reason to use exception wrapping is to prevent the code further up
     * the call stack from having to know about every possible exception in the
     * system.
     *
     * @param methodName method that throws the exception
     * @param ex the exception thrown
     * @param msg the message that accompany the exception.
     * @return new wrapper exception object.
     */
    public static EISException wrappingException(final String methodName, final Exception ex, final String msg) {
        final String _m = "wrappingException(String, Exception, String)";
        LOG.entering(CLASSNAME, _m);

        final EISException eisEx;
        if (ex == null) {
            eisEx = new EISException(msg);
        } else {
            eisEx = new EISException(msg, ex);
        }

        LOG.throwing(CLASSNAME, methodName, eisEx);

        LOG.exiting(CLASSNAME, _m);
        return eisEx;
    }

    /**
     * Creates a Runtime Exception using the given message. Also, adds the LOG
     * the message.
     *
     * @param msg message that explains why the exception was thrown.
     * @return a new Runtime Exception setup with the message.
     */
    public static RuntimeException setupRuntimeException(final String msg) {
        final String _m = "setupRuntimeException(String)";
        LOG.entering(CLASSNAME, _m);

        LOG.warning(msg);

        LOG.exiting(CLASSNAME, _m);
        return new RuntimeException(msg);
    }

}
