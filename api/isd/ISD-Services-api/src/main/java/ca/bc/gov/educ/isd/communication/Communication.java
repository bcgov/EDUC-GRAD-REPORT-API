/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        Communication.java 
 *  Date of Last Commit: $Date:: 2016-08-25 10:55:55 -0700 (Thu, 25 Aug 2016)  $  
 *  Revision Number:     $Rev:: 3100                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.communication;

import ca.bc.gov.educ.isd.common.DomainEntity;
import ca.bc.gov.educ.isd.common.party.Party;
import ca.bc.gov.educ.isd.common.party.address.Address;

/**
 * A message between parties.
 *
 * @author CGI Information Management Consultants Inc.
 * @deprecated cp - restructuring to use notification interfaces.
 */
public interface Communication extends DomainEntity {

    Party getSender();

    void setSender(Party party);

    Party getRecipient();

    void setRecipient(Party party);

    Address getAddress();

    void setAddress(Address addr);
}
