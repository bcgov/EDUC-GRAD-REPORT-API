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
 *  File:                $Id:: StudentProfileReportImpl.java 4388 2016-10-17 #$
 *  Date of Last Commit: $Date:: 2016-10-17 16:23:38 -0700 (Mon, 17 Oct 2016) $
 *  Revision Number:     $Rev:: 4388                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                   $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.admin.impl;

import ca.bc.gov.educ.isd.reports.admin.data.StudentProfile;
import ca.bc.gov.educ.isd.reports.jasper.impl.AdminReportImpl;

/**
 * Responsible for exporting StudentProfile reports.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class StudentProfileReportImpl extends AdminReportImpl<StudentProfile> {

    private static final long serialVersionUID = 10L;

    /**
     * Default (empty) constructor.
     */
    public StudentProfileReportImpl() {
        super("StudentProfileReport");
    }
}
