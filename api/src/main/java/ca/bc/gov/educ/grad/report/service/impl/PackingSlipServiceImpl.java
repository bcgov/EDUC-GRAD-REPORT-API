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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants.LOG_TRACE_ENTERING;
import static ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants.LOG_TRACE_EXITING;

@Slf4j
@Service
public class PackingSlipServiceImpl implements PackingSlipService {

    private static final String CLASSNAME = PackingSlipServiceImpl.class.getName();
    private static transient final Logger LOG = Logger.getLogger(CLASSNAME);
    private static final String REPORT_DATA_MISSING = "REPORT_DATA_MISSING";

    private static final long serialVersionUID = 5L;

    @Autowired
    ReportService reportService;
    @Autowired
    GradDataConvertionBean gradDataConvertionBean;

    /**
     * Creates and returns a packing slip report for a PSI. The report document
     * returned is typically a PDF that includes a print-ready packing slip page
     * suitable for bundling with any number of order items (as tallied by
     * size). This creates the appropriate order type based on the catalogue
     * item. The ordered date is set to today.
     *
     * @param orderNumber Order number to include on the packing slip.
     * @param ordered Date the order was made.
     * @param quantity The quantity of items included in the packet.
     * @param total The quantity of items included in the packet.
     * @return A ReportDocument that can be bundled with transcripts.
     * @throws DomainServiceException Could not create the packing slip report.
     */
    public ReportDocument createPackingSlipReport(
            final Long orderNumber,
            final Date ordered,
            final String orderedBy,
            final int quantity,
            final int current,
            final int total) throws DomainServiceException {
        final String methodName = "createStudentPackingSlipReport(Long, String, Date, OrderType, int)";
        log.trace(LOG_TRACE_ENTERING, methodName);

        PostalDeliveryInfo deliveryInfo = getPostalDeliveryInfo();
        OrderType orderType = getOrderType();

        final PackingSlipDetails details = createPackingSlipDetails(deliveryInfo, orderType, orderNumber, ordered, orderedBy, quantity, current, total);
        final ReportDocument packingSlipReport = createPackingSlipReport(details, orderType);

        log.trace(LOG_TRACE_EXITING, methodName);
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
            final int quantity,
            final int current,
            final int total)
            throws DomainServiceException {
        final String methodName = "createPackingSlipDetails(PostalDeliveryInfo, Date, int)";
        log.trace(LOG_TRACE_ENTERING, methodName);

        final PackingSlipDetailsImpl details = new PackingSlipDetailsImpl(address);

        try {
            details.setRecipient(address.getName());
            details.setAttentionTo(address.getAttentionTo());
            details.setDocumentsShipped(quantity);
            details.setCurrentSlip(current);
            details.setTotalSlips(total);
            details.setOrderDate(ordered);
        } catch (final Exception ex) {
            throw new DomainServiceException("Could not create or set packing slip details.", ex);
        }

        log.trace(LOG_TRACE_EXITING, methodName);
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
            final OrderType orderType,
            final Long orderNumber,
            final Date ordered,
            final String orderedBy,
            final int quantity,
            final int current,
            final int total
    ) throws DomainServiceException {
        final String methodName = "createPackingSlipDetails(PostalDeliveryInfo, Long, String, int)";
        log.trace(LOG_TRACE_ENTERING, methodName);

        final PackingSlipDetailsImpl details = createPackingSlipDetails(address, ordered, quantity, current, total);
        details.setOrderNumber(Long.toString(orderNumber));
        details.setOrderedByName(orderedBy);
        details.setPaperType(orderType.getPaperType());

        log.trace(LOG_TRACE_EXITING, methodName);
        return details;
    }

    private ReportDocument createPackingSlipReport(
            final PackingSlipDetails details,
            final OrderType orderType)
            throws DomainServiceException {
        final String methodName = "createPackingSlipReport(PackingSlipDetails, OrderType)";
        log.trace(LOG_TRACE_ENTERING, methodName);

        final ReportDocument report;

        try {
            final PackingSlipReport packingSlip = reportService.createPackingSlipReport();

            packingSlip.setPackingSlipDetails(details);
            packingSlip.setOrderType(orderType);

            report = reportService.export(packingSlip);
        } catch (final NullPointerException | IOException e) {
            throw new DomainServiceException("Could not create packing slip report.", e);
        }

        log.trace(LOG_TRACE_EXITING, methodName);
        return report;
    }

    private PostalDeliveryInfo getPostalDeliveryInfo() throws DomainServiceException {
        final String methodName = "getPostalDeliveryInfo(String)";
        log.trace(LOG_TRACE_ENTERING, methodName);

        final PostalDeliveryInfo postalDeliveryInfo;

        try {
            ReportData reportData = ReportRequestDataThreadLocal.getReportData();

            if (reportData == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        getClass(),
                        REPORT_DATA_MISSING,
                        "Report Data not exists for the current report generation");
                log.error(dse.getMessage(), dse);
                throw dse;
            }

            postalDeliveryInfo = gradDataConvertionBean.getPostalDeliveryInfo(reportData);

        } catch (Exception ex) {
            String msg = "Failed to access delivery info data";
            final DataException dex = new DataException(null, null, msg, ex);
            log.error(msg, dex);
            throw dex;
        }

        log.trace(LOG_TRACE_EXITING, methodName);
        return postalDeliveryInfo;
    }

    private OrderType getOrderType() throws DomainServiceException {
        final String methodName = "getOrderType(String)";
        log.trace(LOG_TRACE_ENTERING, methodName);

        final OrderType orderType;

        try {
            ReportData reportData = ReportRequestDataThreadLocal.getReportData();

            if (reportData == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        getClass(),
                        REPORT_DATA_MISSING,
                        "Report Data not exists for the current report generation");
                log.error(dse.getMessage(), dse);
                throw dse;
            }

            orderType = gradDataConvertionBean.getPackingSlipOrderType(reportData);

        } catch (Exception ex) {
            String msg = "Failed to access order type data";
            final DataException dex = new DataException(null, null, msg, ex);

            log.error(msg, dex);
            throw dex;
        }

        log.trace(LOG_TRACE_EXITING, methodName);
        return orderType;
    }
}
