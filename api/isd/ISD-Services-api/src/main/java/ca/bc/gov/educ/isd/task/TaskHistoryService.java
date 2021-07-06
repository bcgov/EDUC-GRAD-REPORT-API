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
 *  File:                $Id:: TaskHistoryService.java 8911 2017-12-07 01:11:4#$
 *  Date of Last Commit: $Date:: 2017-12-06 17:11:44 -0800 (Wed, 06 Dec 2017)  $
 *  Revision Number:     $Rev:: 8911                                           $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.task;

import ca.bc.gov.educ.isd.common.CommonEntityService;

/**
 * Task history management functions.
 *
 * Task histories are logs of information associated with actions performed on a
 * task. This service allows for the creation, reading, writing, searching, and
 * removal of any task history.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TaskHistoryService extends CommonEntityService<TaskHistory, TaskHistory> {
}
