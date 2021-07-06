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
 *  File:                $Id:: AdminReportImpl.java 6655 2017-03-27 23:12:43Z #$
 *  Date of Last Commit: $Date:: 2017-03-27 16:12:43 -0700 (Mon, 27 Mar 2017)  $
 *  Revision Number:     $Rev:: 6655                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.jasper.impl;

import ca.bc.gov.educ.isd.reports.admin.AdminReport;
import ca.bc.gov.educ.isd.reports.admin.data.AdminReportDAO;

import java.util.Date;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.nullSafe;

/**
 * Superclass for all administrative reports. This uses a global "Admin" report
 * and a number of subreports.
 *
 * @author CGI Information Management Consultants Inc.
 * @param <DAO> Type of business report entity used to populate the report.
 */
public abstract class AdminReportImpl<DAO extends AdminReportDAO>
        extends ReportImpl
        implements AdminReport<DAO> {

    private static final long serialVersionUID = 5L;

    public static final String REPORT_NAME = "Admin";
    private static final String PARAMETER_SUBREPORT_NAME = "P_SUBREPORT_NAME";
    private static final String PARAMETER_DATE_BEGAN = "P_DATE_BEGAN";
    private static final String PARAMETER_DATE_ENDED = "P_DATE_ENDED";

    private Date dateBegan;
    private Date dateEnded;

    /**
     * Data to pass into the report after wrapping it in a JR collection.
     */
    private Object dataSource;

    /**
     * Overrides the name used by all subclasses. Instead of the subclassed
     * report name being used as the report name, it is used as the subreport
     * name. The report name is fixed as "Admin". This allows a common header
     * and footer to be used for all administrative reports. (The alternative is
     * to create numerous reports and have each report include the same header
     * and footer, which is a duplication of effort best avoided.)
     *
     * @param name Subreport name to run.
     */
    public AdminReportImpl(final String name) {
        super(REPORT_NAME);
        setParameter(PARAMETER_SUBREPORT_NAME, name);
    }

    /**
     * Changes the given object list to an internal representation and adds the
     * data source as a report parameter.
     *
     * @see
     * http://community.jaspersoft.com/wiki/why-first-record-missing-my-subreport
     *
     * @param o The data to switch from the external API to the internal report
     * API, a list of type RegistrationIssue.
     */
    @Override
    public void setDataSource(final Object o) {
        this.dataSource = o;
    }

    /**
     * Returns a list of DAO instances.
     *
     * @return A list, possibly empty, never null.
     */
    @Override
    public Object getDataSource() {
        return this.dataSource;
    }

    /**
     * Ensures that the admin filenames include the subreport name.
     *
     * @return "Admin_" + subreport name
     */
    @Override
    protected String getFilenameSuffix() {
        final Object o = getParameter(PARAMETER_SUBREPORT_NAME);
        final String subreportName = o == null ? "" : "_" + o.toString();

        return subreportName;
    }

    /**
     * Pass the start and finish dates into the report.
     */
    @Override
    protected void preprocessParameters() {
        setParameter(PARAMETER_DATE_BEGAN, getDateBegan());
        setParameter(PARAMETER_DATE_ENDED, getDateEnded());
    }

    /**
     * Returns the report starting date.
     *
     * @return A start date.
     */
    public Date getDateBegan() {
        return nullSafe(this.dateBegan);
    }

    /**
     * Changes the report starting date.
     *
     * @param dateBegan A start date.
     */
    @Override
    public void setDateBegan(final Date dateBegan) {
        assert dateBegan != null;
        this.dateBegan = new Date(dateBegan.getTime());
    }

    /**
     * Returns the report ending date.
     *
     * @return An end date.
     */
    public Date getDateEnded() {
        return nullSafe(this.dateEnded);
    }

    /**
     * Changes the report ending date.
     *
     * @param dateEnded An end date.
     */
    @Override
    public void setDateEnded(final Date dateEnded) {
        assert dateEnded != null;
        this.dateEnded = new Date(dateEnded.getTime());
    }
}
