/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        CodeSetAdminService.java 
 *  Date of Last Commit: $Date:: 2018-05-18 08:40:07 -0700 (Fri, 18 May 2018)  $  
 *  Revision Number:     $Rev:: 10208                                          $  
 *  Last Commit by:      $Author:: DAJARVIS                                    $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.codes;

import ca.bc.gov.educ.isd.common.CommonEntityService;

/**
 * Provides the ability to set codes and codeset values as well as activate and deactivate codes.
 * 
 * @author CGI Information Management Consultants Inc.
 */
public interface CodeSetAdminEntityService extends CommonEntityService<CodeSet, CodeSetSearchResult>  {

}
