/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 * 
 *  Revision Control Information
 *  File:                SearchObjectFieldValue.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.common;

/**
 * Search object that contains field name and value to search for.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SearchObjectFieldValue extends SearchObject {

    String getFieldName();

    Object getValue();
}
