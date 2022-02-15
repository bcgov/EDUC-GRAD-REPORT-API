package ca.bc.gov.educ.grad.report.model.packingslip;

import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;

import java.util.Date;

public interface PackingSlipService {

    /**
     * Creates and returns a packing slip report for an individual. The report
     * document returned is typically a PDF that includes a print-ready packing
     * slip page suitable for bundling with any number of order items (as
     * tallied by size). This creates the appropriate order type based on the
     * catalogue item.
     *
     * @param orderNumber Order number to include on the packing slip.
     * @param ordered Date the order was made.
     * @param quantity The quantity of items included in the packet.
     * @param total The quantity of items included in the packet.
     * @return A ReportDocument that can be bundled with transcripts.
     * @throws DomainServiceException Could not create the packing slip report.
     */
    ReportDocument createPackingSlipReport(
            final Long orderNumber,
            final Date ordered,
            final String orderedBy,
            final int quantity,
            final int total) throws DomainServiceException;


}
