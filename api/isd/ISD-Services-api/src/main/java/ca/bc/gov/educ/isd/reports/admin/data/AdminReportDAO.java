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
 *  File:                $Id:: AdminReportDAO.java 6682 2017-03-29 22:06:2#$
 *  Date of Last Commit: $Date:: 2017-03-29 15:06:28 -0700 (Wed, 29 Mar 2017)  $
 *  Revision Number:     $Rev:: 6682                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.admin.data;

import java.io.Serializable;

/**
 * Superclass for all classes in this package. This is used by the generic admin
 * report to pass collections of subclasses into the appropriate subreport
 * without having to be coupled to specific subclasses.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface AdminReportDAO extends Serializable {
}
