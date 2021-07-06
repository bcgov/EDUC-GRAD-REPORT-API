/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        OnlineAgreement.java 
 *  Date of Last Commit: $Date:: 2016-07-26 09:08:41 -0700 (Tue, 26 Jul 2016)  $  
 *  Revision Number:     $Rev:: 2365                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.users;

import ca.bc.gov.educ.isd.common.DomainEntity;

/**
 * Terms of service, which a user must agree to before they can use the system.
 *
 * The on-line agreement is a container for any terms of service or acceptable
 * user contracts that the user must agree to before they may use the system.
 *
 * <p>
 * By creating a domain object for this it is possible to track the changes to
 * the terms of service over time and users continuing agreement.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface OnlineAgreement extends DomainEntity {

    /**
     * Retrieve Mime type for the display of the contents of this agreement.
     *
     * @return MIME type
     */
    String getMimeType();

    //TODO - consider refactor to a described data super interface.
    //TODO - consider versioning in the super interface.
    /**
     * Retrieve the displayable title of the agreement.
     *
     * @return Displayable agreement title.
     */
    String getTitle();

    /**
     * Retrieve the body (terms) of the agreement.
     *
     * @return Displayable agreement body
     */
    String getContents();

    /**
     * Retrieve the associated Online Service Registration Form.
     * 
     * @return the Online Service Registration Form.
     */
    OnlineServiceReg getOnlineServiceReg();
}
