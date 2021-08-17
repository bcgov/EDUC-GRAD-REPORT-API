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
package ca.bc.gov.educ.isd.eis.trax.db;

/**
 * Interface for classes that have getters and setters for country code in TRAX.
 * Typically the implementation should handle the conversion to and from
 * TRAX/ISO internally, so that only one of the setters should be called in a
 * given block of code.
  *
 */
public interface TRAXCountryAdmin extends TRAXCountry {

	/**
	 * Set the TRAX internal country code.
	 *
	 * @param traxCountryCode
	 */
	void setTRAXCountryCode(String traxCountryCode);

	/**
	 * Set the ISO alpha-2 country code.
	 *
	 * Note that as an implementation decision,	it was decided to NOT use
	 * Locales, as they are dependent on the Java version, which won't be
	 * updated in parallel with the TRAX country code table.
	 *
	 * @param isoCountryCode
	 */
	void setCountryCode(String isoCountryCode);
}
