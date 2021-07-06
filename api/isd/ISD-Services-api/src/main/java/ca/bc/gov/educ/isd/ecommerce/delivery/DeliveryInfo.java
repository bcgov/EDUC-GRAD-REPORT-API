/* *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: DeliveryInfo.java 7046 2017-05-10 18:27:51Z DAJ#$
 *  Date of Last Commit: $Date:: 2017-05-10 11:27:51 -0700 (Wed, 10 May 2017)  $
 *  Revision Number:     $Rev:: 7046                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.delivery;

import ca.bc.gov.educ.isd.common.DomainEntity;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.party.address.Address;
import ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderItem;

/**
 * Delivery Information for an ordered item. Since each catalogue item may have
 * an a different delivery method and recipient delivery information is recorded
 * for instance of an ordered item.
 *
 * However many types of delivery information have a similar data structure, for
 * this reason they are implemented as a SINGLE TABLE inheritance strategy
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface DeliveryInfo extends DomainEntity {

    String getRecipientName();

    SalesOrderItem getSalesOrderItem();

    void setSalesOrderItem(SalesOrderItem orderItem);

    /**
     * Returns the address information associated with instances of this
     * interface.
     *
     * TODO: DeliveryInfo instances should implement the Address interface. That
     * is, "delivery information" should contain the information necessary to
     * deliver a sales order item. Presently, returning the Address provides a
     * consistent API, but should be reworked so that a DeliveryInfo itself
     * implements the Address interface. Calling "toString" on an Address
     * results in either an e-mail address or mailing address, but could equally
     * be an SFTP server address (and fully qualified path). Calling "toString"
     * on DeliveryInfo doesn't make as much sense as an API call, so a more
     * representative name would be beneficial.
     *
     * @return
     * @throws DomainServiceException
     */
    Address toAddress() throws DomainServiceException;
}
