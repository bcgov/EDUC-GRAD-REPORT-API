/*
 * *********************************************************************
 *  Copyright (c) 2015, Ministry of Education, BC.
 *
 *  All rights reserved.
 *
 *  Revision Control Information
 *  File:                DatabaseDefinitions.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */

package ca.bc.gov.educ.grad.dto.impl.constants;

/**
 * Contains the centralized definitions of the table names, column names and
 * schema.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class DatabaseDefinitions {

    public static final String SYSTEM = "ISD_";

    public static final String SCHEMA = "STUD";

    private static final String XREF = "STUDENT_X_REF";

    public static final String XREF_TABLE = SYSTEM + SCHEMA + "_" + XREF;
    public static final String XREF_TABLE_SEQ = XREF_TABLE + "_SEQ";
    public static final String XREF_TABLE_ID_NAME = SCHEMA + "_" +XREF + "_ID";
    public static final String XREF_SEQ = XREF_TABLE + "_SEQ";
    
    private static final String IDENTIFIER = "IDENTIFIER";

    public static final String IDENTIFIER_TABLE = SYSTEM + SCHEMA + "_" + IDENTIFIER;
    public static final String IDENTIFIER_TABLE_SEQ = IDENTIFIER_TABLE + "_SEQ";
    public static final String IDENTIFIER_TABLE_ID_NAME = SCHEMA + "_" +IDENTIFIER + "_ID";
    
    private static final String PEN = "PEN";

    public static final String PEN_TABLE = SYSTEM + SCHEMA + "_" + PEN;
    public static final String PEN_TABLE_SEQ = IDENTIFIER_TABLE + "_SEQ";
    public static final String PEN_TABLE_ID_NAME = SCHEMA + "_" +PEN + "_ID";
}
