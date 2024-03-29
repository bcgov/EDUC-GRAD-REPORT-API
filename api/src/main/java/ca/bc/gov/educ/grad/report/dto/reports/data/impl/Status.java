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
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static ca.bc.gov.educ.grad.report.model.common.support.StringUtils.findPositions;
import static ca.bc.gov.educ.grad.report.model.common.support.StringUtils.nearestValue;

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
        //add new line if length > 30 chars
        List<IncompletionReason> incompletionReasonsResult = new ArrayList<>();
        Queue<Integer> blankPos = new LinkedList<>();
        for(IncompletionReason incompleteReason: incompletionReasons) {
            String desc = incompleteReason.getDescription();
            if(desc != null && desc.length() > 48) {
                List<Integer> blankPositions = findPositions(desc,' ');
                desc = new StringBuilder(desc).insert(nearestValue(48, blankPositions), '\n').toString();
                String[] parts = StringUtils.split(desc, '\n');

                IncompletionReason r1 = new IncompletionReason();
                r1.setCode(incompleteReason.getCode());
                r1.setDescription(parts[0]);
                incompletionReasonsResult.add(r1);

                IncompletionReason blank = new IncompletionReason();
                blank.setCode(UUID.randomUUID().toString());
                incompletionReasonsResult.add(blank);
                blankPos.add(incompletionReasonsResult.indexOf(blank));

                IncompletionReason r2 = new IncompletionReason();
                r2.setCode(incompleteReason.getCode());
                r2.setDescription(parts[1]);
                incompletionReasonsResult.add(r2);

            }
        }
        for(IncompletionReason incompleteReason: incompletionReasons) {
            if(!incompletionReasonsResult.contains(incompleteReason)) {
                int pos = incompletionReasonsResult.size();
                if(!blankPos.isEmpty()) {
                    pos = blankPos.poll();
                    incompletionReasonsResult.set(pos, incompleteReason);
                } else {
                    incompletionReasonsResult.add(pos, incompleteReason);
                }
            }
        }
        this.incompletionReasons = incompletionReasonsResult;
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
