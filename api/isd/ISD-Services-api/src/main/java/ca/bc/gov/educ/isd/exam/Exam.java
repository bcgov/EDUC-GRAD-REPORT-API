/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        Exam.java
 *  Date of Last Commit: $Date:: 2016-11-01 15:46:26 -0700 (Tue, 01 Nov 2016)  $
 *  Revision Number:     $Rev:: 4983                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.exam;

import ca.bc.gov.educ.isd.common.DomainEntity;
import java.util.Date;
import java.util.List;

/**
 * An exam is comprised of a number of examination results.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Exam extends DomainEntity {

    /**
     * Returns a list of results that contains courses and associated marks a
     * student achieved.
     *
     * @return The list of marks for a student's provincial examination results.
     */
    List<ExamResult> getResults();

    /**
     * Returns the date that the examination results were issued. On the
     * provincial examination report, this is the report date.
     *
     * @return Date that the provincial examination was issued.
     */
    Date getIssueDate();

    /**
     * Convenience method that answers whether the exam has any exam results.
     *
     * @return true iff getResults().isEmpty() == true.
     */
    boolean isEmpty();
}
