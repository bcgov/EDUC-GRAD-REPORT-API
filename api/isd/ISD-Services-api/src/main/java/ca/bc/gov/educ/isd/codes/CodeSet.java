/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        CodeSet.java 
 *  Date of Last Commit: $Date:: 2016-04-06 13:01:46 -0700 (Wed, 06 Apr 2016)  $  
 *  Revision Number:     $Rev:: 1115                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.codes;

import ca.bc.gov.educ.isd.common.DomainEntity;

import java.util.Date;
import java.util.List;

/**
 * A named set of codes.
 *
 * @author CGI Information Management Consultants Inc.
 * @see
 * <a href="http://specification.sifassociation.org/Implementation/NA/3.3/">
 * SIF Implementation Specification (NA) 3.3 - Data Model</a>
 */
public interface CodeSet extends DomainEntity {

    /**
     *
     * @return
     */
    String getCodeSetHex();

    /**
     *
     * @return
     */
    String getName();

    /**
     *
     * @return
     */
    String getVersion();

    /**
     *
     * @return
     */
    String getDescription();

    /**
     *
     * @return
     */
    String getSource();

    /**
     * Return the fully qualified name space for this code.
     *
     * @return String Name space representing this Code Set.
     */
    String getNamespace();

    /**
     * Retrieve the list of codes which are part of this Code Set.
     *
     * @return List of Codes in this Code Set.
     */
    List<Code> getCodes();

    //TODO this should be a long and a TTL
    /**
     * Retrieve the expiry date of this code set.
     * <p>
     * @return The expiry date.
     */
    Date getExpiryDate();
}
