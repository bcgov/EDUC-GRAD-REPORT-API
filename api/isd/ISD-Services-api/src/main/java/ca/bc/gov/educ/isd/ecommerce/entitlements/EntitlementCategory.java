/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        EntitlementCategory.java 
 *  Date of Last Commit: $Date:: 2017-01-11 13:02:22 -0800 (Wed, 11 Jan 2017)  $  
 *  Revision Number:     $Rev:: 5786                                           $  
 *  Last Commit by:      $Author:: bbates                                      $ 
 *  
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.entitlements;

import ca.bc.gov.educ.isd.common.DescribedEntity;
import java.util.List;

/**
 * Get the list of entitlements belonging to this particular category.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface EntitlementCategory extends DescribedEntity {

    List<EntitlementItem> getItems();

}
