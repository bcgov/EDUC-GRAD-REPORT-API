/*
 * ***********************************************************************
 *  Copyright (c) 2018, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id::                                           $
 *  Date of Last Commit: $Date::                                         $
 *  Revision Number:     $Rev::                                          $
 *  Last Commit by:      $Author::                                       $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.trax.db;

import java.io.Serializable;

/**
 * Represents a minimal school master record.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SchoolMaster extends Serializable {

    String getMincode();

    String getDistno();

    String getSchlno();

    /**
     * Comprised of district number and school number.
     *
     * @return A non-null instance, possibly empty.
     */
    String getSchoolCategoryCode();

}
