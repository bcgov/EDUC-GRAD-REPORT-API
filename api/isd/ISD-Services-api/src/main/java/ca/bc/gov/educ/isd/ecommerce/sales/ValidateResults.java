/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 * 
 *  Revision Control Information
 *  File:                ValidateResults.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.sales;

import java.util.List;

/**
 * Results of calling validate. Indicator if valid or not and reasons why.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ValidateResults {

    Boolean isValid();

    List<String> getReasons();
}
