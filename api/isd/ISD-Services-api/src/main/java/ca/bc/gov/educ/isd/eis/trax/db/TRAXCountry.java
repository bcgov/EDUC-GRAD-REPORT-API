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
 * Interface for classes that have getters for country code in TRAX.
 *
 */
public interface TRAXCountry {

	/**
	 * Returns the TRAX internal country code.
	 *
	 * @return
	 */
	String getTRAXCountryCode();

	/**
	 * Returns the ISO alpha-2 country code.
	 *
	 * @return
	 */
	String getCountryCode();

}
