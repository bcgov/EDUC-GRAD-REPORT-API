package ca.bc.gov.educ.grad.report.model.reports;

import ca.bc.gov.educ.grad.report.model.order.OrderType;

/**
 * Represents information required to generate a packing slip for printed
 * student reports (e.g., transcripts, certificates, etc.).
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PackingSlipReport extends Report {

    /**
     * Mandatory call to set the packing slip delivery details (such as
     * application ID, student records name, locator code, transmitted date,
     * etc.).
     *
     * @param details The details to include on the packing slip.
     */
    void setPackingSlipDetails(ca.bc.gov.educ.grad.report.model.reports.PackingSlipDetails details);

    /**
     * Mandatory call to set the type of packing slip to generate (such as
     * Certificate or Transcript). This can only be called after
     * setPackingSlipDetails is called.
     *
     * @param orderType The packing slip order type.
     */
    void setOrderType(OrderType orderType);

    /**
     * Call when sending print materials to a Post-Secondary Institution. This
     * can be null, and is an optional call.
     *
     * @param destinationType Where to send the print materials.
     */
    void setDestinationType(DestinationType destinationType);
}

