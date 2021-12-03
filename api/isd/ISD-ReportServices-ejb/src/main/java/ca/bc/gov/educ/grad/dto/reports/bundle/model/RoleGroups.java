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
package ca.bc.gov.educ.grad.dto.reports.bundle.model;

import java.lang.reflect.Field;

import static ca.bc.gov.educ.grad.dto.reports.bundle.model.Roles.*;
import static ca.bc.gov.educ.grad.dto.reports.impl.constants.Roles.*;
import static ca.bc.gov.educ.grad.model.common.support.impl.Roles.EVNT_PUBLISH_ENTITY_CHANGE;
import static ca.bc.gov.educ.grad.model.common.support.impl.Roles.USER_REPORTS_SERVICE;

/**
 * Contains centralized location for role groups.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class RoleGroups {

    /**
     * RoleGroup for Users of Report Services.
     */
    public static final String[] BCMPSERVICES_USER = {
        EVNT_PUBLISH_ENTITY_CHANGE,
        USER_BCMP_SERVICE,
        USER_BCMP_BUNDLE,
        USER_BCMP_APPEND,
        USER_BCMP_ENUMERATE,
        USER_BCMP_TRANSCRIPT_ORDER_TYPE,
        USER_BCMP_CERTIFICATE_ORDER_TYPE,
        USER_BCMP_XPIF,
        USER_REPORTS_SERVICE,
        USER_REPORTS_EXPORT,
        USER_REPORTS_TRANSCRIPT,
        USER_REPORTS_PACKINGSLIP,
        USER_REPORTS_CERTIFICATES};

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
            throws IllegalArgumentException, IllegalAccessException,
            NoSuchFieldException {
        final Field field = RoleGroups.class.getDeclaredField(roleGroupName);

        return (String[]) field.get(field);
    }
}
