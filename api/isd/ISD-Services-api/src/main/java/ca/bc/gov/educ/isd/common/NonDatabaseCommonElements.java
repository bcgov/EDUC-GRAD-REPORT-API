/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.common;

/**
 * Non-Database mapped field size definition.
 *
 * This class defines constants mostly used for the validation of user inputs
 * which are not mapped in the database
 *
 * For each definition, we provide a key/value pair that respect the naming
 * convention adopted in {@link DatabaseCommonElements}. It is mandatory to
 * respect the convention as other classes scanning this class through
 * reflection are expecting the specific semantic.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class NonDatabaseCommonElements {

    // Common PSI objects
    public static final String COL_PSI_APPLICANT_ID = "PSI_APPLICANT_ID";
    public static final int COL_PSI_APPLICANT_ID_LEN = 15;

    // Country
    /**
     * This is an absolute fudge that needs to be fixed. We have too many
     * definitions for the column size of what represent a country. File
     * DatabaseCommonElements defines: COL_COUNTRY_LEN = 70
     * COL_REFCOUNTRY_CODE_LEN = 50
     *
     * and in the xhtml, we limit the inputText size to maxlength="35"
     *
     * The following is the definition of a common denominator for what is the
     * acceptable maximum length a country name entered by the user till we
     * figure out a common value.
     */
    public static final String COL_DEFAULT_COUNTRY = "COL_DEFAULT_COUNTRY";
    public static final int COL_DEFAULT_COUNTRY_LEN = 35;

    
    public static final String COL_DEFAULT_VARCHAR = "COL_DEFAULT_VARCHAR";
    public static final int COL_DEFAULT_VARCHAR_LEN = 255;
    
    public static final String COL_DEFAULT_CIVIC_ADDRESS = "COL_DEFAULT_CIVIC_ADDRESS";
    public static final int COL_DEFAULT_CIVIC_ADDRESS_LEN = 40;
    
    // According to ISO 3166-1 alpha-2 (https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2)
    public static final String COL_DEFAULT_COUNTRY_CODE_SUBDIVISION = "COL_COUNTRY_CODE_SUBDIVISION";
    public static final int COL_DEFAULT_COUNTRY_CODE_SUBDIVISION_LEN = 2;
    
    // According to ISO 3166-1 (https://en.wikipedia.org/wiki/ISO_3166-1)
    public static final String COL_DEFAULT_COUNTRY_CODE = "COL_COUNTRY_CODE";
    public static final int COL_DEFAULT_COUNTRY_CODE_LEN = 2;
    
    
    

    private NonDatabaseCommonElements() {
    }

}
