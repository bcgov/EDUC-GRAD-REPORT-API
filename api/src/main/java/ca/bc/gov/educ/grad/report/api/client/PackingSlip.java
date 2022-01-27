package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PackingSlip {

    String recipient;
    Address address;
    String orderType;
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

    public ca.bc.gov.educ.grad.report.api.client.Address getAddress() {
        return address;
    }

    public void setAddress(ca.bc.gov.educ.grad.report.api.client.Address address) {
        this.address = address;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
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
