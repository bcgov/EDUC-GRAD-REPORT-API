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
package ca.bc.gov.educ.isd.adaptor.dao.impl;

import ca.bc.gov.educ.isd.eis.trax.db.StudentProfileMasterLite;

import java.io.Serializable;

/**
 * The combined data from student master and profile for the search function in
 * the admin dashboard.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class StudentProfileMasterLiteEntity implements StudentProfileMasterLite, Serializable {

    private static final long serialVersionUID = 1L;

    private String pen;
    private String profileId;
    private String profileEntityId;
    private String lastLoginDate;
    private String birthdate;
    private String firstName;
    private String middleName;
    private String lastName;

    public StudentProfileMasterLiteEntity() {
    }

    public String getPen() {
        return pen;
    }
    public void setPen(final String pen) {
        this.pen = pen;
    }

    public String getProfileId() {
        return profileId;
    }
    public void setProfileId(final String profileId) {
        this.profileId = profileId;
    }

    public String getProfileEntityId() {
        return profileEntityId;
    }
    public void setProfileEntityId(final String profileEntityId) {
        this.profileEntityId = profileEntityId;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }
    public void setLastLoginDate(final String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(final String birthdate) {
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
}
