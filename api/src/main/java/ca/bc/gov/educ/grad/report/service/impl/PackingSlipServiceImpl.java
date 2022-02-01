package ca.bc.gov.educ.grad.report.service.impl;

import ca.bc.gov.educ.grad.report.api.client.ReportData;
import ca.bc.gov.educ.grad.report.dao.GradDataConvertionBean;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dto.impl.PackingSlipDetailsImpl;
import ca.bc.gov.educ.grad.report.exception.EntityNotFoundException;
import ca.bc.gov.educ.grad.report.model.common.DataException;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.common.party.address.PostalDeliveryInfo;
import ca.bc.gov.educ.grad.report.model.order.OrderType;
import ca.bc.gov.educ.grad.report.model.packingslip.PackingSlipDetails;
import ca.bc.gov.educ.grad.report.model.packingslip.PackingSlipService;
import ca.bc.gov.educ.grad.report.model.reports.PackingSlipReport;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.reports.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.model.reports.DestinationType.PSI;

@Service
public class PackingSlipServiceImpl implements PackingSlipService {

    private static final String CLASSNAME = PackingSlipServiceImpl.class.getName();
    private static transient final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final long serialVersionUID = 5L;

    @Autowired
    ReportService reportService;
    @Autowired
    GradDataConvertionBean gradDataConvertionBean;

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
     * @return A ReportDocument that can be bundled with transcripts.
     * @throws DomainServiceException Could not create the packing slip report.
     */
    public ReportDocument createPackingSlipReport(
            final Long orderNumber,
            final Date ordered,
            final String orderedBy,
            final int quantity) throws DomainServiceException {
        final String _m = "createStudentPackingSlipReport(Long, String, Date, OrderType, int)";
        LOG.entering(CLASSNAME, _m);

        PostalDeliveryInfo deliveryInfo = getPostalDeliveryInfo();
        OrderType orderType = getOrderType();

        final PackingSlipDetails details = createPackingSlipDetails(deliveryInfo, orderNumber, ordered, orderedBy, quantity);
        final ReportDocument packingSlipReport = createPackingSlipReport(details, orderType);

        LOG.exiting(CLASSNAME, _m);
        return packingSlipReport;
    }

    /**
     * Creates and returns a packing slip report for a PSI. The report document
     * returned is typically a PDF that includes a print-ready packing slip page
     * suitable for bundling with any number of order items (as tallied by
     * size). This creates the appropriate order type based on the catalogue
     * item. The ordered date is set to today.
     *
     * @param address The recipient name and address.
     * @param orderType The order type that, ultimately, determines the paper
     * type.
     * @param quantity The quantity of items included in the packet.
     * @return A ReportDocument that can be bundled with transcripts.
     * @throws DomainServiceException Could not create the packing slip report.
     */
    private ReportDocument createPSIPackingSlipReport(
            final PostalDeliveryInfo address,
            final OrderType orderType,
            final int quantity)
            throws DomainServiceException {
        final String _m = "createPSIPackingSlipReport(PostalDeliveryInfo, OrderType, int)";
        LOG.entering(CLASSNAME, _m);

        final PackingSlipDetailsImpl details = createPackingSlipDetails(address, new Date(), quantity);

        // Indicate that the packing slip is desinted for a PSI.
        details.setDestinationType(PSI);

        final ReportDocument packingSlipReport = createPackingSlipReport(details, orderType);

        LOG.exiting(CLASSNAME, _m);
        return packingSlipReport;
    }

    /**
     * Call to create a packing slip complete with all address and quantity
     * details filled in. This does not include the order number.
     *
     * @param address The mailing address information for postal delivery.
     * @param quantity The number of order items bundled together with this packing
     * slip.
     * @return Packing slip details suitable for inclusion on a packing slip
     * report.
     * @throws DomainServiceException Could not fill the packing slip details.
     */
    private PackingSlipDetailsImpl createPackingSlipDetails(
            final PostalDeliveryInfo address,
            final Date ordered,
            final int quantity)
            throws DomainServiceException {
        final String _m = "createPackingSlipDetails(PostalDeliveryInfo, Date, int)";
        LOG.entering(CLASSNAME, _m);

        final PackingSlipDetailsImpl details = new PackingSlipDetailsImpl(address);

        try {
            details.setRecipient(address.getName());
            details.setDocumentsShipped(quantity);
            details.setOrderDate(ordered);
        } catch (final Exception ex) {
            throw new DomainServiceException("Could not create or set packing slip details.", ex);
        }

        LOG.exiting(CLASSNAME, _m);
        return details;
    }

