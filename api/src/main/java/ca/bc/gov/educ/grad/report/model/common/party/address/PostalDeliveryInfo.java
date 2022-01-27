package ca.bc.gov.educ.grad.report.model.common.party.address;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PostalDeliveryInfo extends PostalAddress {

    String getName();

    void setName(String name);

    void setStreetLine1(String streetLine1);

    void setStreetLine2(String streetLine2);

    void setStreetLine3(String streetLine3);

    void setCity(String city);

    void setRegion(String region);

    void setCountryCode(String countryCode);

    void setPostalCode(String code);
}

