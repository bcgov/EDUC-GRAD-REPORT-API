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
package ca.bc.gov.educ.isd.common;

/**
 * SortObject is used for sorting in queries.
 */
public interface SortObject {

	/**
	 * Name of the field to sort on.
	 *
	 * @return field name
	 */
	public String getFieldName();

	/**
	 * Indicates if sort order ascending
	 *
	 * @return true for ascending, false for descending
	 */
	public Boolean isAscending();
}
