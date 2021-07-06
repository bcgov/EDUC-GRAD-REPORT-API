/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                PSIRegistryAdmin.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.psi;

import java.util.Date;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PSIRegistryAdmin extends PSIRegistry {

    /**
     * Sets the PSI Id (primary key).
     *
     * @param Id
     */
    void setId(final Long Id);

    /**
     * Sets the PSI Code (unique index).
     *
     * @param psiCode
     */
    void setPsiCode(final String psiCode);

    /**
     * Sets the PSI Name
     *
     * @param psiName
     */
    void setPsiName(final String psiName);

    /**
     * Sets the CLS Code.
     *
     * @param psiCslCode
     */
    void setPsiCslCode(final String psiCslCode);

    /**
     * Sets PSI Attn Name.
     *
     * @param psiAttnName
     */
    void setPsiAttnName(final String psiAttnName);

    /**
     * Sets the Address 1.
     *
     * @param address1
     */
    void setAddress1(final String address1);

    /**
     * Sets the second Address option.
     *
     * @param address2
     */
    void setAddress2(final String address2);

    /**
     * Sets the third address option.
     *
     * @param address3
     */
    void setAddress3(final String address3);

    /**
     * Sets the City of the PSI Registry.
     *
     * @param city
     */
    void setCity(final String city);

    /**
     * Sets the Provincial Code.
     *
     * @param provCode
     */
    void setProvCode(final String provCode);

    /**
     * Sets the Country code.
     *
     * @param cntryCode
     */
    void setCntryCode(final String cntryCode);

    /**
     * Sets the Open Flag.
     *
     * @param openFlag
     */
    void setOpenFlag(final String openFlag);

    /**
     * Sets the Global Sign Off.
     *
     * @param globalSignOff
     */
    void setGlobalSignOff(final String globalSignOff);

    /**
     * Sets the postal code.
     *
     * @param psiPostal
     */
    void setPsiPostal(final String psiPostal);

    /**
     * Sets the Fax number.
     *
     * @param Fax
     */
    void setFax(final String Fax);

    /**
     * Sets the Phone number.
     *
     * @param Phone1
     */
    void setPhone1(final String Phone1);

    /**
     * Sets the transmission mode.
     *
     * @param TransmissionMode
     */
    void setTransmissionMode(final TransmissionMode TransmissionMode);

    /**
     * Sets the PSIS code.
     *
     * @param psiSCode
     */
    void setPsisCode(final String psiSCode);

    /**
     * Sets the URL.
     *
     * @param psiURL
     */
    void setPsiUrl(final String psiURL);

    /**
     * Sets the grouping code.
     *
     * @param psiGruoping
     */
    void setPsiGrouping(final String psiGruoping);

    /**
     * Sets the synchronization date.
     *
     * @param syncDate
     */
    void setSyncDate(Date syncDate);
}
