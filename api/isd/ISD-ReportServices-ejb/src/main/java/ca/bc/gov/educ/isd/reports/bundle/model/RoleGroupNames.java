/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *
 *  Revision Control Information
 *  File:                RoleGroupNames.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.bundle.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the names of the RoleGroup arrays in class Roles. These should be
 * exactly the same String as the array name (used for reflection lookup of the
 * array name).
 *
 * @author CGI Information Management Consultants Inc.
 */
public class RoleGroupNames {

    /**
     * RoleGroup for User of Report Services.
     */
    public static final String BCMPSERVICES_USER = "BCMPSERVICES_USER";

    /**
     * Returns a listing of all role group names defined in this class.
     *
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static final List<String> getRolegroupNames()
            throws IllegalArgumentException, IllegalAccessException {
        final Field[] fields = RoleGroupNames.class.getDeclaredFields();
        final List<String> rolegroupNames = new ArrayList<>();
        for (final Field field : fields) {
            if (!field.getType().isArray()) {
                rolegroupNames.add(field.get(field).toString());
            }
        }

        return rolegroupNames;
    }
}
