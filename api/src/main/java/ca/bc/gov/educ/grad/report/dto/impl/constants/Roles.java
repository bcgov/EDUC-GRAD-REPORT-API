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
package ca.bc.gov.educ.grad.report.dto.impl.constants;

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

    public static final String STUDENT_ACHIEVEMENT_REPORT = "STUDENT_ACHIEVEMENT_REPORT";
    public static final String STUDENT_TRANSCRIPT_REPORT = "STUDENT_TRANSCRIPT_REPORT";
    public static final String STUDENT_CERTIFICATE_REPORT = "STUDENT_CERTIFICATE_REPORT";

    /**
     * Returns a listing of all roles defined in the passed class.
     *
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static final List<String> getRoles() throws IllegalArgumentException, IllegalAccessException {
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
