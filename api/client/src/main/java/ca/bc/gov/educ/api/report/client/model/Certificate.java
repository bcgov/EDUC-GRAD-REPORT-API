package ca.bc.gov.educ.api.report.client.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class Certificate {
    private Date issued;
    private OrderType orderType;
    private boolean isOrigin;

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getIssued() {
        return issued;
    }

    public void setIssued(Date value) {
        this.issued = value;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType value) {
        this.orderType = value;
    }

    public boolean getIsOrigin() {
        return isOrigin;
    }

    public void setIsOrigin(boolean origin) {
        isOrigin = origin;
    }
}
