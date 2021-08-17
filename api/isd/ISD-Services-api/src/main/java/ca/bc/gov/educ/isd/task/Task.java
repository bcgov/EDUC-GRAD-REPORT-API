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
 *  File:                $Id:: Task.java 10324 2018-05-25 19:08:12Z DAJARVIS   $
 *  Date of Last Commit: $Date:: 2018-05-25 12:08:12 -0700 (Fri, 25 May 2018)  $
 *  Revision Number:     $Rev:: 10324                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.task;

import ca.bc.gov.educ.isd.common.DomainEntity;
import ca.bc.gov.educ.isd.common.SearchResult;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * An individual Task that details issues to be addressed in the manual
 * intervention queue. These contain can contain varying detail depending which
 * service has detailed the task attributes.
 *
 * FIXME: Move accessors into TaskAdmin interface.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Task extends DomainEntity, SearchResult {

    /**
     * Returns the Date Completed.
     *
     * @return The date task was completed.
     */
    Date getDateCompleted();

    /**
     * Returns the time allocated to the task.
     *
     * @return The time to the resolution.
     */
    Long getAllocatedTime();

    /**
     * Return the priority of the task.
     *
     * @return The priority of the task.
     */
    String getPriority();

    /**
     * TODO: Convert to using TaskStatus.
     *
     * Return the task status.
     *
     * @return The status of the task.
     */
    String getTaskStatus();

    /**
     * Return the Issue type that the task was opened for.
     *
     * @return The Issue type that the task was opened for.
     */
    TaskType getIssueType();

    /**
     * Returns the history of tasks.
     *
     * @return A non-null list of tasks.
     */
    List<TaskHistory> getTaskHistory();

    /**
     * Return the session attributes captured when the task was created.
     *
     * @return The Session attributes captured when the task was created.
     */
    Map<String, String> getTaskAttributes();

    /**
     * Return the description of the task.
     *
     * @return The description of the task.
     */
    String getDescription();

    /**
     * Return the entityID of the Assignee.
     *
     * @return The entityID of the Assignee (may be empty)
     */
    String getAssignee();

    /**
     * Return the entityID of the UserProfile that this task is related to.
     *
     * @return The entityID of the User that had the issue (may be empty)
     */
    String getSubjectEntityId();

    /**
     * Return the Task deadline
     *
     * @return The deadline based on allocated time
     */
    Date getTaskDeadline();

    /**
     * Return if the task has been persisted.
     *
     * @return if the task has been persisted. Used for preventing duplicate
     * entries into task history caused by the setters being called twice in the
     * Task Services write method.,
     */
    Map<String, Object> getStartingValues();

    /**
     * Return if the task has been persisted.
     *
     * @return if the task has been persisted. Used for preventing duplicate
     * entries into task history caused by the setters being called twice in the
     * Task Services write method.,
     */
    Map<String, Object> getChangedValues();

    /**
     * Return the Task notes
     *
     * @return The notes written about the task
     */
    String getTaskNotes();

    /**
     * Retrieve name of the user or system that last updated this entity,
     * provided that that user who last updated the task is an administrator.
     * This is a transient value.
     *
     * @return The administrator user who last updated this entity, or the empty
     * string, never null.
     */
    String getAdminLastUpdatedBy();

    /**
     * Set the task priority.
     *
     * @param priority
     */
    void setPriority(String priority);

    /**
     * FIXME: Use TaskStatus enum.
     *
     * Set the task status.
     *
     * @param taskStatus
     */
    void setTaskStatus(String taskStatus);

    /**
     * Set the issue type that pertains to the task.
     *
     * @param issueType
     */
    void setIssueType(TaskType issueType);

    /**
     * Sets the attributes captured when the task was created.
     *
     * @param inMap the descriptors for the task.
     */
    void setTaskAttributes(Map<String, String> inMap);

    /**
     * Set the description type that pertains to the task.
     *
     * @param description
     */
    void setDescription(String description);

    /**
     * Set for task assignee party id.
     *
     * @param assigneeParty
     */
    void setAssignee(String assigneeParty);

    /**
     * Set the Task Subject User
     *
     * @param profileEid profile entity ID.
     */
    void setSubjectEntityId(String profileEid);

    /**
     * Set the Task notes
     *
     * @param taskNotes The notes written about the task
     */
    void setTaskNotes(String taskNotes);

    /**
     * Set name of the user or system that last updated this entity, provided
     * that that user who last updated the task is an administrator. This value
     * is not persisted, but is meant to be derived from last updated by.
     *
     * @param adminLastUpdatedBy The administrator user who last updated this
     * entity, or the empty string, never null.
     */
    void setAdminLastUpdatedBy(String adminLastUpdatedBy);

    /**
     * Changes the allocation time.
     *
     * @param allocatedTime The new time that the task was allocated.
     */
    void setAllocatedTime(final Long allocatedTime);

    /**
     * Changes the deadline when the task must be completed.
     *
     * @param deadline When the task must be completed.
     */
    void setTaskDeadline(final Date deadline);

    /**
     * Adds the given task history item to the list of task histories.
     *
     * @param taskHistory The item to add to the task history list.
     */
    void add(final TaskHistory taskHistory);

}
