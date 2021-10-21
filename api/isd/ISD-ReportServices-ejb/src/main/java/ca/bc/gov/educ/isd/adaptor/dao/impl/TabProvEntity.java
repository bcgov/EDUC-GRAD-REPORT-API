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
 *  File:                TabProvEntity.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.adaptor.dao.impl;

import java.io.Serializable;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TabProvEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    protected TabProvEntityPK tabProvEntityPK;

    public TabProvEntity() {
    }

    public TabProvEntity(TabProvEntityPK tabProvEntityPK) {
        this.tabProvEntityPK = tabProvEntityPK;
    }

    public TabProvEntity(String provCode, String provName, String cntryCode) {
        this.tabProvEntityPK = new TabProvEntityPK(provCode, provName, cntryCode);
    }

    public TabProvEntityPK getTabProvEntityPK() {
        return tabProvEntityPK;
    }

    public void setTabProvEntityPK(TabProvEntityPK tabProvEntityPK) {
        this.tabProvEntityPK = tabProvEntityPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tabProvEntityPK != null ? tabProvEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabProvEntity)) {
            return false;
        }
        TabProvEntity other = (TabProvEntity) object;
        return !((this.tabProvEntityPK == null && other.tabProvEntityPK != null) || (this.tabProvEntityPK != null && !this.tabProvEntityPK.equals(other.tabProvEntityPK)));
    }

    @Override
    public String toString() {
        return "ca.bc.gov.educ.isd.traxadaptor.dao.TabProvEntity[ tabProvEntityPK=" + tabProvEntityPK + " ]";
    }

}
