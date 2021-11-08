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
 *  File:                $Id:: PersonalEducationNumber.java 8289 2017-09-26 23#$
 *  Date of Last Commit: $Date:: 2017-09-26 16:04:07 -0700 (Tue, 26 Sep 2017)  $
 *  Revision Number:     $Rev:: 8289                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.student;

/**
 * @author CGI Information Management Consultants Inc.
 */

public interface PersonalEducationNumber {

    String getValue();

    String getEntityId();
    void setEntityId(String entityId);

    public String getPen();
    public void setPen(String pen);
}
