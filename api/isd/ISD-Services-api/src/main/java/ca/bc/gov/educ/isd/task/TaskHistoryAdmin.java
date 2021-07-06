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
 *  File:                $Id:: TaskHistoryAdmin.java 8911 2017-12-07 01:11:44Z#$
 *  Date of Last Commit: $Date:: 2017-12-06 17:11:44 -0800 (Wed, 06 Dec 2017)  $
 *  Revision Number:     $Rev:: 8911                                           $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.task;

import java.util.Date;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TaskHistoryAdmin extends TaskHistory {

    public void setTimeStamp(Date timeStamp);

    public void setValueType(String valueType);

    public void setActionType(String actionType);

    public void setModifiedBy(String modifiedBy);

    public void setNotes(String notes);

    public void setTask(Task task);

    public void setCodeType(String codeType);

    public void setValue(String value);

}
