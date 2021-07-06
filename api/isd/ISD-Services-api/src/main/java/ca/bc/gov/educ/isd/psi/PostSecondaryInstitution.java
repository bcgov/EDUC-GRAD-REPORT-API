/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: PostSecondaryInstitution.java 6471 2017-03-08 2#$
 *  Date of Last Commit: $Date:: 2017-03-08 14:38:11 -0800 (Wed, 08 Mar 2017)  $
 *  Revision Number:     $Rev:: 6471                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.psi;

import ca.bc.gov.educ.isd.common.party.Organization;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PostSecondaryInstitution extends Organization {

    /**
     * Gets the PSI Code (primary key).
     *
     * @return the PSI code.
     */
    String getPsiCode();

    /**
     * Gets the PSI Name
     *
     * @return the PSI name
     */
    @Override
    String getName();

    /**
     * Sets the PSI Name
     *
     * @param psiName PSI name that replaces the old one (if any)
     */
    void setName(String psiName);

    /**
     * Gets the CLS Code.
     *
     * @return the CLS code.
     */
    String getPsiCslCode();

    /**
     * Sets the CLS Code.
     *
     * @param psiCslCode CLS code that replaces the old one (if any).
     */
    void setPsiCslCode(String psiCslCode);

    /**
     * Gets PSI Attn Name.
     *
     * @return the PSI Attn Name
     */
    String getPsiAttnName();

    /**
     * Sets the Attn Name.
     *
     * @param psiAttnName Attn Name that replaces the old one (if any).
     */
    void setPsiAttnName(String psiAttnName);

    /**
     * Gets the Address 1.
     *
     * @return Gets the first address option.
     */
    String getAddress1();

    /**
     * Sets the Address 1.
     *
     * @param address1 First Address option that replaces the old one (if any)
     */
    void setAddress1(String address1);

    /**
     * Gets the second Address option.
     *
     * @return second address option.
     */
    String getAddress2();

    /**
     * Sets the second address option.
     *
     * @param address2 Second address option that replaces the old one (if any)
     */
    void setAddress2(String address2);

    /**
     * Gets the third address option.
     *
     * @return third address option.
     */
    String getAddress3();

    /**
     * Sets the third address option.
     *
     * @param address3 Third address option that replaces the old one (if any)
     */
    void setAddress3(String address3);

    /**
     * Gets the City of the PSI Registry.
     *
     * @return the city of the PSI Registry.
     */
    String getCity();

    /**
     * Sets the city of the PSI Registry.
     *
     * @param city city that replaces the old one (if any)
     */
    void setCity(String city);

    /**
     * Gets the Provincial Code.
     *
     * @return the provincial code.
     */
    String getProvCode();

    /**
     * Sets the provincial code.
     *
     * @param provCode Provincial code that replaces the old one (if any)
     */
    void setProvCode(String provCode);

    /**
     * Gets the Country code.
     *
     * @return the country code.
     */
    String getCntryCode();

    /**
     * Sets the country code.
     *
     * @param cntryCode Country code that replaces the old one (if any)
     */
    void setCntryCode(String cntryCode);

    /**
     * Gets the Open Flag.
     *
     * @return the open flag.
     */
    Character getOpenFlag();

    /**
     * Sets the open flag.
     *
     * @param openFlag open flag that replaces the old one (if any)
     */
    void setOpenFlag(Character openFlag);

    /**
     * Gets the Global Sign Off.
     *
     * @return the global sign off.
     */
    Character getGlobalSignOff();

    /**
     * Sets the Global Sign Off.
     *
     * @param globalSignOff Global Sign Off that replaces the old one (if any).
     */
    void setGlobalSignOff(Character globalSignOff);

    /**
     * Gets the postal code.
     *
     * @return the postal code.
     */
    String getPsiPostal();

    /**
     * Sets the postal code.
     *
     * @param psiPostal postal code that replaces the old one (if any)
     */
    void setPsiPostal(String psiPostal);

    /**
     * Gets the Fax number.
     *
     * @return the fax number.
     */
    String getFax();

    /**
     * Sets the fax number.
     *
     * @param fax the fax that replaces the old one (if any)
     */
    void setFax(String fax);

    /**
     * Gets the Phone number.
     *
     * @return the phone number.
     */
    String getPhone1();

    /**
     * Sets the phone number.
     *
     * @param phone1 phone number that replaces the old one (if any)
     */
    void setPhone1(String phone1);

    /**
     * FIXME: Use TransmissionMode enum, not String.
     *
     * Gets the transmission mode.
     *
     * @return the transmission mode.
     */
    String getTransmissionMode();

    /**
     * Sets the transmission mode.
     *
     * @param transmissionMode the transmission mode that replaces the old one
     * (if any)
     */
    void setTransmissionMode(String transmissionMode);

    /**
     * Gets the PSIS code.
     *
     * @return the PSIS code.
     */
    String getPsisCode();

    /**
     * Sets the PSIS code.
     *
     * @param psisCode the PSIS code that replaces the old one (if any).
     */
    void setPsisCode(String psisCode);

    /**
     * Gets the URL.
     *
     * @return the URL.
     */
    String getPsiUrl();

    /**
     * Sets the URL.
     *
     * @param psiUrl the URL that replaces the old one (if any).
     */
    void setPsiUrl(String psiUrl);

    /**
     * Gets the grouping code.
     *
     * @return the grouping code.
     */
    String getPsiGrouping();

    /**
     * Sets the grouping code.
     *
     * @param psiGrouping the grouping that replaces the old one (if any)
     */
    void setPsiGrouping(String psiGrouping);

}
