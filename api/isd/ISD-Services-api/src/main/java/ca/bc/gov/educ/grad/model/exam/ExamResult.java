/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        ExamResult.java
 *  Date of Last Commit: $Date:: 2018-04-16 16:18:34 -0700 (Mon, 16 Apr 2018)  $
 *  Revision Number:     $Rev:: 9956                                           $
 *  Last Commit by:      $Author:: JASTAPLE                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.model.exam;

import ca.bc.gov.educ.grad.model.common.DomainEntity;
import ca.bc.gov.educ.grad.model.transcript.Mark;

/**
 * Represents additional information about a course grade that constitutes an
 * examination result.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ExamResult extends DomainEntity {

    /**
     * Returns the examination title. The name of the exam and the name of a
     * course are different.
     *
     * @return The title for an examination taken by a student.
     */
    String getTitle();

    /**
     * Returns the session during which the examination was offered.
     *
     * @return The month and year the course was offered (or taken?).
     */
    String getSessionDate();

    /**
     * Returns the mark associated with this examination result.
     *
     * @return A non-null mark including percentages and a final letter grade.
     */
    Mark getMark();

    /**
     * Returns the Assessment Code. The name of the exam and the name of a
     * course are different.
     *
     * @return The assessment code for an examination taken by a student.
     */
    String getCourseCode();
}
