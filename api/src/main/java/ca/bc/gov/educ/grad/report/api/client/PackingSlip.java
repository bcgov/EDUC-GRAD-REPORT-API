package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

public class PackingSlip {

    String recipient;
    School school;
    PackingSlipOrderType orderType;
    Date orderDate;
    String orderedBy;
    Long orderNumber;
    int quantity;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @JsonDeserialize(as = PackingSlipOrderType.class)
    public PackingSlipOrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(PackingSlipOrderType orderType) {
        this.orderType = orderType;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}