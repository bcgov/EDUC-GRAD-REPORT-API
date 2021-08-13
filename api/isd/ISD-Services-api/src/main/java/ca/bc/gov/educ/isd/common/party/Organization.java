/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: Organization.java 6430 2017-03-02 23:13:00Z DAJ#$
 *  Date of Last Commit: $Date:: 2017-03-02 15:13:00 -0800 (Thu, 02 Mar 2017)  $
 *  Revision Number:     $Rev:: 6430                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.common.party;

/**
 * An organization is legal entity that is not a person, such as a corporation,
 * Ministry, or co-operative.
 *
 * @author chris.prince
 * @version 1.0
 * @updated 10-Nov-2015 4:44:15 PM
 */
public interface Organization extends Party {

    /**
     *
     * @return
     */
    String getName();
}
