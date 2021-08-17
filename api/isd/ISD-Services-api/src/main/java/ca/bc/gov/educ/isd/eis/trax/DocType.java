/* *********************************************************************
 *  Copyright (c) 2015, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        DocType.java 
 *  Date of Last Commit: $Date:: 2016-09-27 16:17:37 -0700 (Tue, 27 Sep 2016)  $  
 *  Revision Number:     $Rev:: 3856                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */
package ca.bc.gov.educ.isd.eis.trax;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum DocType {

    XCPT("Transcript");

    private final String docType;

    private DocType(final String docType) {
        this.docType = docType;
    }

    public String toFileString() {
        String retValue;

        switch (docType) {
            case "Transcript":
                retValue = "XCPT";
                break;
            default:
                retValue = "UNKNOWN";
        }

        return retValue;
    }

}
