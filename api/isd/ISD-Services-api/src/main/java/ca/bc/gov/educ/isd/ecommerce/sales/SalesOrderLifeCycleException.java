/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        SalesOrderLifeCycleException.java 
 *  Date of Last Commit: $Date:: 2016-08-25 10:55:55 -0700 (Thu, 25 Aug 2016)  $  
 *  Revision Number:     $Rev:: 3100                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.common.DomainEntity;
import ca.bc.gov.educ.isd.common.DomainServiceException;

/** A domain service exception related to the lifecycle of sales orders and 
 * sales order items.
 * 
 * <p>This simple wrapper exception is used to indicate specific problems 
 * interacting with Sales Orders, such as attempting to manipulated invalid 
 * order items.  Or the checkout of an order which does not have all 
 * of its delivery information set.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class SalesOrderLifeCycleException extends DomainServiceException {

    private static final long serialVersionUID = 751061858648985268L;

    public SalesOrderLifeCycleException(DomainEntity entity) {
        super(entity);
    }

    public SalesOrderLifeCycleException(DomainEntity entity, String message) {
        super(entity, message);
    }

    public SalesOrderLifeCycleException(DomainEntity entity, String msg, Throwable cause) {
        super(entity, msg, cause);
    }

    public SalesOrderLifeCycleException(DomainEntity entity, Throwable cause) {
        super(entity, cause);
    }

    public SalesOrderLifeCycleException(DomainEntity entity, String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(entity, msg, cause, enableSuppression, writableStackTrace);
    }
    
}
