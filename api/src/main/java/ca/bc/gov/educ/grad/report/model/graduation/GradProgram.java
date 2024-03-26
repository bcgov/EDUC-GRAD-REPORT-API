/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: GradProgram.java 7800 2017-07-28 21:01:59Z DAJA#$
 *  Date of Last Commit: $Date:: 2017-07-28 14:01:59 -0700 (Fri, 28 Jul 2017)  $
 *  Revision Number:     $Rev:: 7800                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.model.graduation;

import ca.bc.gov.educ.grad.report.model.common.DomainEntity;

/**
 * Represents a graduation program.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface GradProgram extends DomainEntity {

    /**
     * Returns the graduation program code (also known as the requirement year).
     * See GraduationProgramCode for a list of possible values.
     *
     * @return A non-null String, never empty.
     */
    GraduationProgramCode getCode();

    /**
     * Changes the graduation program code.
     *
     * @param code The new graduation program code.
     */
    void setCode(GraduationProgramCode code);

    public String getExpiryDate();

    public void setExpiryDate(String value);
}
