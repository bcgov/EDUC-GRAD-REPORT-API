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
package ca.bc.gov.educ.isd.traxadaptor.dao.impl;

import java.io.Serializable;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TabProvEntityPK implements Serializable {

    private String provCode;
    private String provName;
    private String cntryCode;

    public TabProvEntityPK() {
    }

    public TabProvEntityPK(final String provCode, final String provName, final String cntryCode) {
        this.provCode = provCode;
        this.provName = provName;
        this.cntryCode = cntryCode;
    }

    public String getProvCode() {
        return provCode;
    }

    public void setProvCode(final String provCode) {
        this.provCode = provCode;
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(final String provName) {
        this.provName = provName;
    }

    public String getCntryCode() {
        return cntryCode;
    }

    public void setCntryCode(final String cntryCode) {
        this.cntryCode = cntryCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (provCode != null ? provCode.hashCode() : 0);
        hash += (provName != null ? provName.hashCode() : 0);
        hash += (cntryCode != null ? cntryCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabProvEntityPK)) {
            return false;
        }
        TabProvEntityPK other = (TabProvEntityPK) object;
        if ((this.provCode == null && other.provCode != null) || (this.provCode != null && !this.provCode.equals(other.provCode))) {
            return false;
        }
        if ((this.provName == null && other.provName != null) || (this.provName != null && !this.provName.equals(other.provName))) {
            return false;
        }
        return !((this.cntryCode == null && other.cntryCode != null) || (this.cntryCode != null && !this.cntryCode.equals(other.cntryCode)));
    }

    @Override
    public String toString() {
        return "ca.bc.gov.educ.isd.traxadaptor.dao.TabProvEntityPK[ provCode=" + provCode + ", provName=" + provName + ", cntryCode=" + cntryCode + " ]";
    }

}
