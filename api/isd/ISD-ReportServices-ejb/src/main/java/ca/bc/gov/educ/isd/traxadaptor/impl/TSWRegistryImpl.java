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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.traxadaptor.impl;

import ca.bc.gov.educ.isd.eis.trax.db.TSWRegistry;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * A TRAX data object containing data from the TRAX database for the given PSI
 * Code. The data pertains to PSI Registry.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class TSWRegistryImpl implements TSWRegistry, Serializable {

    // ------------------ CONSTANT(S) AND FINAL(S)
    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = TSWRegistryImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    // ------------------ VARIABLE(S)
    private final String psiCode;
    private String psiName;
    private String psiCslCode;
    private String psiAttnName;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String provCode;
	private String traxCountryCode;
	private String isoCountryCode;
    private Character openFlag;
    private Character globalSignOff;
    private String psiPostal;
    private String fax;
    private String phone1;
    private String transmissionMode;
    private String psisCode;
    private String psiUrl;
    private String psiGrouping;

    // ------------------ CONSTRUCTOR(S)
    public TSWRegistryImpl(String psiCode) {
        this.psiCode = psiCode;
    }

    /**
     * Constructor that initializes all possible attributes of the class.
     *
     * @param psiCode Code that represents the registry
     * @param psiName Name of the PSI Registry
     * @param psiCslCode CSL Code of the PSI Registry
     * @param psiAttnName ATTN Name of the PSI Registry
     * @param address1 First option address of the PSI Registry
     * @param address2 Second option address of the PSI Registry
     * @param address3 Third option address of the PSI Registry
     * @param city City of the PSI Registry
     * @param provCode Provincial code of the PSI Registry
     * @param cntryCode Country code of the PSI Registry
     * @param openFlag Flag of the PSI Registry
     * @param globalSignOff Global Sign Off of the PSI Registry
     * @param psiPostal Postal code of the PSI Registry
     * @param fax Fax number of the PSI Registry
     * @param phone1 Phone number of the PSI Registry
     * @param transmissionMode Transmission Mode of the PSI Registry
     * @param psisCode PSIS Code of the PSI Registry
     * @param psiUrl URL of the PSI Registry
     * @param psiGrouping Grouping of the PSI Registry
     */
    public TSWRegistryImpl(
            final String psiCode,
            final String psiName,
            final String psiCslCode,
            final String psiAttnName,
            final String address1,
            final String address2,
            final String address3,
            final String city,
            final String provCode,
            final String cntryCode,
            final Character openFlag,
            final Character globalSignOff,
            final String psiPostal,
            final String fax,
            final String phone1,
            final String transmissionMode,
            final String psisCode,
            final String psiUrl,
            final String psiGrouping) {
        this.psiCode = psiCode;
        this.psiName = psiName;
        this.psiCslCode = psiCslCode;
        this.psiAttnName = psiAttnName;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.provCode = provCode;
		setTRAXCountryCode(cntryCode);
        this.openFlag = openFlag;
        this.globalSignOff = globalSignOff;
        this.psiPostal = psiPostal;
        this.fax = fax;
        this.phone1 = phone1;
        setTransmissionMode(transmissionMode);
        this.psisCode = psisCode;
        this.psiUrl = psiUrl;
        this.psiGrouping = psiGrouping;
    }

    /**
     * @param map * @deprecated
     */
    public TSWRegistryImpl(Map<String, String> map) {
        this.psiCode = map.get(PSIRegistryAttributesConstants.psiCode.name());
        this.psiName = map.get(PSIRegistryAttributesConstants.psiName.name());
        this.psiCslCode = map.get(PSIRegistryAttributesConstants.psiCslCode.name());
        this.psiAttnName = map.get(PSIRegistryAttributesConstants.psiAttnName.name());
        this.address1 = map.get(PSIRegistryAttributesConstants.address1.name());
        this.address2 = map.get(PSIRegistryAttributesConstants.address2.name());
        this.address3 = map.get(PSIRegistryAttributesConstants.address3.name());
        this.city = map.get(PSIRegistryAttributesConstants.city.name());
        this.provCode = map.get(PSIRegistryAttributesConstants.provCode.name());
        setTRAXCountryCode(map.get(PSIRegistryAttributesConstants.cntryCode.name()));
        if (map.get(PSIRegistryAttributesConstants.openFlag.name()) != null) {
            this.openFlag = map.get(PSIRegistryAttributesConstants.openFlag.name()).toCharArray()[0];
        }
        if (map.get(PSIRegistryAttributesConstants.globalSignOff.name()) != null) {
            this.globalSignOff = map.get(PSIRegistryAttributesConstants.globalSignOff.name()).toCharArray()[0];
        }
        this.psiPostal = map.get(PSIRegistryAttributesConstants.psiPostal.name());
        this.fax = map.get(PSIRegistryAttributesConstants.fax.name());
        this.phone1 = map.get(PSIRegistryAttributesConstants.phone1.name());
        setTransmissionMode(map.get(PSIRegistryAttributesConstants.transmissionMode.name()));
        this.psisCode = map.get(PSIRegistryAttributesConstants.psisCode.name());
        this.psiUrl = map.get(PSIRegistryAttributesConstants.psiUrl.name());
        this.psiGrouping = map.get(PSIRegistryAttributesConstants.psiGrouping.name());
    }

    /**
     * @param tswRegistry
     */
    public TSWRegistryImpl(final TSWRegistry tswRegistry) {
        this.psiCode = tswRegistry.getPsiCode();
        this.psiName = tswRegistry.getPsiName();
        this.psiCslCode = tswRegistry.getPsiCslCode();
        this.psiAttnName = tswRegistry.getPsiAttnName();
        this.address1 = tswRegistry.getAddress1();
        this.address2 = tswRegistry.getAddress2();
        this.address3 = tswRegistry.getAddress3();
        this.city = tswRegistry.getCity();
        this.provCode = tswRegistry.getProvCode();
        setTRAXCountryCode(tswRegistry.getCountryCode());
        if (tswRegistry.getOpenFlag() != null) {
            this.openFlag = tswRegistry.getOpenFlag();
        }
        if (tswRegistry.getGlobalSignOff() != null) {
            this.globalSignOff = tswRegistry.getGlobalSignOff();
        }
        this.psiPostal = tswRegistry.getPsiPostal();
        this.fax = tswRegistry.getFax();
        this.phone1 = tswRegistry.getPhone1();
        setTransmissionMode(tswRegistry.getTransmissionMode());
        this.psisCode = tswRegistry.getPsisCode();
        this.psiUrl = tswRegistry.getPsiUrl();
        this.psiGrouping = tswRegistry.getPsiGrouping();
    }

    // ------------------ GETTER(S) AND SETTER(S)
    @Override
    public String getPsiCode() {
        return psiCode;
    }

    @Override
    public String getPsiName() {
        return psiName;
    }

    @Override
    public void setPsiName(String psiName) {
        this.psiName = psiName;
    }

    @Override
    public String getPsiCslCode() {
        return psiCslCode;
    }

    @Override
    public void setPsiCslCode(String psiCslCode) {
        this.psiCslCode = psiCslCode;
    }

    @Override
    public String getPsiAttnName() {
        return psiAttnName;
    }

    @Override
    public void setPsiAttnName(String psiAttnName) {
        this.psiAttnName = psiAttnName;
    }

    @Override
    public String getAddress1() {
        return address1;
    }

    @Override
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Override
    public String getAddress2() {
        return address2;
    }

    @Override
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Override
    public String getAddress3() {
        return address3;
    }

    @Override
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getProvCode() {
        return provCode;
    }

    @Override
    public void setProvCode(String provCode) {
        this.provCode = provCode;
    }

	@Override
	public String getTRAXCountryCode() {
		return traxCountryCode;
	}

	@Override
	public void setTRAXCountryCode(final String traxCountryCode) {
		this.traxCountryCode = traxCountryCode;
		this.isoCountryCode = TRAXCountryConverter.getInstance().traxToISO(traxCountryCode);
	}

	/**
	 * Gets ISO Country Code.
	 *
	 * @return
	 */
	@Override
	public String getCountryCode() {
		if (isoCountryCode == null) {
			this.isoCountryCode = TRAXCountryConverter.getInstance().traxToISO(traxCountryCode);
		}
		return isoCountryCode;
	}

	/**
	 * Sets the ISO country code.
	 *
	 */
	@Override
	public void setCountryCode(final String isoCountryCode) {
		this.isoCountryCode = isoCountryCode;
		this.traxCountryCode = TRAXCountryConverter.getInstance().isoToTRAX(isoCountryCode);
	}

    @Override
    public Character getOpenFlag() {
        return openFlag;
    }

    @Override
    public void setOpenFlag(Character openFlag) {
        this.openFlag = openFlag;
    }

    @Override
    public Character getGlobalSignOff() {
        return globalSignOff;
    }

    @Override
    public void setGlobalSignOff(Character globalSignOff) {
        this.globalSignOff = globalSignOff;
    }

    @Override
    public String getPsiPostal() {
        return psiPostal;
    }

    @Override
    public void setPsiPostal(String psiPostal) {
        this.psiPostal = psiPostal;
    }

    @Override
    public String getFax() {
        return fax;
    }

    @Override
    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String getPhone1() {
        return phone1;
    }

    @Override
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    @Override
    public String getTransmissionMode() {
        return transmissionMode;
    }

    @Override
    public void setTransmissionMode(String transmissionMode) {
        this.transmissionMode = transmissionMode != null ? transmissionMode.trim() : "";
    }

    @Override
    public String getPsisCode() {
        return psisCode;
    }

    @Override
    public void setPsisCode(String psisCode) {
        this.psisCode = psisCode;
    }

    @Override
    public String getPsiUrl() {
        return psiUrl;
    }

    @Override
    public void setPsiUrl(String psiUrl) {
        this.psiUrl = psiUrl;
    }

    @Override
    public String getPsiGrouping() {
        return psiGrouping;
    }

    @Override
    public void setPsiGrouping(String psiGrouping) {
        this.psiGrouping = psiGrouping;
    }

    // ------------------ METHOD(S)
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof TSWRegistryImpl)) {
            return false;
        }
        final TSWRegistryImpl other = (TSWRegistryImpl) object;
        return this.psiCode.equals(other.psiCode);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.psiCode);
        return hash;
    }
}
