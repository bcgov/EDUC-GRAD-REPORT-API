/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: EnvironmentConstants.java 7404 2017-06-06 23:39#$
 *  Date of Last Commit: $Date:: 2017-06-06 16:39:04 -0700 (Tue, 06 Jun 2017)  $
 *  Revision Number:     $Rev:: 7404                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.common;

import static ca.bc.gov.educ.isd.common.Constants.PAYMENT_SERVICES_MODULE_NAME;

/**
 * JNDI Environment Settings
 *
 * @author CGI Information Management Consultants Inc.
 */
public class EnvironmentConstants {

    public static final String JNDI_ENV_PREFIX = "java:/" + PAYMENT_SERVICES_MODULE_NAME;
    public static final String JNDI_ENV_TRANSACTION_PROCESSOR = JNDI_ENV_PREFIX + "/merchant/ref1";

    private EnvironmentConstants() {
    }
}
