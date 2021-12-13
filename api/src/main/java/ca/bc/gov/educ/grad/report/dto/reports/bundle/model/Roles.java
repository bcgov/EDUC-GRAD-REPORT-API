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
 *  File:                $Id:::                                                $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.bundle.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains centralized location for security roles. Note that role names may
 * NOT contain a period.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class Roles {

    public static final String USER_BCMP_SERVICE = "BCMPSERVICES_USER";
    public static final String USER_BCMP_BUNDLE = USER_BCMP_SERVICE + "_bundle";
    public static final String USER_BCMP_APPEND = USER_BCMP_SERVICE + "_append";
    public static final String USER_BCMP_ENUMERATE = USER_BCMP_SERVICE + "_enumerate";
    public static final String USER_BCMP_PAPERTYPE = USER_BCMP_SERVICE + "_papertype";
    public static final String USER_BCMP_TRANSCRIPT_ORDER_TYPE = USER_BCMP_SERVICE + "_transcript_order_type";
    public static final String USER_BCMP_CERTIFICATE_ORDER_TYPE = USER_BCMP_SERVICE + "_certificate_order_type";

    public static final String USER_BCMP_XPIF = USER_BCMP_SERVICE + "_xpif";

    /**
     * Returns a listing of all roles defined in the passed class.
     *
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static final List<String> getRoles()
            throws IllegalArgumentException, IllegalAccessException {
        final Field[] fields = Roles.class.getDeclaredFields();
        final List<String> roles = new ArrayList<>();
        for (final Field field : fields) {
            if (!field.getType().isArray()) {
                roles.add(field.get(field).toString());
            }
        }
        return roles;
    }
}
