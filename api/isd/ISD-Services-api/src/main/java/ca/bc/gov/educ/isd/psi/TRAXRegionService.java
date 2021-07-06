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
 *  File:                TRAXRegionService.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.psi;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TRAXRegionService extends Serializable {

    Map<String, List<? extends Region>> readAllRegions();
}
