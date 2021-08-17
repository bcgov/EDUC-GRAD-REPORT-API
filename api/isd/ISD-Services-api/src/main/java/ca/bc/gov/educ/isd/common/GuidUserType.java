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

import java.io.Serializable;
import java.util.Objects;

import ca.bc.gov.educ.isd.users.UserAccountType;

/**
 * This class will contain guid and usertype for the user.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class GuidUserType implements Serializable {

    private static final long serialVersionUID = 3L;

    private UserAccountType userAccountType;
    private String guid;
    private String displayName;

    public GuidUserType(
            final String guid,
            final String displayName,
            final UserAccountType userAccountType) {
        this.guid = guid;
        this.displayName = displayName;
        this.userAccountType = userAccountType;
    }

    /**
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * TODO: Replace with an exception.
     *
     * @return
     */
    public Boolean validateNullSafe() {
        Boolean result = false;

        if (userAccountType != null && guid != null && !(userAccountType.getAccountTypeDesc().isEmpty()) && !(this.getGuid().isEmpty())) {
            result = true;
        }

        assert result : "User's account type or guid parameter MAY NOT be null or empty.";
        return result;
    }

    /**
     * @param guid the guid to set
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return the userType
     */
    public UserAccountType getUserAccountType() {
        return userAccountType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserAccountType(final UserAccountType userType) {
        this.userAccountType = userType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.guid);
        hash = 29 * hash + Objects.hashCode(this.displayName);
        hash = 29 * hash + Objects.hashCode(this.userAccountType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GuidUserType other = (GuidUserType) obj;
        if (!Objects.equals(this.guid, other.guid)) {
            return false;
        }
        if (!Objects.equals(this.displayName, other.displayName)) {
            return false;
        }
        return this.userAccountType == other.userAccountType;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "guid=" + guid + ", displayName=" + displayName + ", userAccountType=" + userAccountType + '}';
    }
}
