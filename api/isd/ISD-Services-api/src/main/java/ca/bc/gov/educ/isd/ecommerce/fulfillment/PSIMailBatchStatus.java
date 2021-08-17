
/* *************************************************************************
 *   Copyright (c) 2015, Ministry of Education, BC.
 * 
 *   All rights reserved.
 *     This information contained herein may not be used in whole
 *     or in part without the express written consent of the
 *     Government of British Columbia, Canada.
 * 
 *   Revision Control Information
 *   File:                $Id::                                                 $
 *   Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015)  $
 *   Revision Number:     $Rev:: 36                                             $
 *   Last Commit by:      $Author:: bbates                                      $
 * 
 *  
 **********************************************************************  */
package ca.bc.gov.educ.isd.ecommerce.fulfillment;

/**
 *
 * @author Ministry of Education, BC
 */
public enum PSIMailBatchStatus {
    
  //TODO: RLO - add proper descriptions to this enum.
    NEW("NEW", "New."),
    PROCESSING("PROCESSING", "Processing."),
    SENT("FILLED", "Sent."),
    FAILED("FAILED", "Failed.");

    private final String code;

    private final String description;

    private PSIMailBatchStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

}
