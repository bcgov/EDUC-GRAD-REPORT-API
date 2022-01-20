package ca.bc.gov.educ.grad.report.model.reports;

import ca.bc.gov.educ.grad.report.model.common.DomainEntity;
import ca.bc.gov.educ.grad.report.model.common.party.address.PostalAddress;

import java.util.Date;

/**
 * Represents the information required to fill out a packing slip.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PackingSlipDetails extends DomainEntity {

    public static final String TRANSCRIPTS = "Transcripts";
    public static final String CERTIFICATES = "Certificates";

    /**
     * Returns the name of the student who has transcript and certificate
     * records that comprise the package to which this packing slip applies.
     *
     * @return A non-null String, possibly empty.
     */
    String getRecipient();

    /**
     * Returns the delivery address for mailing the transcripts and
     * certificates.
     *
     * @return A mailing address instance suitable for directing postal mail,
     * never null.
     */
    PostalAddress getAddress();

    /**
     * Returns the name of the person who ordered the transcripts and
     * certificates on behalf of the student whose records are sought.
     *
     * @return A non-null String, possibly empty.
     */
    String getOrderedByName();

    /**
     * Returns the number of documents included in the package.
     *
     * @return A whole number, never null.
     */
    Integer getDocumentsShipped();

    /**
     * Returns the STs order number.
     *
     * @return A non-null String, possibly empty.
     */
    String getOrderNumber();

    /**
     * Returns the date the packing slip was transmitted to the postal mail
     * handler. The transmission date should not be null when constructing an
     * instance of this class.
     *
     * @return The transmission date or the current date in the event that the
     * transmission date is null. This must not return null.
     */
    Date getOrderDate();

    /**
     * Indicates whether this packing slip is destined for a post-secondary
     * institution.
     *
     * @return PSI or null.
     */
    DestinationType getDestinationType();
}

