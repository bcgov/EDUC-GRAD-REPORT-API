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
 *  File:                EntitlementCategoryEnum.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.entitlements;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum EntitlementCategoryEnum {

    CURRENT_STUDENTS("CURRENT_STUDENTS", "CURRENT STUDENTS"),
    FORMER_STUDENTS("FORMER_STUDENTS", "FORMER STUDENTS");

    String name;
    String description;

    private EntitlementCategoryEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

}
