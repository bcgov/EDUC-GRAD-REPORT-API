/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: Certificate.java 3760 2016-09-22 14:53:27Z cpri#$
 *  Date of Last Commit: $Date:: 2016-09-22 07:53:27 -0700 (Thu, 22 Sep 2016)  $
 *  Revision Number:     $Rev:: 3760                                           $
 *  Last Commit by:      $Author:: cprince                                     $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.cert;

import ca.bc.gov.educ.isd.codes.SignatureBlockType;
import ca.bc.gov.educ.isd.reports.bundle.service.OrderType;

import java.util.Date;
import java.util.Map;

/**
 * Represents information required to for certificates.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Certificate {

    /**
     * Returns the date (year and month) when the certificate was issued to the
     * student.
     *
     * @return A non-null Date instance.
     */
    Date getIssued();

    /**
     * Returns the flag if the certificate is origin or replacement
     *
     * @return A non-null Date instance.
     */
    boolean getIsOrigin();

    /**
     * Gets certificate type.
     *
     * @return the certificate type
     */
    OrderType getOrderType();

    /**
     * Gets signature block types.
     *
     * @return the Map of SignatureBlockType types
     */

    Map<String, SignatureBlockType> getSignatureBlockTypes();
}
