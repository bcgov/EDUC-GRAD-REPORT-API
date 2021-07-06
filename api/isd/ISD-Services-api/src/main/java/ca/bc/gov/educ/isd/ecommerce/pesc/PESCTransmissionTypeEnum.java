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
 *  File:                PESCTransmissionStatusEnum.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.pesc;

/**
 * Indicator of what type of transmission was sent.
 * @author CGI Information Management Consultants Inc.
 */
public enum PESCTransmissionTypeEnum {

    FINAL_TX("Final transmission"), //Send grades now once, read from the interim view
    UPDATES_TX("Transmission with updates"), //Send grades now amd whem a PSI requests them, read from the interim view
    REPLACED("Inactive updates Transmission");

    private String description;

    private PESCTransmissionTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

}
