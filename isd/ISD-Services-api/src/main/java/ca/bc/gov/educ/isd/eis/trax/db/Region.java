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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.trax.db;

/**
 * Geographic region (Province or State).
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Region extends TRAXData {

    public String getRegionCode();

    public void setRegionCode(String provCode);

    public String getRegionName();

    public void setRegionName(String provName);

    public String getCntryCode();

    public void setCntryCode(String cntryCode);
}
