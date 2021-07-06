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
 *  File:                $Id:: StudentReportImpl.java 11041 2018-07-31 19:18:4#$
 *  Date of Last Commit: $Date:: 2018-07-31 12:18:43 -0700 (Tue, 31 Jul 2018)  $
 *  Revision Number:     $Rev:: 11041                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.impl;

import ca.bc.gov.educ.isd.reports.StudentReport;
import ca.bc.gov.educ.isd.reports.data.adapter.BusinessEntityAdapter;
import ca.bc.gov.educ.isd.reports.data.impl.DistrictOrganisation;
import ca.bc.gov.educ.isd.reports.data.impl.School;
import ca.bc.gov.educ.isd.reports.data.impl.Student;
import ca.bc.gov.educ.isd.reports.jasper.impl.ReportImpl;

import java.util.Date;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.reports.data.impl.DistrictOrganisation.LOGO_CODE_BC;

/**
 * Superclass for all examination, transcript, and scholarship reports.
 *
 * @author CGI Information Management Consultants Inc.
 */
public abstract class StudentReportImpl extends ReportImpl implements StudentReport {

    private static final long serialVersionUID = 4L;
    private static final String CLASSNAME = StudentReportImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    public static final String P_REPORT_TYPE = "P_REPORT_TYPE";
    public static final String P_REPORT_DATE = "P_REPORT_DATE";

    /**
     * Container for all the information needed by the various student reports,
     * including transcript of grades.
     */
    private Student student;

    /**
     * Used by subclasses (e.g., certificate report) to provide a different
     * filename for the report template.
     *
     * @param name Report template to use.
     */
    protected StudentReportImpl(final String name) {
        super(name);
    }

    /**
     * Returns the main object used to fill the report. All the reports use a
     * single student instance populated with necessary data to fulfill the
     * report's data requirements.
     *
     * @return this.student
     */
    @Override
    public Object getDataSource() {
        return this.student;
    }

    /**
     * Sets information to use for filling various reports.
     *
     * @param student Student information (e.g., address, name, pen).
     */
    @Override
    public void setStudent(final ca.bc.gov.educ.isd.student.Student student) {
        this.student = BusinessEntityAdapter.adapt(student);
    }

    /**
     * Returns the student that contains information used to fill the report.
     *
     * @return A non-null Student instance passed into the report.
     */
    protected Student getStudent() {
        return this.student;
    }

    /**
     * Sets the school associated with the student. Uses a default logo code of
     * BC.
     *
     * @param school School information to use for filling the report.
     */
    @Override
    public void setSchool(final ca.bc.gov.educ.isd.school.School school) {
        setSchool(school, LOGO_CODE_BC);
    }

    /**
     * Sets the school associated with the student.
     *
     * @param school School information to use for filling the report.
     */
    @Override
    public void setSchool(
            final ca.bc.gov.educ.isd.school.School school,
            final String logoCode) {
        ensureValidStudent("setSchool");

        final School s = BusinessEntityAdapter.adapt(school, logoCode);
        getStudent().setSchool(s);
    }

    /**
     * Sets the date the report was issued or last updated. This sets the
     * P_REPORT_DATE parameter for the report. The report issue date (i.e., the
     * report date) doesn't have a clear static value in the data model, so it
     * has been abstracted as a report parameter.
     *
     * @param date The report issue or last updated date.
     */
    @Override
    public void setReportDate(final Date date) {
        setParameter(P_REPORT_DATE, date);
    }

    /**
     * Returns the school instance.
     *
     * @return The school that records the student's transcripts.
     */
    private School getSchool() {
        return getStudent().getSchool();
    }

    /**
     * Returns the school's district organisation.
     *
     * @return The district organisation of the school that records the
     * student's transcripts.
     */
    protected DistrictOrganisation getDistrictOrganisation() {
        return getSchool().getDistrictOrganisation();
    }

    /**
     * Returns the logo code associated with the student's school's district
     * organisation.
     *
     * @return "BC" or "YU"
     */
    protected String getLogoCode() {
        return getDistrictOrganisation().getLogoCode();
    }

    /**
     * Validates given object instances prior to calling dependent methods. This
     * will raise an assertion error if the object is null. Otherwise, this
     * method returns without affecting state.
     *
     * @param o The object to check for null.
     * @param invoke Method name that was invoked and possibly raised an error.
     * @param method Method name that cannot be called prior to calling
     * setStudent.
     */
    protected final void validate(final Object o, final String invoke, final String method) {
        assert o != null : "Call " + invoke + " before calling " + method;
    }

    /**
     * Helper method.
     *
     *
     * @param method The name of the method that cannot be called prior to
     * calling setStudent.
     */
    protected final void ensureValidStudent(final String method) {
        validate(getStudent(), "setStudent", method);
    }

    /**
     * Returns a description of this report (name, media type, etc.).
     *
     * @return The report description.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(64);
        sb.append("Name: <");
        sb.append(getName());
        sb.append(">; format: <");
        sb.append(getFormat());
        sb.append(">");

        return sb.toString();
    }
}
