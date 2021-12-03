/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        Course.java 
 *  Date of Last Commit: $Date:: 2016-09-22 07:53:27 -0700 (Thu, 22 Sep 2016)  $  
 *  Revision Number:     $Rev:: 3760                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.grad.model.course;

import ca.bc.gov.educ.grad.model.common.DomainEntity;

import java.util.Date;

/**
 * Course are used for both examination results and transcript results.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Course extends DomainEntity {

    /**
     * Also known as examination title in ExaminationResult.
     *
     * @return A non-null string that contains the name of the course taken.
     */
     String getName();

    /**
     * The course code associated with the course name for the examination
     * title.
     *
     * @return A non-null string that contains the course code (5 digits max).
     */
     String getCode();

    /**
     * Returns the code that represents the course level. For example, in "MATH
     * 10", the "10" is part the course level.
     *
     * @return A non-null string that contains the course level (3 digits max).
     */
     String getLevel();

    /**
     * When the course was offered.
     *
     * @return The date the course was offered (or taken?).
     */
     Date getSessionDate();
}
