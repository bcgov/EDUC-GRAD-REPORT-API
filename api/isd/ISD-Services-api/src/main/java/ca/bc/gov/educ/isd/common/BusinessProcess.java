/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        BusinessProcess.java 
 *  Date of Last Commit: $Date:: 2016-04-06 13:01:46 -0700 (Wed, 06 Apr 2016)  $  
 *  Revision Number:     $Rev:: 1115                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.common;

/**
 * A business process.
 * @author CGI Information Management Consultants Inc.
 * @version 1.0
 * @updated 10-Nov-2015 4:44:13 PM
 */
public interface BusinessProcess extends BusinessService {
    
    /** The version of the business process. 
     * 
     * @return A string identifying a unique version of this business process.
     */
    String getVersion();
    
    /** The coded value for the name of this business process.
     * 
     * The code for the business process name is used to determine the 
     * user-friendly name of the business process using the code set service.
     * <p>
     * Further the name and version of this business process (together) must 
     * for a unique value in the globally deployed ISD system.  This name and 
     * version combination can then be used to register or retrieve code set 
     * values relevant to the business process.
     * 
     * @return  The coded value for the business process name.
     */
    String getNameCode();
    
    /** Get the coded value for the processes current state.
     * <p>
     * The human-readable form for the state code can be retrieved from 
     * the Code Set Service.
     * 
     * @return Coded Value for the current process state.
     */
    String getStateCode();
    
    
}
