/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: Party.java 6430 2017-03-02 23:13:00Z DAJARVIS   $
 *  Date of Last Commit: $Date:: 2017-03-02 15:13:00 -0800 (Thu, 02 Mar 2017)  $
 *  Revision Number:     $Rev:: 6430                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.common.party;

import ca.bc.gov.educ.isd.common.DomainEntity;

import java.util.List;

/**
 * Represents a Party comprised of a list of third-party identifiers.
 *
 * @author CGI Information Management Consultants Inc.
 */
public abstract interface Party extends DomainEntity {

    /**
     * List of identifiers typically populated from external entities. For
     * example, this list could include Care Card numbers for students. In the
     * Party model, each domain entity can have an arbitrarily long list of
     * identifiers that correspond to third-party entities.
     *
     * @return A list of identifiers, possibly empty, never null.
     */
    List<Identifier> getIdentifiers();

}
