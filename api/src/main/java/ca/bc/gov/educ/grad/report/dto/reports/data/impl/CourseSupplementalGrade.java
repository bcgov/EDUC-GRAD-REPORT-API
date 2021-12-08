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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.data.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CourseSupplementalGrade implements Serializable {

    private String supplementalGradeCode;
    private String courseAcademicSupplementalGradeScaleCode;
    private String courseAcademicSupplementalGradeStatusCode;
    private String courseAcademicSupplementalGrade;

    public String getSupplementalGradeCode() {
        return supplementalGradeCode;
    }

    public void setSupplementalGradeCode(final String supplementalGradeCode) {
        this.supplementalGradeCode = supplementalGradeCode;
    }

    public String getCourseAcademicSupplementalGradeScaleCode() {
        return courseAcademicSupplementalGradeScaleCode;
    }

    public void setCourseAcademicSupplementalGradeScaleCode(final String courseAcademicSupplementalGradeScaleCode) {
        this.courseAcademicSupplementalGradeScaleCode = courseAcademicSupplementalGradeScaleCode;
    }

    public String getCourseAcademicSupplementalGradeStatusCode() {
        return courseAcademicSupplementalGradeStatusCode;
    }

    public void setCourseAcademicSupplementalGradeStatusCode(final String courseAcademicSupplementalGradeStatusCode) {
        this.courseAcademicSupplementalGradeStatusCode = courseAcademicSupplementalGradeStatusCode;
    }

    public String getCourseAcademicSupplementalGrade() {
        return courseAcademicSupplementalGrade;
    }

    public void setCourseAcademicSupplementalGrade(final String courseAcademicSupplementalGrade) {
        this.courseAcademicSupplementalGrade = courseAcademicSupplementalGrade;
    }
}
