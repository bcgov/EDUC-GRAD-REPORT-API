/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: TaskAdminService.java 10324 2018-05-25 19:08:12#$
 *  Date of Last Commit: $Date:: 2018-05-25 12:08:12 -0700 (Fri, 25 May 2018)  $
 *  Revision Number:     $Rev:: 10324                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.task;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchObject;
import ca.bc.gov.educ.isd.common.SortObject;
import java.util.List;

/**
 * Task and task management functions for administrators.
 *
 * Tasks are created by services that require manual intervention to address an
 * issue. This includes but is not exclusive to orders for physical transcripts
 * and issues with students PENs.
 *
 * The task admin service allows for the creation, reading, writing, searching,
 * and removal of any tasks.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TaskAdminService extends CommonEntityService<Task, Task> {

    /**
     * Paged search for an entity based on a set of fields and values.
     *
     * The paging is accomplished through a page number and page size parameters
     * as follows
     *
     * @see #search(java.util.Map)
     * @param filterCriteria List of SearchObjects
     * @param pageSize The number of records on each page.
     * @param pageNum The page number to retrieve (determined by the page size)
     * @param sortCriteria List of Order objects
     * @return
     * @throws DomainServiceException If there is an error accessing the data or
     * if the entity does not exist.
     */
    List<Task> search(
            List<SearchObject> filterCriteria,
            Integer pageSize,
            Integer pageNum,
            List<SortObject> sortCriteria)
            throws DomainServiceException;

    /**
     * Looks up the given task's lastUpdatedBy and makes a determination about
     * whether the value represents an admin account. In that case, the task's
     * adminLastUpdatedBy value is set.
     *
     * @param task The task with an admin last updated by to modify.
     * @throws DomainServiceException Could not determine whether the last
     * updated by account belongs to an admin.
     */
    void updateAdminLastUpdatedBy(Task task) throws DomainServiceException;

}
