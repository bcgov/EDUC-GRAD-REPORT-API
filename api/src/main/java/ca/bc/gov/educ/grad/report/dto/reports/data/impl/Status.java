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
 *  File:                $Id:: Status.java 5864 2017-01-18 21:34:24Z matalbot  $
 *  Date of Last Commit: $Date:: 2017-01-18 13:34:24 -0800 (Wed, 18 Jan 2017)  $
 *  Revision Number:     $Rev:: 5864                                           $
 *  Last Commit by:      $Author:: matalbot                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.data.impl;

import ca.bc.gov.educ.grad.report.dto.reports.data.BusinessEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Responsible for answering questions about a Student, including:
 * <ul>
 * <li>graduation message</li>
 * <li>is a former student</li>
 * <li>can print a certificate</li>
 * <li>can print a transcript</li>
 * <li>reasons a student has not yet graduated</li>
 * <li>has an examination in session</li>
 * </ul>
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Status extends BusinessEntity {

    private static final long serialVersionUID = -7199844704391696583L;

    /**
     * A description explaining why the student has or has not yet graduated.
     */
    private String graduationMessage;

    /**
     * List of reasons why the Student has not yet graduated.
     */
    @XmlElement(name = "incompletionReason")
    private List<IncompletionReason> incompletionReasons;

    /**
     * Default (empty) constructor.
     */
    public Status() {
    }

    /**
     * Provides a description explaining why the student has or has not yet
     * graduated.
     *
     * @return A non-null String, possibly empty.
     */
    public String getGraduationMessage() {
        return nullSafe(this.graduationMessage);
    }

    /**
     * Answers whether the student graduated. If the list of incompletion
     * reasons is empty, this will return true.
     *
     * @return true The student graduated.
     */
    public boolean getGraduated() {
        return getIncompletionReasons().isEmpty();
    }

    /**
     * Returns a list of transcript marks (courses and grades) used to populate
     * the Transcript of Grades report.
     *
     * Lazily initialized.
     *
     * @return A list of courses and corresponding grades that the student
     * achieved, never null (possibly empty).
     */
    public List<IncompletionReason> getIncompletionReasons() {
        if (this.incompletionReasons == null) {
            this.incompletionReasons = createEmptyList();
        }

        return this.incompletionReasons;
    }

    /**
     * Adds the given incompletion reason to the student's list of reasons for
     * not graduating.
     *
     * @param ir The incompletion reason (code and description) to add to the
     * list.
     */
    public void addIncompletionReason(final IncompletionReason ir) {
        getIncompletionReasons().add(ir);
    }

    /**
     * Sets the description explaining for why the student has or has not yet
     * graduated.
     *
     * @param graduationMessage The text to display in the summary of reports.
     */
    public void setGraduationMessage(final String graduationMessage) {
        this.graduationMessage = graduationMessage;
    }

    /**
     * Changes the list of reasons why the student has yet to graduate.
     *
     * @param incompletionReasons The text to display in the summary of reports.
     */
    public void setIncompletionReasons(final List<IncompletionReason> incompletionReasons) {
        this.incompletionReasons = incompletionReasons;
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<Status, Builder> {

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
         * @return New Status instance.
         */
        @Override
        protected Status createObject() {
            return new Status();
        }

        /**
         * Sets the text that informs the student of their graduation status
         * (graduated, honors, did not graduate, etc.).
         *
         * @param graduationMessage Describes whether the student graduated or
         * not.
         * @return thisBuilder
         */
        public Builder withGraduationMessage(final String graduationMessage) {
            getObject().setGraduationMessage(graduationMessage);
            return thisBuilder();
        }

        /**
         * Sets the list of incompletion reasons.
         *
         * @param incompletionReasons Reasons the student has not yet graduated.
         * @return thisBuilder
         */
        public Builder withIncompletionReasons(final List<IncompletionReason> incompletionReasons) {
            getObject().setIncompletionReasons(incompletionReasons);
            return thisBuilder();
        }
    }
}
