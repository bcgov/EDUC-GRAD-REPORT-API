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
package ca.bc.gov.educ.isd.common.support.impl;

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
     * RoleGroup for Unauthorized test users.
     */
    public static final String NEW_USER_GROUP = "NEW_USER_GROUP";

    public static final String TRAX_ROLEGROUP = "TRAX_ROLEGROUP";

    /**
     * Returns a listing of all role group names defined in this class.
     *
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static final List<String> getRolegroupNames() throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = RoleGroupNames.class.getDeclaredFields();
        List<String> rolegroupNames = new ArrayList<>();
        for (Field field : fields) {
            if (!field.getType().isArray()) {
                rolegroupNames.add(field.get(field).toString());
            }
        }
        return rolegroupNames;
    }
}
