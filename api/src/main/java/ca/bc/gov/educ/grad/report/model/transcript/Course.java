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
 *  File:                $Id:: Course.java 9753 2018-03-23 22:21:32Z DAJARVIS  $
 *  Date of Last Commit: $Date:: 2018-03-23 15:21:32 -0700 (Fri, 23 Mar 2018)  $
 *  Revision Number:     $Rev:: 9753                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.transcript;

import ca.bc.gov.educ.grad.report.model.common.DomainEntity;
import ca.bc.gov.educ.grad.report.model.course.ReportCourseType;

/**
 * Course associated with a student's transcript.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Course extends DomainEntity {

    /**
     * Returns the course name (e.g., "LD History AP 12").
     *
     * @return A non-null string that contains the name of the course taken.
     */
    String getName();

    /**
     * Returns the course code associated with the course name (e.g., "XHIAP").
     *
     * @return A non-null string that contains the course code (5 digits max).
     */
    String getCode();

    /**
     * Returns the code that represents the course level (e.g., "12", "12A").
     *
     * @return A non-null string that contains the course level (3 digits max).
     */
    String getLevel();

    /**
     * Returns a string that represents the credits earned by a student for
     * completing this course suitable for displaying to the user. This can
     * return results such as {2p, 2, (4), 4, 1}.
     *
     * @return A non-null, possibly empty String.
     */
    String getCredits();

    /**
     * Returns when the course was offered.
     *
     * @return The date the course was offered (or taken?).
     */
    String getSessionDate();

    /**
     * Returns the course type code for this course.
     *
     * @return A code value from ReportCourseType enumerated type.
     * @see ReportCourseType
     */
    String getType();

    /**
     * Returns the related course code.
     *
     * @return
     */
    String getRelatedCourse();

    /**
     * Returns the grade level of the related course.
     *
     * @return
     */
    String getRelatedLevel();

}
