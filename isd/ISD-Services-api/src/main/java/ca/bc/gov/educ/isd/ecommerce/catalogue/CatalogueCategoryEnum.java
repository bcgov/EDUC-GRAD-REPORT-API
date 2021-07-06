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
 *  File:                CatalogueCategoryEnum.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.catalogue;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum CatalogueCategoryEnum {

    PEN_ITEMS_CS("PEN CS", "PEN ITEMS CS"),
    PEN_ITEMS_FS("PEN FS", "PEN ITEMS FS"),
    NON_PEN_ITEMS("NON-PEN", "NON-PEN ITEMS"),
    GED_ITEMS("GED Transcripts and Certificates. (Special)", "GED ITEMS");

    String name;
    String description;

    private CatalogueCategoryEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

}
