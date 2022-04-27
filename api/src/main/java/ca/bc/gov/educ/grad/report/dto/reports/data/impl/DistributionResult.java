/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.data.impl;

import ca.bc.gov.educ.grad.report.dto.reports.data.BusinessEntity;

import java.util.List;

/**
 * Represents information that is placed on a certificate report.
 *
 * @author CGI Information Management Consultants Inc.
 */
//@XmlAccessorType(XmlAccessType.FIELD)
public final class DistributionResult extends BusinessEntity {

    private static final long serialVersionUID = 2L;

    /**
     * List of Students.
     */
    private List<Student> students;

    /**
     * School
     */
    private School school;

    /**
     * Default (empty) constructor.
     */
    public DistributionResult() {
    }

    /**
     * Sets the list of students
     *
     * @param students
     */
    public void setStudents(final List<Student> students) {
        if (students != null) {
            this.students = students;
        }
    }

    public void setSchool(final School school) {
        this.school = school;
    }

    /**
     * Provided so that subreports can also view all the fields (without having
     * to pass all the fields to each subreport individually via parameters).
     *
     * @return this certificate
     */
    public DistributionResult getDistribution() {
        return this;
    }

    /**
     * Returns an instance of the list of student that contains the information about student
     *
     * @return The List.
     */
    public List<Student> getStudents() {
        return this.students;
    }

    /**
     * Returns an instance of the school
     *
     * @return The School.
     */
    public School getSchool() {
        return school;
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<DistributionResult, Builder> {

        /**
         * Returns the builder used to construct outer class instances.
         *
         * @return this
         */
        @Override
        protected Builder thisBuilder() {
            return this;
        }

        /**
         * Returns an outer class instance without attributes initialized.
         *
         * @return New Certificate instance.
         */
        @Override
        protected DistributionResult createObject() {
            return new DistributionResult();
        }

        /**
         * Builds the Distribution.
         *
         * @param students
         * @return thisBuilder builder
         */
        public Builder withStudents(final List<Student> students) {
            getObject().setStudents(students);
            return thisBuilder();
        }

        /**
         * Builds the Distribution.
         *
         * @param school
         * @return thisBuilder builder
         */
        public Builder withSchool(final School school) {
            getObject().setSchool(school);
            return thisBuilder();
        }


    }
}
