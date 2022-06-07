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
 *  File:                PrintUtils.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.common.support;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Used to facilitate the print process.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class PrintUtils {

    public static String commaDelimited(final List<?> list) {
        final String response = list.toString();

        return response;
    }
    
    /**
     * Prints a map that contains an array of strings. 
     * 
     * 
     * @param map
     * @return 
     */
    public String commaDelimited(Map<String, String[]> map) {
        StringBuilder strBld = new StringBuilder();
        strBld.append("{");
        boolean first = true;

        if (map != null) {
            Set<String> keys = map.keySet();
            for (String key : keys) {
                if (!first) {
                    strBld.append(", ");
                }
                first = false;
                String[] arr = map.get(key);
                strBld.append(key).append("=").append(arr != null ? Arrays.toString(arr) : "EMPTY");
            }

        }
        strBld.append("}");
        return strBld.toString();
    }

    public static String commaDelimited(final Object... args) {
        final String response = Arrays.asList(args).toString();

        return response;
    }

}
