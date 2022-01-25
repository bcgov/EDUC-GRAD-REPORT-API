package ca.bc.gov.educ.grad.report.model.common.party.address;

import ca.bc.gov.educ.grad.report.model.common.DomainEntity;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;

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

