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
 *  File:                ParameterPredicateImpl.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.jasper.impl;

import ca.bc.gov.educ.isd.common.support.filter.AbstractPredicate;
import ca.bc.gov.educ.isd.transcript.ParameterPredicate;

/**
 *
 * @author martin.talbot
 */
public class ParameterPredicateImpl extends AbstractPredicate<String> implements ParameterPredicate {
    private String key;

    public ParameterPredicateImpl() {};

    public String getKey() {
        return this.key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }
    
    @Override
    public boolean evaluate(final String c) {
        return c.startsWith(getKey());
    }
}
