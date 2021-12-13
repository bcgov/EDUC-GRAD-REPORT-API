/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
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
package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.report.model.transcript.Course;

import java.util.Objects;

import static java.lang.String.format;

/**
 * This class models a course.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class CourseImpl extends AbstractDomainEntity implements Course {

    private static final long serialVersionUID = 4L;

    private String name = "";
    private String code = "";
    private String level = "";
    private String credits = "";
    private String sessionDate = "";
    private String type = "";
    private String relatedCourse = "";
    private String relatedLevel = "";

    public CourseImpl() {
    }

    public CourseImpl(
            final String name,
            final String code,
            final String level,
            final String credits,
            final String session,
            final String type,
            final String relatedCourse,
            final String relatedLevel) {
        this.name = name;
        this.code = code;
        this.level = level;
        this.credits = credits;
        this.sessionDate = session;
        this.type = type;
        this.relatedCourse = relatedCourse;
        this.relatedLevel = relatedLevel;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getLevel() {
        return this.level;
    }

    @Override
    public String getCredits() {
        return this.credits;
    }

    @Override
    public String getSessionDate() {
        return this.sessionDate;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getRelatedCourse() {
        return this.relatedCourse;
    }

    public void setRelatedCourse(String relatedCourse) {
        this.relatedCourse = relatedCourse;
    }

    @Override
    public String getRelatedLevel() {
        return this.relatedLevel;
    }

    public void setRelatedLevel(String relatedLevel) {
        this.relatedLevel = relatedLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    /**
     * Returns a unique identifier for this instance.
     *
     * @return A unique number.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.code);
        hash = 67 * hash + Objects.hashCode(this.level);
        hash = 67 * hash + Objects.hashCode(this.sessionDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CourseImpl other = (CourseImpl) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        return Objects.equals(this.sessionDate, other.sessionDate);
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return format("%s("
                + "courseName = <%s>, "
                + "courseCode = <%s>, "
                + "courseLevel = <%s>, "
                + "courseType = <%s>"
                + ")",
                getClass().getSimpleName(),
                getName(),
                getCode(),
                getLevel(),
                getType());
    }
}
