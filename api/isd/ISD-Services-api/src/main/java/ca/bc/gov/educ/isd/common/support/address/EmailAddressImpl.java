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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.common.support.address;

import ca.bc.gov.educ.isd.common.party.address.EmailAddress;
import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * @author CGI Information Management Consultants Inc.
 */
public final class EmailAddressImpl extends AbstractDomainEntity implements EmailAddress {

    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = EmailAddressImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private InternetAddress inetAddress;

    public EmailAddressImpl(final String email) throws AddressException {
        setEmailAddress(email);
    }

    /**
     * TODO: Throw a domain service exception if the address is null.
     *
     * @return The email address associated with this validating implementation,
     * never null.
     */
    @Override
    public InternetAddress getEmailAddress() {
        return this.inetAddress == null ? new InternetAddress() : this.inetAddress;
    }

    @Override
    public void setEmailAddress(final String address) throws AddressException {
        this.inetAddress = new InternetAddress(address);
        this.inetAddress.validate();
        LOG.log(Level.FINE, "Validated email address.");
    }

    /**
     * Returns the e-mail address as a String.
     *
     * @return A non-null String, possibly empty.
     */
    @Override
    public String toString() {
        return getEmailAddress().toString();
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
