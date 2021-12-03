/* *********************************************************************
 *  Copyright (c) 2015, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015)  $
 *  Revision Number:     $Rev:: 36                                             $
 *  Last Commit by:      $Author:: bbates                                     $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.model.common.support.impl;

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

    public static final String USER = "USER";

    public static final String PUBLIC_USER = "PUBLIC_USER";

    public static final String USER_SELF_REG_WORKFLOW = "USER_SELF_REG_WORKFLOW";

    public static final String ADMIN_TASK_PROCESS = "ADMIN_TASK_PROCESS";

    public static final String TRAX_READ = "TRAX_READ";

    public static final String FULFILLMENT_SERVICES_USER = "FULFILLMENT_SERVICES_USER";

    public static final String USER_ADMIN_SERVICE = "USER_ADMIN";
    public static final String USER_ADMIN_REMOVE = USER_ADMIN_SERVICE + "_remove";

    //TODO cp - Test Driver does not need its own roles, should use the role of the target function
    public static final String BCCAMPUS_TESTDRIVER = "BCCAMPUS_TESTDRIVER";
    public static final String BCCAMPUS_TESTDRIVER_READ = BCCAMPUS_TESTDRIVER + "_read";

    public static final String BCMAILPLUS_TESTDRIVER = "BCMAILPLUS_TESTDRIVER";
    public static final String BCMAILPLUS_TESTDRIVER_READ = BCMAILPLUS_TESTDRIVER + "_read";

    public static final String BCEID_TESTDRIVER = "BCEID_TESTDRIVER";
    public static final String BCEID_TESTDRIVER_READ = BCEID_TESTDRIVER + "_read";

    public static final String EVNT_PUBLISH_ENTITY_CHANGE = "EVENT_PUBLISH_ENTITY_CHANGE";

    public static final String SXR_SERVICE = "STUDENT_X_REF";
    public static final String SXR_SEARCH = SXR_SERVICE + "_search";
    public static final String SXR_READ = SXR_SERVICE + "_read";

    public static final String USER_PROFILE_SERVICE = "USER_PROFILE_SERVICE";
    public static final String USER_PROFILE_READ = USER_PROFILE_SERVICE + "_read";
    public static final String USER_PROFILE_SEARCH = USER_PROFILE_SERVICE + "_search";

    // FIXME: these are duplicated from ReportServices - TranscriptServices cannot see them there
    public static final String USER_REPORTS_SERVICE = "USER_REPORTS_SERVICE";
    public static final String USER_REPORTS_EXPORT = USER_REPORTS_SERVICE + "_export";
    public static final String USER_REPORTS_TRANSCRIPT = USER_REPORTS_SERVICE + "_transcript";
    public static final String USER_REPORTS_PEAR = USER_REPORTS_SERVICE + "_pear";
    public static final String USER_REPORTS_SCHOLARSHIPS = USER_REPORTS_SERVICE + "_scholarships";
    public static final String USER_REPORTS_PACKINGSLIP = USER_REPORTS_SERVICE + "_packingslip";
    public static final String USER_REPORTS_CERTIFICATES = USER_REPORTS_SERVICE + "_certificates";

    public static final String[] TRAX_ROLEGROUP = {TRAX_READ};

    /**
     * RoleGroup for Testing of Code Set Services.
     */
    public static final String[] TESTING_USER = {
        BCMAILPLUS_TESTDRIVER_READ
    };

    /**
     * Unauthorized user group
     */
    public static final String[] NEW_USER_GROUP = {
        USER
    };

    /**
     * Returns a listing of all roles defined in this class.
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
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        final Field field = Roles.class.getDeclaredField(roleGroupName);

        return (String[]) field.get(field);
    }
}
