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
 *  File:                $Id:: AdminReport.java 4514 2016-10-20 18:38:12Z cpri#$
 *  Date of Last Commit: $Date:: 2016-10-20 11:38:12 -0700 (Thu, 20 Oct 2016)  $
 *  Revision Number:     $Rev:: 4514                                           $
 *  Last Commit by:      $Author:: cprince                                     $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.admin;

import ca.bc.gov.educ.isd.reports.Report;
import ca.bc.gov.educ.isd.reports.admin.data.AdminReportDAO;

import java.util.Date;

/**
 * General interface for all administrative reports.
 *
 * @author CGI Information Management Consultants Inc.
 * @param <T> Report data content container type.
 */
public interface AdminReport<T extends AdminReportDAO> extends Report {

    /**
     * Changes the report starting date.
     *
     * @param dateBegan A start date.
     */
    void setDateBegan(Date dateBegan);

    /**
     * Changes the report ending date.
     *
     * @param dateEnded An end date.
     */
    void setDateEnded(Date dateEnded);

    /**
     * Changes the data source for the report. Must be an object so that the
     * implementation of this interface can convert the data using the adapter
     * pattern.
     *
     * @param dataSource New data source to use.
     */
    void setDataSource(Object dataSource);
}
