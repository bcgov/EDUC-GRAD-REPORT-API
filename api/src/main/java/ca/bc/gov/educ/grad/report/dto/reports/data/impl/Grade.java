/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education and Child Care, BC.
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

import static ca.bc.gov.educ.grad.report.dto.reports.data.BusinessEntity.nullSafe;

/**
 * Represents a grade percentage and corresponding letter grade. Final and
 * interim marks are both considered grades.
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Percentage grade for a course. This can be an acronym, as well as a
     * numeric percentage.
     */
    private String percent;

    /**
     * Letter grade the student achieved for a course.
     */
    private String letter;

    /**
     * Constructs a grade with a given percent and letter. In theory the letter
     * can be calculated based on the percent value, but for now the calculation
     * is performed by business logic elsewhere.
     *
     * @param percent 0 - 100 or a three-letter code.
     * @param letter A to C (with or without +/-), empty, or null.
     */
    public Grade(final String percent, final String letter) {
        setPercent(percent);
        setLetter(letter);
    }

    /**
     * Sets the percentage (or three-letter code) for this grade.
     *
     * @param percent Passed in during construction.
     */
    private void setPercent(final String percent) {
        this.percent = percent;
    }

    /**
     * Sets the letter for this grade (e.g., A to C with + and -).
     *
     * @param letter Passed in during construction.
     */
    private void setLetter(final String letter) {
        this.letter = letter;
    }

    /**
     * Grade as a percentage, but also includes status codes.
     *
     * @return A number from 0 - 100 or a three-letter courseCode.
     */
    public String getPercent() {
        return nullSafe(this.percent);
    }

    /**
     * Grade percentage translated into a letter grade.
     *
     * @return A letter, typically between A through C with + and - symbols.
     */
    public String getLetter() {
        return nullSafe(this.letter);
    }
}
