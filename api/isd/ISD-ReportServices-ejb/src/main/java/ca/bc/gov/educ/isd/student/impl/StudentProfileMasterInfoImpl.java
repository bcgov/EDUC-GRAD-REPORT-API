/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
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
package ca.bc.gov.educ.isd.student.impl;

import ca.bc.gov.educ.isd.student.StudentProfileMasterInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.trimSafe;

/**
 * Represents combined data from student profile and student master. Correlated by the PEN.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class StudentProfileMasterInfoImpl implements StudentProfileMasterInfo, Serializable {

    private static final long serialVersionUID = 1L;
    private static final int DEF_FULLNAME_STRING_BUFFER_SIZE = 100;
    private static final int DEF_NAME_PARTS_ARRAY_SIZE = 3;
    private static final String NAME_PARTS_DELIMITER = " ";
    
    final private String pen;
    final private String profileId;
    final private String lastLoginDate;
    final private String birthdate;
    final private String firstName;
    final private String middleName;
    final private String lastName;

    private int index;
    
    
    public StudentProfileMasterInfoImpl(
            final String pen, final String profileId, final String lastLoginDate, final String birthdate, 
            final String firstName, final String middleName, final String lastName) {
        this.pen = pen;
        this.profileId = profileId;
        this.lastLoginDate = lastLoginDate;
        this.birthdate = birthdate;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
    
    @Override
    public String getPen() {
        return pen;
    }

    @Override
    public String getProfileId() {
        return profileId;
    }

    @Override
    public String getLastLoginDate() {
        return lastLoginDate;
    }

    @Override
    public String getBirthdate() {
        return birthdate;
    }
    
    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
    
    @Override
    public String getFullName() {
        List<String> nameParts = new ArrayList<>(DEF_NAME_PARTS_ARRAY_SIZE);

        nameParts.add(trimSafe(getFirstName()));
        nameParts.add(trimSafe(getMiddleName()));
        nameParts.add(trimSafe(getLastName()));
        
        StringBuilder sb = new StringBuilder(DEF_FULLNAME_STRING_BUFFER_SIZE);
        String delimiter = "";
        
        for (String part : nameParts) {
            if (!part.isEmpty()) {
                sb.append(delimiter);
                delimiter = NAME_PARTS_DELIMITER;

                sb.append(part);
            }
        }
        
        return sb.toString();
    }

    public int getIndex() {
        return index;
    }
    
    public void setIndex(final int index) {
        this.index = index;
    }
}
