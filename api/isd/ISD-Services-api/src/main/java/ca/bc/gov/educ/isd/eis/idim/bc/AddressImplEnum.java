/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.bc.gov.educ.isd.eis.idim.bc;

import static ca.bc.gov.educ.isd.eis.idim.bc.AddressEnum.*;
import static ca.bc.gov.educ.isd.eis.idim.bc.AddressTypeEnum.*;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum AddressImplEnum {

    CONTACT_ADDRESS_LINE_ONE(PREFIX_ACCOUNT_CONTACT.value() + "_" + ADDRESS_LINE_ONE),
    CONTACT_ADDRESS_LINE_TWO(PREFIX_ACCOUNT_CONTACT.value() + "_" + ADDRESS_LINE_TWO.value()),
    CONTACT_CITY(PREFIX_ACCOUNT_CONTACT.value() + "_" + CITY.value()),
    CONTACT_POSTAL_CODE(PREFIX_ACCOUNT_CONTACT.value() + "_" + POSTAL_CODE.value()),
    CONTACT_PROVINCE(PREFIX_ACCOUNT_CONTACT.value() + "_" + PROVINCE.value()),
    CONTACT_COUNTRY(PREFIX_ACCOUNT_CONTACT.value() + "_" + COUNTRY.value()),
    CONTACT_UNSTRUCTURED_ADDRESS(PREFIX_ACCOUNT_CONTACT.value() + "_" + UNSTRUCTURED_ADDRESS.value()),
    RESIDENTIAL_ADDRESS_LINE_ONE(PREFIX_INDIVIDUAL_RESIDENTIAL.value() + "_" + ADDRESS_LINE_ONE.value()),
    RESIDENTIAL_ADDRESS_LINE_TWO(PREFIX_INDIVIDUAL_RESIDENTIAL.value() + "_" + ADDRESS_LINE_TWO.value()),
    RESIDENTIAL_CITY(PREFIX_INDIVIDUAL_RESIDENTIAL.value() + "_" + CITY.value()),
    RESIDENTIAL_POSTAL_CODE(PREFIX_INDIVIDUAL_RESIDENTIAL.value() + "_" + POSTAL_CODE.value()),
    RESIDENTIAL_PROVINCE(PREFIX_INDIVIDUAL_RESIDENTIAL.value() + "_" + PROVINCE.value()),
    RESIDENTIAL_COUNTRY(PREFIX_INDIVIDUAL_RESIDENTIAL.value() + "_" + COUNTRY.value()),
    RESIDENTIAL_UNSTRUCTURED_ADDRESS(PREFIX_INDIVIDUAL_RESIDENTIAL.value() + "_" + UNSTRUCTURED_ADDRESS.value()),
    MAILING_ADDRESS_LINE_ONE(PREFIX_INDIVIDUAL_MAILING.value() + "_" + ADDRESS_LINE_ONE.value()),
    MAILING_ADDRESS_LINE_TWO(PREFIX_INDIVIDUAL_MAILING.value() + "_" + ADDRESS_LINE_TWO.value()),
    MAILING_CITY(PREFIX_INDIVIDUAL_MAILING.value() + "_" + CITY.value()),
    MAILING_POSTAL_CODE(PREFIX_INDIVIDUAL_MAILING.value() + "_" + POSTAL_CODE.value()),
    MAILING_PROVINCE(PREFIX_INDIVIDUAL_MAILING.value() + "_" + PROVINCE.value()),
    MAILING_COUNTRY(PREFIX_INDIVIDUAL_MAILING.value() + "_" + COUNTRY.value()),
    MAILING_UNSTRUCTURED_ADDRESS(PREFIX_INDIVIDUAL_MAILING.value() + "_" + UNSTRUCTURED_ADDRESS.value()),
    BUSINESS_ADDRESS_LINE_ONE(PREFIX_BUSINESS.value() + "_" + ADDRESS_LINE_ONE.value()),
    BUSINESS_ADDRESS_LINE_TWO(PREFIX_BUSINESS.value() + "_" + ADDRESS_LINE_TWO.value()),
    BUSINESS_CITY(PREFIX_BUSINESS.value() + "_" + CITY.value()),
    BUSINESS_POSTAL_CODE(PREFIX_BUSINESS.value() + "_" + POSTAL_CODE.value()),
    BUSINESS_PROVINCE(PREFIX_BUSINESS.value() + "_" + PROVINCE.value()),
    BUSINESS_COUNTRY(PREFIX_BUSINESS.value() + "_" + COUNTRY.value()),
    BUSINESS_UNSTRUCTURED_ADDRESS(PREFIX_BUSINESS.value() + "_" + UNSTRUCTURED_ADDRESS.value());

    private final String value;

    AddressImplEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AddressImplEnum fromValue(String v) {
        for (AddressImplEnum c : AddressImplEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);

    }

}
