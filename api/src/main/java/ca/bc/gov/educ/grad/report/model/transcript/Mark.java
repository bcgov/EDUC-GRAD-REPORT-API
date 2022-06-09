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
 *  File:                $Id:: Mark.java 6146 2017-02-10 20:22:37Z DAJARVIS    $
 *  Date of Last Commit: $Date:: 2017-02-10 12:22:37 -0800 (Fri, 10 Feb 2017)  $
 *  Revision Number:     $Rev:: 6146                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.transcript;

import ca.bc.gov.educ.grad.report.model.common.DomainEntity;

/**
 * Represents a mark achieved by a student for a course.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Mark extends DomainEntity {

    /**
     * On the transcript report, this is the Gr. 12 School % column.
     *
     * @return A number from 0 - 100, or a three-letter code.
     */
    String getSchoolPercent();

    /**
     * Returns the best school percentage value for an examination result.
     *
     * @return A number from 0 - 100.
     */
    String getBestSchoolPercent();

    /**
     * On the transcript report, this is the Gr. 12 Exam % column.
     *
     * @return A number from 0 - 100, or a three-letter code.
     */
    String getExamPercent();

    /**
     * Returns the best examination percentage value for an examination result.
     *
     * @return A number from 0 - 100.
     */
    String getBestExamPercent();

    /**
     * Final grade as a percentage, but also includes status codes.
     *
     * @return A number from 0 - 100 or a three-letter code.
     */
    String getFinalPercent();

    /**
     * Final grade percentage translated into a letter grade.
     *
     * @return A letter, typically between A through F with + and - symbols.
     */
    String getFinalLetterGrade();

    /**
     * Interim grade as a percentage, but also includes status codes.
     *
     * @return A number from 0 - 100 or a three-letter code.
     */
    String getInterimPercent();

    /**
     * Interim grade percentage translated into a letter grade.
     *
     * @return A letter, typically between A through F with + and - symbols.
     */
    String getInterimLetterGrade();
}
