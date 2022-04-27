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
 *  File:                $Id:: Parameters.java 7831 2017-08-03 17:54:07Z DAJAR#$
 *  Date of Last Commit: $Date:: 2017-08-03 10:54:07 -0700 (Thu, 03 Aug 2017)  $
 *  Revision Number:     $Rev:: 7831                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.reports;

import java.util.Map;

/**
 * Encapsulates a list of parameters passed into a report. Each parameter maps
 * name/value pairs.
 *
 * @author CGI Information Management Consultants Inc.
 * @param <K> The parameter name.
 * @param <V> The parameter value.
 */
public interface Parameters<K, V> extends Map<K, V> {
}
