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
 *  File:                $Id:: TswTranNongradEntityPK.java 9614 2018-03-12 18:#$
 *  Date of Last Commit: $Date:: 2018-03-12 11:06:47 -0700 (Mon, 12 Mar 2018)  $
 *  Revision Number:     $Rev:: 9614                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl;

import java.io.Serializable;

/**
 * Represents a composite key for TswTranNongradEntity instances.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TswTranNongradEntityPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private String studNo;

    private String nonGradCode;

    public TswTranNongradEntityPK() {
    }

    public TswTranNongradEntityPK(String studNo, String nonGradCode) {
        this.studNo = studNo;
        this.nonGradCode = nonGradCode;
    }

    public String getStudNo() {
        return this.studNo;
    }

    public String getNonGradCode() {
        return this.nonGradCode;
    }
}
