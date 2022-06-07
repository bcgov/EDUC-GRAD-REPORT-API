/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                StringUtils.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.common.support;

import static ca.bc.gov.educ.grad.report.model.common.support.VerifyUtils.nullSafe;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class StringUtils {

    public static String strip(String string, String toStrip) {
        final String response = nullSafe(string).trim().replaceAll(toStrip + "$|^" + toStrip, "");
        return response;
    }

    public static boolean StringUtilsIsNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    /**
     * @param builder
     * @param delimiter
     * @param info
     */
    public static void appendString(StringBuilder builder, String delimiter, String info) {

        if (StringUtilsIsNotBlank(info)) {
            builder.append(delimiter).append(info);
        }
    }

}
