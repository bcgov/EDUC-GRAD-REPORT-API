/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information
 *  File:                LinkedParameters.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.reports;

import java.util.LinkedHashMap;

/**
 * Encapsulates a linked list of parameters passed into a report. Each parameter maps
 * name/value pairs with their insertion order maintained.
 *
 * @author CGI Information Management Consultants Inc.
 * @param <K> The parameter name.
 * @param <V> The parameter value.
 */
public class LinkedParameters<K, V> extends LinkedHashMap<K, V> implements Parameters<K, V>  {
    
}
