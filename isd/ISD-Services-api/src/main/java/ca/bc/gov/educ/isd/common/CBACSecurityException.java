/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        CBACSecurityException.java 
 *  Date of Last Commit: $Date:: 2016-04-06 13:01:46 -0700 (Wed, 06 Apr 2016)  $  
 *  Revision Number:     $Rev:: 1115                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.common;

/**
 * Context Based Access Control exception.
 * <p> This exception is used to indicate that the operation cannot be performed
 * because the user attempted an operation on records other than their own. </p>
 * <p> The user may be permitted to perform the operation, but only on their own
 * data.
 * </p><p> For example, a user can modify elements of their own user profile, but
 * cannot modify elements of another user's profile. In this case they have Role-
 * Based-Access-Control privileges to call the modify user profile operation, but
 * they may only call in the context of their own records.</p>
 * @author CGI Information Management Consultants Inc.
 * @version 1.0
 * @updated 10-Nov-2015 4:44:14 PM
 */
public class CBACSecurityException extends DataException {

    private static final long serialVersionUID = 3616494209832167517L;

    /**
     *
     * @param eID
     * @param entity
     */
    public CBACSecurityException(final String eID, final DomainEntity entity) {
        super(eID, entity);
    }

    /**
     *
     * @param eID
     * @param entity
     * @param message
     */
    public CBACSecurityException(final String eID, final DomainEntity entity, final String message) {
        super(eID, entity, message);
    }

    /**
     *
     * @param eID
     * @param entity
     * @param message
     * @param cause
     */
    public CBACSecurityException(final String eID, final DomainEntity entity, final String message, final Throwable cause) {
        super(eID, entity, message, cause);
    }

    /**
     *
     * @param eID
     * @param entity
     * @param cause
     */
    public CBACSecurityException(final String eID, final DomainEntity entity, final Throwable cause) {
        super(eID, entity, cause);
    }

    /**
     *
     * @param eID
     * @param entity
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    @SuppressWarnings("LongVariable")  // Long Variable name is inherited from external api.
    public CBACSecurityException(final String eID, final DomainEntity entity, final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(eID, entity, message, cause, enableSuppression, writableStackTrace);
    }


}
