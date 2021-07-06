/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
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

import java.io.Serializable;

/**
 * Root TRAX Data interface. All data value objects returned from TRAX queries
 * extend from this.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TRAXData extends Serializable {

    /**
     * A suggested timeout value used in the query hints.
     */
    static final int TIMEOUT = 15 * 60 * 1000;

}
