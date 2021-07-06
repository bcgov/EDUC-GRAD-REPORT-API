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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.traxadaptor.impl;

import ca.bc.gov.educ.isd.eis.trax.db.TabProvince;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TabProvinceImpl implements TabProvince {

    private String provCode;
    private String provName;
    private String cntryCode;

    public TabProvinceImpl() {
    }

    public TabProvinceImpl(final String provCode, final String provName, final String cntryCode) {
        this.provCode = provCode;
        this.provName = provName;
        this.cntryCode = cntryCode;
    }

    @Override
    public String getRegionCode() {
        return provCode;
    }

    @Override
    public void setRegionCode(String provCode) {
        this.provCode = provCode;
    }

    @Override
    public String getRegionName() {
        return provName;
    }

    @Override
    public void setRegionName(String provName) {
        this.provName = provName;
    }

    @Override
    public String getCntryCode() {
        return cntryCode;
    }

    @Override
    public void setCntryCode(String cntryCode) {
        this.cntryCode = cntryCode;
    }
}
