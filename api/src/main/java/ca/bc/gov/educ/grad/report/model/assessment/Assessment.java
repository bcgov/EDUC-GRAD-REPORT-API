/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        Assessment.java
 *  Date of Last Commit: $Date:: 
 *  Revision Number:     $Rev:: 
 *  Last Commit by:      $Author::
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.model.assessment;

import ca.bc.gov.educ.grad.report.model.common.DomainEntity;

import java.util.Date;
import java.util.List;


/**
 * The interface Assessment.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Assessment extends DomainEntity {
    /**
     * Returns a list of results that contains assessment that a
     * student performed.
     *
     * @return The list of assessmentResult for a student's provincial assessment.
     */
    List<AssessmentResult> getResults();

    /**
     * Convenience method that answers whether the exam has any exam results.
     *
     * @return true iff getResults().isEmpty() == true.
     */
    boolean isEmpty();

    /**
     * Gets issue date.
     *
     * @return the issue date
     */
    Date getIssueDate();
}
