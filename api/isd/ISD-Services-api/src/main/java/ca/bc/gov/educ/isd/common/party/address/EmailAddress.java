/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: EmailAddress.java 7004 2017-05-05 00:29:41Z DAJ#$
 *  Date of Last Commit: $Date:: 2017-05-04 17:29:41 -0700 (Thu, 04 May 2017)  $
 *  Revision Number:     $Rev:: 7004                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.common.party.address;

import ca.bc.gov.educ.isd.common.DomainEntity;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.Serializable;

/**
 * Represents an email address.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface EmailAddress extends Address, DomainEntity, Serializable {

    /**
     * The email address in the form of its Java EE type.
     *
     * @return
     */
    InternetAddress getEmailAddress();

    /**
     * set the email address and validate it.
     * <p>
     * @param address
     * <p>
     * @throws AddressException
     */
    void setEmailAddress(String address) throws AddressException;
}
