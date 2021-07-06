/* *************************************************************************
 *   Copyright (c) 2015, Ministry of Education, BC.
 * 
 *   All rights reserved.
 *     This information contained herein may not be used in whole
 *     or in part without the express written consent of the
 *     Government of British Columbia, Canada.
 * 
 *   Revision Control Information
 *   File:                $Id:: OrderPerson.java 5390 2016-11-25 06:33:08Z bbat#$
 *   Date of Last Commit: $Date:: 2016-11-24 22:33:08 -0800 (Thu, 24 Nov 2016)  $
 *   Revision Number:     $Rev:: 5390                                           $
 *   Last Commit by:      $Author:: bbates                                     $
 * 
 *  
 **********************************************************************  */
package ca.bc.gov.educ.isd.ecommerce.sales.ged;

import java.io.Serializable;
import java.util.Date;

//TODO cp - temporary until we can review the common.party packages.
/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface OrderPerson extends Cloneable, Serializable {

    Date getDateOfbirth();

    String getFirstName();

    String getFormerName();

    String getLastName();

    String getMiddleName();

    void setDateOfbirth(Date dateOfbirth);

    void setFirstName(String firstName);

    void setFormerName(String formerName);

    void setLastName(String lastName);

    void setMiddleName(String middleName);

    /**
     * @return the email
     */
    String getEmail();

    /**
     * @param email the email to set
     */
    void setEmail(String email);

}
