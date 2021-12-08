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
package ca.bc.gov.educ.grad.report.model.common.support;

import ca.bc.gov.educ.grad.report.model.common.BusinessService;
import ca.bc.gov.educ.grad.report.model.common.DomainEntity;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Used to validate method parameters.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class VerifyUtils {

    /**
     * Exception Message for failed input or pre-condition verification.
     *
     * This constant in consumed by automated unit test code.
     */
    public static final String MSG_VERIFICATION_FAILED_INPUT = "Input and pre-condition verification failed.";

    // TODO: Replace using \\d+?
    private final static String REG_ALL_DIGITS = "[0-9]+";

    public static RuntimeException verifyPEN(final String pen) {
        final RuntimeException ex;

        if (pen == null) {
            ex = new NullPointerException("PEN parameter may not be null.");
        } else if (!pen.matches(REG_ALL_DIGITS)) {
            //TODO check the data format and definition of a PEN and make sure that a PEN passing thorugh here is really a PEN.
            ex = new IllegalArgumentException("PEN parameter must be one of more digits.");
        } else {
            ex = null;
        }

        return ex;
    }

    /**
     * Utility to throw exception when parameters on pre-conditions are invalid.
     *
     * <b>Implementation Note:</b> This method does not contain constraint
     * checks.
     *
     * @param causeEx The causal exception, if this value is null, then a Domain
     * service exception is not thrown.
     * @param logger Logger of the caller.
     * @param className Class Name of the caller (used in conjunction with the
     * Logger)
     * @param methodName Calling Method Name (used in conjunction with the
     * Logger)
     * @throws DomainServiceException
     */
    public static void throwInvalidPreconditions(
            final Throwable causeEx,
            final Logger logger,
            final String className,
            final String methodName) throws DomainServiceException {
        if (causeEx != null) {
            logger.log(Level.WARNING, "Input and pre-condition verification failed for {0}: {1}", new Object[]{className, methodName});
            final DomainServiceException des = new DomainServiceException(null, AbstractBusinessService.MSG_VERIFICATION_FAILED_INPUT, causeEx);
            logger.throwing(className, methodName, des);
            throw des;
        }
    }

    /**
     * Utility to throw exception when parameters on pre-conditions are invalid.
     *
     * <b>Implementation Note:</b> This method does not contain constraint
     * checks.
     *
     * @param causeEx The causal exception, if this value is null, then a Domain
     * service exception is not thrown.
     * @param logger Logger of the caller.
     * @param className Class Name of the caller (used in conjunction with the
     * Logger)
     * @param methodName Calling Method Name (used in conjunction with the
     * Logger)
     * @param entity the value of entity
     * @throws DomainServiceException
     */
    public static void throwInvalidPreconditions(
            final Throwable causeEx,
            final Logger logger,
            final String className,
            final String methodName,
            final DomainEntity entity) throws DomainServiceException {
        if (causeEx != null) {
            logger.log(Level.WARNING, "Input and pre-condition verification failed for {0}: {1}", new Object[]{className, methodName});
            logger.log(Level.WARNING, "Caused by: {0}", causeEx.getMessage());

            final DomainServiceException des = new DomainServiceException(entity, MSG_VERIFICATION_FAILED_INPUT, causeEx);
            logger.throwing(className, methodName, des);

            throw des;
        }
    }

    /**
     * Returns true if the given String is neither null nor empty.
     *
     * @param paramName The string to check for emptiness.
     * @return true The string has content, but could be filled with spaces.
     * @deprecated Use one of the other verifyNotNullOrEmpty methods that has
     * extensive logging and exceptions.
     */
    public static boolean verifyNotNullOrEmpty(final String paramName) {
        return (paramName != null && !paramName.isEmpty());
    }

    public static boolean verifyNotNullOrEmpty(final List paramName) {
        return (paramName != null && !paramName.isEmpty());
    }

    /**
     * Checks if a parameter is null or "empty", log the result and prepare an
     * exception. Also checks that the domain entity has a entity id. (not empty
     * and 30 chars or less)
     *
     * @param logger the value of logger
     * @param classname the value of classname
     * @param methodName the value of methodName
     * @param paramName
     * @param paramValue
     * @param ex
     * @return
     */
    @SuppressWarnings({"AssignmentToMethodParameter", "ThrowableInstanceNotThrown", "ThrowableInstanceNeverThrown"})
    public static RuntimeException verifyNotNullOrEmpty(
            final Logger logger,
            final String classname,
            final String methodName,
            final String paramName,
            final DomainEntity paramValue,
            RuntimeException ex) {
        if (ex == null) {
            if (paramValue == null) {
                final String msg = paramName + " parameter may not be null.";
                logger.finest(msg);
                ex = new NullPointerException(msg);
            } else if (paramValue.getEntityId() == null) {
                final String msg = paramName + " parameter may not have a null Entity Id.";
                logger.finest(msg);
                ex = new IllegalArgumentException(msg);
            } else if (paramValue.getEntityId().isEmpty()) {
                final String msg = paramName + " parameter may not have an empty Entity Id.";
                logger.finest(msg);
                ex = new IllegalArgumentException(msg);
            } else if (paramValue.getEntityId().length() > 30) {
                final String msg = MessageFormat.format("{0} parameter has an entity id that is too long, may only have 30 characters but was {1}.", paramName, paramValue.getEntityId().length());
                logger.finest(msg);
                ex = new IllegalArgumentException(msg);
            }
        }
        return ex;
    }

    @SuppressWarnings({"AssignmentToMethodParameter", "ThrowableInstanceNotThrown", "ThrowableInstanceNeverThrown"})
    public static RuntimeException verifyNotNullOrEmpty(
            final Logger logger,
            final String classname,
            final String methodName,
            final String paramName,
            final BusinessService paramValue,
            RuntimeException ex) {
        if (ex == null) {
            if (paramValue == null) {
                final String msg = paramName + " parameter may not be null.";
                logger.finest(msg);
                ex = new NullPointerException(msg);

            }
        }
        return ex;
    }

    /**
     * Checks if a java.util.Collection parameter is not an empty collection.
     *
     * Note does not check parameters for null, due to the context in which the
     * Verify utils are being used.
     *
     * @param logger
     * @param classname
     * @param methodName
     * @param paramName
     * @param paramValue
     * @param ex
     * @return
     */
    @SuppressWarnings({"AssignmentToMethodParameter", "ThrowableInstanceNotThrown", "ThrowableInstanceNeverThrown"})
    public static RuntimeException verifyNotEmpty(
            final Logger logger,
            final String classname,
            final String methodName,
            final String paramName,
            final Collection paramValue,
            RuntimeException ex) {
        if (ex == null) {
            if (paramValue.isEmpty()) {
                final String msg = paramName + " parameter may not be an empty collection.";
                logger.finest(msg);
                ex = new NullPointerException(msg);

            }
        }
        return ex;
    }

    /**
     * Checks if a java.util.Collection parameter is not an empty collection.
     *
     * Note does not check parameters for null, due to the context in which the
     * Verify utils are being used.
     *
     * @param logger
     * @param classname
     * @param methodName
     * @param paramName
     * @param paramValue
     * @param ex
     * @return
     */
    @SuppressWarnings({"AssignmentToMethodParameter", "ThrowableInstanceNotThrown", "ThrowableInstanceNeverThrown"})
    public static RuntimeException verifyNotEmpty(
            final Logger logger,
            final String classname,
            final String methodName,
            final String paramName,
            final Map paramValue,
            RuntimeException ex) {
        if (ex == null) {
            if (paramValue.isEmpty()) {
                final String msg = paramName + " parameter may not be an empty collection.";
                logger.finest(msg);
                ex = new NullPointerException(msg);

            }
        }
        return ex;
    }

    @SuppressWarnings({"AssignmentToMethodParameter", "ThrowableInstanceNotThrown", "ThrowableInstanceNeverThrown"})
    public static RuntimeException verifyNotNull(
            final Logger logger,
            final String classname,
            final String methodName,
            final String paramName,
            final Object paramValue,
            RuntimeException ex) {
        if (ex == null) {
            if (paramValue == null) {
                final String msg = paramName + " parameter may not be null.";
                logger.finest(msg);
                ex = new NullPointerException(msg);
            }
        }
        return ex;
    }

    @SuppressWarnings({"AssignmentToMethodParameter", "ThrowableInstanceNotThrown", "ThrowableInstanceNeverThrown"})
    public static RuntimeException verifyGreaterThanZero(
            final Logger logger,
            final String classname,
            final String methodName,
            final String paramName,
            final Long paramValue,
            RuntimeException ex) {
        if (ex == null) {
            if (paramValue <= 0L) {
                final String msg = paramName + " parameter may not be less than or equal to zero.";
                logger.finest(msg);
                ex = new IllegalArgumentException(msg);
            }
        }
        return ex;
    }

    @SuppressWarnings({"AssignmentToMethodParameter", "ThrowableInstanceNotThrown", "ThrowableInstanceNeverThrown"})
    public static RuntimeException verifyGreaterThanEqualZero(
            final Logger logger,
            final String classname,
            final String methodName,
            final String paramName,
            final BigDecimal paramValue,
            RuntimeException ex) {
        if (ex == null) {
            if (paramValue == null) {
                final String msg = paramName + " parameter may not be null.";
                logger.finest(msg);
                ex = new IllegalArgumentException(msg);
            } else if (paramValue.doubleValue() < 0d) {
                final String msg = paramName + " parameter may not be less than to zero.";
                logger.finest(msg);
                ex = new IllegalArgumentException(msg);
            }
        }
        return ex;
    }

    @SuppressWarnings({"AssignmentToMethodParameter", "ThrowableInstanceNotThrown", "ThrowableInstanceNeverThrown"})
    public static RuntimeException verifyStateNotNull(
            final Logger logger,
            final String classname,
            final String methodName,
            final String stateVariableName,
            final Object stateVariableValue,
            RuntimeException ex) {
        if (ex == null) {
            if (stateVariableValue == null) {
                final String msg = "Pre-condition failed " + stateVariableName + ", state variable may not be null.";
                logger.finest(msg);
                ex = new IllegalStateException(msg);
            }
        }
        return ex;
    }

    @SuppressWarnings({"AssignmentToMethodParameter", "ThrowableInstanceNotThrown", "ThrowableInstanceNeverThrown"})
    public static RuntimeException verifyNotNullOrEmpty(
            final Logger logger,
            final String classname,
            final String methodName,
            final String paramName,
            final String paramValue,
            RuntimeException ex) {
        if (ex == null) {
            if (paramValue == null) {
                final String msg = paramName + " parameter may not be null.";
                logger.finest(msg);
                ex = new NullPointerException(msg);
            } else if (paramValue.isEmpty()) {
                final String msg = paramName + " parameter may not be empty.";
                logger.finest(msg);
                ex = new IllegalArgumentException(msg);
            }
        }
        return ex;
    }

    @SuppressWarnings({"AssignmentToMethodParameter", "ThrowableInstanceNotThrown", "ThrowableInstanceNeverThrown"})
    public static RuntimeException verifyNotNullOrEmpty(
            final Logger logger,
            final String classname,
            final String methodName,
            final String paramName,
            final Map paramValue,
            RuntimeException ex) {
        if (ex == null) {
            if (paramValue == null) {
                final String msg = paramName + " parameter may not be null.";
                logger.finest(msg);
                ex = new NullPointerException(msg);
            } else if (paramValue.isEmpty()) {
                final String msg = paramName + " parameter may not be empty.";
                logger.finest(msg);
                ex = new IllegalArgumentException(msg);
            }
        }
        return ex;
    }

    @SuppressWarnings({"AssignmentToMethodParameter", "ThrowableInstanceNotThrown", "ThrowableInstanceNeverThrown"})
    public static RuntimeException verifyNotNullOrEmpty(
            final Logger logger,
            final String classname,
            final String methodName,
            final String paramName,
            final Byte paramValue[],
            RuntimeException ex) {
        if (ex == null) {
            if (paramValue == null) {
                final String msg = paramName + " parameter may not be null.";
                logger.finest(msg);
                ex = new NullPointerException(msg);
            } else if (paramValue.length == 0) {
                final String msg = paramName + " parameter may not be empty.";
                logger.finest(msg);
                ex = new IllegalArgumentException(msg);
            }
        }
        return ex;
    }

    /**
     * Returns the current date if the given date is null.
     *
     * @param date The date to compare against null.
     * @return A valid date instance, never null.
     */
    public static Date nullSafe(final Date date) {
        return date == null ? new Date() : date;
    }

    public static java.sql.Date nullSafe(final java.sql.Date date) {
        java.sql.Date returnDate = new java.sql.Date(System.currentTimeMillis());
        if (date != null) {
            returnDate = date;
        }
        return returnDate;
    }

    /**
     * Returns an empty string if the given character sequence is null.
     *
     * @param cs The character sequence to compare against null.
     * @return A non-null string.
     */
    public static String nullSafe(final CharSequence cs) {
        return cs == null ? "" : cs.toString();
    }

    /**
     * Returns an empty string if the given character sequence is null.
     *
     * @param s The character sequence to compare against null.
     * @return A non-null string.
     */
    public static String nullSafe(final String s) {
        return s == null ? "" : s;
    }

    /**
     * Returns an empty string if the given String is null, otherwise the
     * trimmed version of s.
     *
     * @param s The String to compare against null.
     * @return A non-null string, possibly empty.
     */
    public static String trimSafe(final String s) {
        return nullSafe(s).trim();
    }

    /**
     * Returns an empty string if the given StringBuilder is null.
     *
     * @param sb The StringBuilder to compare against null.
     * @return A non-null string, possibly empty.
     */
    public static String nullSafe(final StringBuilder sb) {
        return sb == null ? "" : sb.toString();
    }

    /**
     * Returns an empty string if the given Character is null.
     *
     * @param c The Character to compare against null.
     * @return A non-null string, possibly empty.
     */
    public static String trimSafe(final Character c) {
        return trimSafe(nullSafe(c).toString());
    }

    /**
     * Returns an empty string if the given char is null.
     *
     * @param c The char to compare against null.
     * @return A non-null string, possibly empty.
     */
    public static String trimSafe(final char c) {
        return nullSafe(c).toString();
    }

    /**
     * Returns a space if the given character is empty.
     *
     * @param c The Character to compare against null.
     * @return A space character, otherwise the given character.
     */
    public static Character nullSafe(final Character c) {
        return c == null ? ' ' : c;
    }

    /**
     * Ensures the list is never null.
     *
     * @param list The list to check against null.
     * @return A new ArrayList iff the given list is null, otherwise the given
     * list.
     */
    public static List<?> nullSafe(final List<?> list) {
        return list == null ? new ArrayList<>() : list;
    }

    /**
     * Used to ensure null safe values are returned from accessors.
     *
     * @param d The double to check for null values.
     * @return d or 0, never null.
     */
    public static Double nullSafe(final Double d) {
        return d == null ? 0 : d;
    }

    /**
     * Used to ensure null safe values are returned from accessors.
     *
     * @param l The long to check against null.
     * @return l or 0, never null.
     */
    public static Long nullSafe(final Long l) {
        return l == null ? 0L : l;
    }

    /**
     * Used to ensure null safe values are returned from accessors.
     *
     * @param i The integer to check for null values.
     * @return i or 0, never null.
     */
    public static Integer nullSafe(final Integer i) {
        return i == null ? 0 : i;
    }

    /**
     * Used to ensure null safe values are returned from accessors.
     *
     * @param bd The number to check for null values.
     * @return bd or 0, never null.
     */
    public static BigDecimal nullSafe(final BigDecimal bd) {
        return bd == null ? new BigDecimal(0) : bd;
    }

    /**
     * Used to ensure null safe values are returned from accessors.
     *
     * @param b The boolean to check for null values.
     * @return false iff b == null, otherwise b.
     */
    public static Boolean nullSafe(final Boolean b) {
        return b == null ? Boolean.FALSE : b;
    }

    /**
     * Returns the given enum as a string, provided the enumerated type is not
     * null.
     *
     * @param e The enumerated type to convert to a string.
     * @return e.toString() or the empty string when e is null.
     */
    public static String nullSafe(final Enum e) {
        return e == null ? "" : e.toString();
    }

    /**
     * This will return true iff the given Character is 'Y' or 'y'.
     *
     * @param c The character to convert to a Boolean value.
     * @return true c equals Y, ignoring case.
     */
    public static Boolean asBoolean(final Character c) {
        return c == null ? false : (c.equals('Y') || c.equals('y'));
    }
}
