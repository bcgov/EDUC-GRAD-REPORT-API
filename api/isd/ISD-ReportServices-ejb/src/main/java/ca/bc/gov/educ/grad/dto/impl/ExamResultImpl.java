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
 *  File:                ExamResultImpl.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.dto.impl;

import ca.bc.gov.educ.grad.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.model.exam.ExamResult;
import ca.bc.gov.educ.grad.model.transcript.Mark;

import java.util.Objects;

/**
 * A container for an individual exam result with the name of the course, the
 * session in * which the course was taken and the exam marks.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ExamResultImpl extends AbstractDomainEntity
        implements ExamResult, Comparable<ExamResult> {

    private static final long serialVersionUID = 3L;

    private String title = "";
    private String sessionDate = "";
    private String courseCode = "";
    private Mark examMark = new MarkImpl();

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getSessionDate() {
        return this.sessionDate;
    }

    @Override
    public Mark getMark() {
        return this.examMark;
    }

    @Override
    public String getCourseCode() {
        return this.courseCode;
    }

    /**
     * set the course name.
     * <p>
     * @param title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * set session date the exam was conducted.
     * <p>
     * @param sessionDate
     */
    public void setSessionDate(final String sessionDate) {
        this.sessionDate = sessionDate;
    }

    /**
     * set the exam mark object.
     * <p>
     * @param examMark
     */
    public void setExamMark(final Mark examMark) {
        this.examMark = examMark;
    }

    public void setCourseCode(final String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public int compareTo(ExamResult o) {
        int sessionComp = this.sessionDate.compareTo(o.getSessionDate());
        int titleComp = this.title.compareToIgnoreCase(o.getTitle());
        return (sessionComp != 0 ? sessionComp : titleComp);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.title);
        hash = 13 * hash + Objects.hashCode(this.sessionDate);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final ExamResultImpl other = (ExamResultImpl) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }

        return Objects.equals(this.sessionDate, other.sessionDate);
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
