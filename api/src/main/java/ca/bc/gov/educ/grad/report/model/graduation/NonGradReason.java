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
 *  File:                $Id:: NonGradReason.java 3100 2016-08-25 17:55:55Z cp#$
 *  Date of Last Commit: $Date:: 2016-08-25 10:55:55 -0700 (Thu, 25 Aug 2016)  $
 *  Revision Number:     $Rev:: 3100                                           $
 *  Last Commit by:      $Author:: cprince                                     $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.graduation;

import ca.bc.gov.educ.grad.report.model.common.DomainEntity;

/**
 * Represents a reason why a student has not yet graduated.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface NonGradReason extends DomainEntity {

    /**
     * Returns the code that represents why the student did not fulfill their
     * graduation requirements.
     *
     * @return A non-null String, should not be empty.
     */
    String getCode();

    /**
     * Returns the description of why the student did not fulfill their
     * graduation requirements.
     *
     * @return A non-null String, should not be empty.
     */
    String getDescription();
}
