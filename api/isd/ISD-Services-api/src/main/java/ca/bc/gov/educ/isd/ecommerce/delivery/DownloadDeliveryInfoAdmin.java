
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
 *   Last Commit by:      $Author:: bbates                                     $
 *
 *
 **********************************************************************  */
package ca.bc.gov.educ.isd.ecommerce.delivery;

import java.util.Date;

/**
 *
 * @author Ministry of Education, BC
 */
public interface DownloadDeliveryInfoAdmin extends DownloadDeliveryInfo, DeliveryInfoAdmin {

    /**
     * Set the number of attempts
     * 
     * @param numAttempts the number of attempts.
     */
    void setNumAttempts(Integer numAttempts);

    /**
     * Set the order date.
     * 
     * @param orderDate the order date.
     */
    void setOrderDate(Date orderDate);

    /**
     * Set the security answer.
     * 
     * @param securityAnswer the security answer.
     */
    void setSecurityAnswer(String securityAnswer);

    /**
     * Set the security question.
     * 
     * @param securityQuestion the security question.
     */
    void setSecurityQuestion(String securityQuestion);

    /**
     * Set the document recipient email address.
     * @param email the email address.
     */
    // FIXME: Change to Address object.
    void setEmail(String email);

    /**
     * Set the recipient name for this document
     * 
     * @param recipientName the recipient name.
     */
    void setRecipientName(String recipientName);

    /**
     * Set the date when the download becomes unavailable.
     * 
     * @param expiryDate the document expiry date.
     */
    void setExpiryDate(Date expiryDate);

    /**
     * Set the number of successful downloads for this document.
     * 
     * @param numDownloads the number of successful downloads.
     */
    void setNumSuccessfulDownloads(Integer numDownloads);

}
