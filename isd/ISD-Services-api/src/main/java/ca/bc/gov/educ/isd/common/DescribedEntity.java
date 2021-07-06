/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        DescribedEntity.java
 *  Date of Last Commit: $Date:: 2017-02-06 14:30:56 -0800 (Mon, 06 Feb 2017)  $
 *  Revision Number:     $Rev:: 6074                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.common;

/**
 * Represents an entity that contains both a description and a name.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface DescribedEntity extends DomainEntity {

    /**
     * The entity name.
     *
     * @return A non-null, possibly empty String.
     */
    String getName();

    /**
     * Human-readable text description of the entity.
     *
     * @return A non-null, possibly empty String.
     */
    String getDescription();
}
