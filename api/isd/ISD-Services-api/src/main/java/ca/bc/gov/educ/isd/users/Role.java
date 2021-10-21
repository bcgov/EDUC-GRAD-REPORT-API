/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        Role.java 
 *  Date of Last Commit: $Date:: 2016-08-25 10:55:55 -0700 (Thu, 25 Aug 2016)  $  
 *  Revision Number:     $Rev:: 3100                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.users;

import ca.bc.gov.educ.isd.common.DomainEntity;
import ca.bc.gov.educ.isd.common.SearchResult;

import java.util.List;

/**
 * A security role used to authorize access to a specific resource or service.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Role extends DomainEntity, SearchResult {
    
    String getName();
    
    String getDescription();
    
    void setName(String name);
    
    void setDescription(String description);
    
    List<RoleGroup> getRoleGroups();
}
