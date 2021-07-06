/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.impl;

import java.lang.reflect.Field;

import static ca.bc.gov.educ.isd.common.support.impl.Roles.EVNT_PUBLISH_ENTITY_CHANGE;
import static ca.bc.gov.educ.isd.reports.impl.constants.Roles.*;

/**
 * Contains centralized location for role groups.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class RoleGroups {

    /**
     * RoleGroup for Users of Report Services.
     */
    public final static String[] REPORTSERVICES_USER = {
        EVNT_PUBLISH_ENTITY_CHANGE,
        USER_REPORTS_EXPORT,
        USER_REPORTS_TRANSCRIPT,
        USER_REPORTS_PEAR,
        USER_REPORTS_SCHOLARSHIPS,
        USER_REPORTS_PACKINGSLIP,
        USER_REPORTS_CERTIFICATES,};

    /**
     * RoleGroup for Administrators of Report Services.
     */
    public final static String[] REPORTSERVICES_ADMIN = {
        EVNT_PUBLISH_ENTITY_CHANGE,
        ADMIN_REPORTS_EXPORT,
        ADMIN_REPORTS_CREATE_DATA,
        ADMIN_REPORTS_CREATE_REPORT,};

    /**
     * Returns a listing of the roles belonging to the given RoleGroup.
     *
     * @param roleGroupName name of the role group of interest
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws java.lang.NoSuchFieldException
     */
    public static final String[] getRolegroupRoles(final String roleGroupName)
            throws IllegalAccessException, NoSuchFieldException {
        final Field field = RoleGroups.class.getDeclaredField(roleGroupName);

        return (String[]) field.get(field);
    }
}
