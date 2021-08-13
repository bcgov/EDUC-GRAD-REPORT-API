/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        UserProfileSearchCriteria.java 
 *  Date of Last Commit: $Date:: 2016-10-20 11:38:12 -0700 (Thu, 20 Oct 2016)  $  
 *  Revision Number:     $Rev:: 4514                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.users;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @deprecated This is does not conform to the object creation pattern, should be an interface only.
 * @author CGI Information Management Consultants Inc.
 */
public class UserProfileSearchCriteria implements Serializable {
    
    private static final long serialVersionUID = 7526472295622776147L;
    
    private String pen;
    private String firstName;
    private String middleName;
    private String lastName;
    private String formerName;
    private Date dateOfBirth;
    private String contactEmailAddress;

    String getPen() {
        return pen;
    }

    public void setPen(String pen) {
        this.pen = pen;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFormerName() {
        return formerName;
    }

    public void setFormerName(String formerName) {
        this.formerName = formerName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }
    
    
}
