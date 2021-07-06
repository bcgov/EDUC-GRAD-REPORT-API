/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: UserRegistrationException.java 7918 2017-08-11 #$
 *  Date of Last Commit: $Date:: 2017-08-11 11:34:36 -0700 (Fri, 11 Aug 2017)  $
 *  Revision Number:     $Rev:: 7918                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.process.user;

import ca.bc.gov.educ.isd.common.DomainEntity;
import ca.bc.gov.educ.isd.common.DomainServiceException;

/**
 * Indicates a exception in the user registration process.
 */
public class UserRegistrationException extends DomainServiceException {

    private static final long serialVersionUID = 2129457416420989652L;

    // FIXME: Enum
    private String registrationStatusError;

    /**
     * @param message The message that indicates why this problem happened.
     */
    public UserRegistrationException(final String message) {
        super(message);
    }

    /**
     * @param message The message that indicates why this problem happened.
     * @param cause The underlying reason the problem happened, if any.
     */
    public UserRegistrationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param entity the domain entity related to the exception.
     */
    public UserRegistrationException(final DomainEntity entity) {
        super(entity);
    }

    /**
     * @param entity the domain entity related to the exception.
     * @param message an description of the error.
     */
    public UserRegistrationException(final DomainEntity entity, final String message) {
        super(entity, message);
    }

    /**
     * @param entity the domain entity related to the exception.
     * @param message an description of the error.
     * @param registrationStatusError
     */
    public UserRegistrationException(final DomainEntity entity, final String message, final String registrationStatusError) {
        super(entity, message);
        this.registrationStatusError = registrationStatusError;
    }

    public String getRegistrationStatusError() {
        return registrationStatusError;
    }

    void setRegistrationStatusError(String registrationStatusError) {
        this.registrationStatusError = registrationStatusError;
    }

}
