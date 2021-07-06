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
 *  File:                PSIRegistry.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.psi;

import ca.bc.gov.educ.isd.common.DomainEntity;
import java.util.Date;

/**
 * An interface for the PSI Registry EJB that it is tied to the ISD's PSI
 * Registry table in ISD database. It exposes the services that the EJB provides
 * in a distributed environments. Specifically, the services of retrieving,
 * updating, and inserting.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PSIRegistry extends DomainEntity {

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
	String getPsiName();

	/**
	 * Gets the CLS Code.
	 *
	 * @return the CLS code.
	 */
	String getPsiCslCode();

	/**
	 * Gets PSI Attn Name.
	 *
	 * @return the PSI Attn Name
	 */
	String getPsiAttnName();

	/**
	 * Gets the Address 1.
	 *
	 * @return Gets the first address option.
	 */
	String getAddress1();

	/**
	 * Gets the second Address option.
	 *
	 * @return second address option.
	 */
	String getAddress2();

	/**
	 * Gets the third address option.
	 *
	 * @return third address option.
	 */
	String getAddress3();

	/**
	 * Gets the City of the PSI Registry.
	 *
	 * @return the city of the PSI Registry.
	 */
	String getCity();

	/**
	 * Gets the Provincial Code.
	 *
	 * @return the provincial code.
	 */
	String getProvCode();

	/**
	 * Gets the ISO Country code.
	 *
	 * @return the country code.
	 */
	String getCntryCode();

	/**
	 * Gets the Open Flag.
	 *
	 * @return the open flag.
	 */
	String getOpenFlag();

	/**
	 * Gets the Global Sign Off.
	 *
	 * @return the global sign off.
	 */
	String getGlobalSignOff();

	/**
	 * Gets the postal code.
	 *
	 * @return the postal code.
	 */
	String getPsiPostal();

	/**
	 * Gets the Fax number.
	 *
	 * @return the fax number.
	 */
	String getFax();

	/**
	 * Gets the Phone number.
	 *
	 * @return the phone number.
	 */
	String getPhone1();

	/**
	 * Gets the transmission mode.
	 *
	 * @return the transmission mode.
	 */
	TransmissionMode getTransmissionMode();

	/**
	 * Gets the PSIS code.
	 *
	 * @return the PSIS code.
	 */
	String getPsisCode();

	/**
	 * Gets the URL.
	 *
	 * @return the URL.
	 */
	String getPsiUrl();

	/**
	 * Gets the grouping code.
	 *
	 * @return the grouping code.
	 */
	String getPsiGrouping();

	/**
	 * Gets the synchronization date.
	 *
	 * @return the grouping code.
	 */
	Date getSyncDate();
}
