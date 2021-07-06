/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        RoleGroupSearchResult.java 
 *  Date of Last Commit: $Date:: 2016-09-22 07:53:27 -0700 (Thu, 22 Sep 2016)  $  
 *  Revision Number:     $Rev:: 3760                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.users;

import ca.bc.gov.educ.isd.common.SearchResult;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface RoleGroupSearchResult extends SearchResult {

    @Override
    Long getId();

    String getName();

    String getDescription();

}