    /**
     * Call to create a packing slip complete with all address and quantity
     * details filled in along with order number.
     *
     * @param address The mailing address information for postal delivery.
     * @param quantity The number of order items bundled together with this packing
     * slip.
     * @return Packing slip details suitable for inclusion on a packing slip
     * report.
     * @throws DomainServiceException Could not fill the packing slip details.
     */
    private PackingSlipDetails createPackingSlipDetails(
            final PostalDeliveryInfo address,
            final Long orderNumber,
            final Date ordered,
            final String orderedBy,
            final int quantity) throws DomainServiceException {
        final String _m = "createPackingSlipDetails(PostalDeliveryInfo, Long, String, int)";
        LOG.entering(CLASSNAME, _m);

        final PackingSlipDetailsImpl details = createPackingSlipDetails(address, ordered, quantity);
        details.setOrderNumber(Long.toString(orderNumber));
        details.setOrderedByName(orderedBy);

        LOG.exiting(CLASSNAME, _m);
        return details;
    }

    private ReportDocument createPackingSlipReport(
            final PackingSlipDetails details,
            final OrderType orderType)
            throws DomainServiceException {
        final String _m = "createPackingSlipReport(PackingSlipDetails, OrderType)";
        LOG.entering(CLASSNAME, _m);

        final ReportDocument report;

        try {
            final PackingSlipReport packingSlip = reportService.createPackingSlipReport();

            packingSlip.setPackingSlipDetails(details);
            packingSlip.setOrderType(orderType);

            report = reportService.export(packingSlip);
        } catch (final NullPointerException | IOException e) {
            throw new DomainServiceException("Could not create packing slip report.", e);
        }

        LOG.exiting(CLASSNAME, _m);
        return report;
    }

    private PostalDeliveryInfo getPostalDeliveryInfo() throws DomainServiceException {
        final String _m = "getPostalDeliveryInfo(String)";
        LOG.entering(CLASSNAME, _m);

        final PostalDeliveryInfo postalDeliveryInfo;

        try {
            ReportData reportData = ReportRequestDataThreadLocal.getGenerateReportData();

            if (reportData == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        null,
                        "Report Data not exists for the current report generation");
                LOG.throwing(CLASSNAME, _m, dse);
                throw dse;
            }

            postalDeliveryInfo = gradDataConvertionBean.getPostalDeliveryInfo(reportData);

        } catch (Exception ex) {
            String msg = "Failed to access delivery info data";
            final DataException dex = new DataException(null, null, msg, ex);
            LOG.throwing(CLASSNAME, _m, dex);
            throw dex;
        }

        LOG.exiting(CLASSNAME, _m);
        return postalDeliveryInfo;
    }

    private OrderType getOrderType() throws DomainServiceException {
        final String _m = "getOrderType(String)";
        LOG.entering(CLASSNAME, _m);

        final OrderType orderType;

        try {
            ReportData reportData = ReportRequestDataThreadLocal.getGenerateReportData();

            if (reportData == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        null,
                        "Report Data not exists for the current report generation");
                LOG.throwing(CLASSNAME, _m, dse);
                throw dse;
            }

            orderType = gradDataConvertionBean.getPackingSlipOrderType(reportData);

        } catch (Exception ex) {
            String msg = "Failed to access order type data";
            final DataException dex = new DataException(null, null, msg, ex);
            LOG.throwing(CLASSNAME, _m, dex);
            throw dex;
        }

        LOG.exiting(CLASSNAME, _m);
        return orderType;
    }
}
