/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        RoleGroup.java 
 *  Date of Last Commit: $Date:: 2016-09-22 07:53:27 -0700 (Thu, 22 Sep 2016)  $  
 *  Revision Number:     $Rev:: 3760                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.users;

import ca.bc.gov.educ.isd.common.DomainEntity;

import java.util.List;

/**
 * A collection of roles that can be applied to a <code>UserProfile</code> in
 * order to authorize a user for a logical group of resources or services
 * 
 * @author CGI Information Management Consultants Inc.
 */
public interface RoleGroup extends DomainEntity {

    /**
     * Gets the name of the role group.
     * 
     * @return The name of the role group.
     */
    String getName();
    
    /**
     * Gets the description of the role group.
     *
     * @return The description of the role group.
     */
    String getDescription();
    
    /**
     * Gets the list of roles included in the role group.
     * 
     * @return The list of roles included in the role group.
     */
    List<Role> getRoles();
    
}
