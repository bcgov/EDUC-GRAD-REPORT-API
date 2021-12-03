/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        BusinessProcessException.java 
 *  Date of Last Commit: $Date:: 2016-04-06 13:01:46 -0700 (Wed, 06 Apr 2016)  $  
 *  Revision Number:     $Rev:: 1115                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.grad.model.common;

/**
 * Application exception root for Business Process Exceptions.
 * @author CGI Information Management Consultants Inc.
 * @version 1.0
 * @updated 10-Nov-2015 4:44:13 PM
 */
public class BusinessProcessException extends DomainServiceException {

    private static final long serialVersionUID = -3420386927441515036L;

    public BusinessProcessException(final DomainEntity entity) {
        super(entity);
    }

    public BusinessProcessException(final DomainEntity entity, final String message) {
        super(entity, message);
    }

    public BusinessProcessException(final DomainEntity entity, final String message, final Throwable cause) {
        super(entity, message, cause);
    }

    public BusinessProcessException(final DomainEntity entity, final Throwable cause) {
        super(entity, cause);
    }

    public BusinessProcessException(final DomainEntity entity, final String message, final Throwable cause, final boolean enableSuppression, final boolean writable) {
        super(entity, message, cause, enableSuppression, writable);
    }


}
