package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.common.party.address.PostalAddress;
import ca.bc.gov.educ.grad.report.model.common.party.address.PostalDeliveryInfo;
import ca.bc.gov.educ.grad.report.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.report.model.packingslip.PackingSlipDetails;
import ca.bc.gov.educ.grad.report.model.reports.DestinationType;
import ca.bc.gov.educ.grad.report.model.reports.PaperType;

import java.util.Date;

/**
 * Implementation of PackingSlipDetails using to create BCMP packing slip
 *
 * @author CGI Information Management Consultants Inc.
 */
public class PackingSlipDetailsImpl extends AbstractDomainEntity implements PackingSlipDetails {

    private static final long serialVersionUID = 1L;

    private final static String DEFAULT_ORDER_TYPE = TRANSCRIPTS;

    /**
     * The document number, one per order. For the first release of STs this
     * value should always be "000001".
     */
    private final static String SEQUENCE_NUMBER = "000001";

    private Long id;
    private String recipient;
    private String attentionTo = "";
    private String orderedByName;
    private Integer documentsShipped;
    private Integer totalShipped;
    private String orderNumber;
    private Date orderDate;
    private DestinationType destinationType;
    private PostalAddress address;
    private PaperType paperType;

    public PackingSlipDetailsImpl() {}

    public PackingSlipDetailsImpl(PostalDeliveryInfo info) {
        this.address = info;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getRecipient() {
        return recipient;
    }

    @Override
    public String getAttentionTo() {
        return attentionTo;
    }

    public void setAttentionTo(String attentionTo) {
        this.attentionTo = attentionTo;
    }

    @Override
    public PostalAddress getAddress() {
        return address;
    }

    @Override
    public String getOrderedByName() {
        return orderedByName;
    }

    @Override
    public Integer getDocumentsShipped() {
        return documentsShipped;
    }

    @Override
    public String getOrderNumber() {
        return orderNumber;
    }

    @Override
    public Date getOrderDate() {
        return this.orderDate;
    }

    @Override
    public DestinationType getDestinationType() {
        return destinationType;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType paperType) {
        this.paperType = paperType;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setOrderedByName(String orderedByName) {
        this.orderedByName = orderedByName;
    }

    public void setDocumentsShipped(Integer documentsShipped) {
        this.documentsShipped = documentsShipped;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setDestinationType(DestinationType destinationType) {
        this.destinationType = destinationType;
    }

    public void setOrderDate(final Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setAddress(PostalAddress address) {
        this.address = address;
    }

    @Override
    public Integer getTotalShipped() {
        return totalShipped;
    }

    public void setTotalShipped(Integer totalShipped) {
        this.totalShipped = totalShipped;
    }
}

