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
 *  File:                $Id:: SelfServeOrderReportImpl.java 3663 2016-09-19 #$
 *  Date of Last Commit: $Date:: 2016-09-19 14:41:55 -0700 (Mon, 19 Sep 2016) $
 *  Revision Number:     $Rev:: 3663                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                   $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.admin.impl;

import ca.bc.gov.educ.isd.reports.admin.data.SelfServeOrder;
import ca.bc.gov.educ.isd.reports.jasper.impl.AdminReportImpl;

/**
 * Responsible for exporting SelfServeOrder reports.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class SelfServeOrderReportImpl
        extends AdminReportImpl<SelfServeOrder> {

    private static final long serialVersionUID = 10L;

    /**
     * Default (empty) constructor.
     */
    public SelfServeOrderReportImpl() {
        super("SelfServeOrderReport");
    }
}
