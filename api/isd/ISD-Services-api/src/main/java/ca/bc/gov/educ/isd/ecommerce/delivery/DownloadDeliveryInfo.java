/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: DownloadDeliveryInfo.java 9028 2017-12-13 21:34#$
 *  Date of Last Commit: $Date:: 2017-12-13 13:34:05 -0800 (Wed, 13 Dec 2017)  $
 *  Revision Number:     $Rev:: 9028                                           $
 *  Last Commit by:      $Author:: cfunk                                       $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.delivery;

import java.util.Date;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface DownloadDeliveryInfo extends DeliveryInfo {

    /**
     * Get the number of attempts at answering the security
     * challenge.
     * 
     * @return the number of challenge attempts.
     */
    Integer getNumAttempts();

    /**
     * Get the date the order was placed.
     * 
     * @return the date the order was placed.
     */
    Date getOrderDate();

    /**
     * Get the date when the download becomes unavailable.
     * 
     * @return the date where the document can no longer be downloaded.
     */
    Date getExpiryDate();

    /**
     * Get the security answer.
     * 
     * @return the answer to the challenge.
     */
    String getSecurityAnswer();

    /**
     * Get the security question.
     * 
     * @return the security question.
     */
    String getSecurityQuestion();

    /**
     * Get the recipient email address
     * 
     * @return the email address.
     */
//    FIXME: This should return an Address object.
    String getEmail();
    
    /**
     * Get the number of successful downloads for this document.
     * 
     * @return the number of successful download.
     */
    Integer getNumSuccessfuldownloads();

    

    

   

   

   

   

}
