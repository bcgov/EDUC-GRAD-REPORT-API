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
 *  File:                $Id:: TaskHistory.java 8911 2017-12-07 01:11:44Z bbat#$
 *  Date of Last Commit: $Date:: 2017-12-06 17:11:44 -0800 (Wed, 06 Dec 2017)  $
 *  Revision Number:     $Rev:: 8911                                           $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.task;

import ca.bc.gov.educ.isd.common.DomainEntity;
import ca.bc.gov.educ.isd.common.SearchResult;
import java.util.Date;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TaskHistory extends DomainEntity, SearchResult {

    public Date getTimeStamp();

    public String getValueType();

    public String getActionType();

    public String getModifiedBy();

    public String getNotes();

    public Task getTask();

    public String getCodeType();

    public String getValue();
}
