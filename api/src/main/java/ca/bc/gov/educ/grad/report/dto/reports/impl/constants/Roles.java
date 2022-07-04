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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.impl.constants;

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

    public static final String USER_REPORTS_SERVICE = "USER_REPORTS_SERVICE";
    public static final String USER_REPORTS_EXPORT = USER_REPORTS_SERVICE + "_export";
    public static final String USER_REPORTS_TRANSCRIPT = USER_REPORTS_SERVICE + "_transcript";
    public static final String USER_REPORTS_PEAR = USER_REPORTS_SERVICE + "_pear";
    public static final String USER_REPORTS_SCHOLARSHIPS = USER_REPORTS_SERVICE + "_scholarships";
    public static final String USER_REPORTS_PACKINGSLIP = USER_REPORTS_SERVICE + "_packingslip";
    public static final String USER_REPORTS_CERTIFICATES = USER_REPORTS_SERVICE + "_certificates";
    public static final String USER_REPORTS_SCHOOL_GRADUATION = USER_REPORTS_SERVICE + "_schoolgraduation";
    public static final String USER_REPORTS_NON_GRAD = USER_REPORTS_SERVICE + "_nongrad";

    public static final String ADMIN_REPORTS_SERVICE = "ADMIN_REPORTS_SERVICE";
    public static final String ADMIN_REPORTS_EXPORT = ADMIN_REPORTS_SERVICE + "_export";
    public static final String ADMIN_REPORTS_CREATE_DATA = ADMIN_REPORTS_SERVICE + "_create_data";
    public static final String ADMIN_REPORTS_CREATE_REPORT = ADMIN_REPORTS_SERVICE + "_create_report";

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
        final List<String> roles = new ArrayList<>(fields.length);

        for (final Field field : fields) {
            if (!field.getType().isArray()) {
                roles.add(field.get(field).toString());
            }
        }

        return roles;
    }
}
