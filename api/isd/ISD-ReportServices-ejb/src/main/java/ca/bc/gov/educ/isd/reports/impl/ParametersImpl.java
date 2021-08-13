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
package ca.bc.gov.educ.isd.reports.impl;

import ca.bc.gov.educ.isd.reports.Parameters;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Contains a mapping of name/value pairs given to the report engine.
 *
 * @author CGI Information Management Consultants Inc.
 * @param <K> The parameter name to give the report.
 * @param <V> The parameter value to give the report.
 */
public class ParametersImpl<K, V>
        extends ConcurrentHashMap<K, V> implements Parameters<K, V> {

    private static final long serialVersionUID = -6543917105276076522L;
}
