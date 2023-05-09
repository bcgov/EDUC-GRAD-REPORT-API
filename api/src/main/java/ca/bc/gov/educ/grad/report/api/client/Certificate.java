package ca.bc.gov.educ.grad.report.api.client;

import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


public class Certificate implements Serializable {
    private Date issued;
    private String certStyle;
    private OrderType orderType;
    private boolean isOrigin;

    @JsonFormat(pattern= ReportApiConstants.DEFAULT_DATE_FORMAT)
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

    public String getCertStyle() {
        return certStyle;
    }

    public void setCertStyle(String certStyle) {
        this.certStyle = certStyle;
    }
}
