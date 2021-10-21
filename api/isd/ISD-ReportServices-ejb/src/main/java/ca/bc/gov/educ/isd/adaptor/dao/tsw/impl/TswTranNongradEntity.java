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
package ca.bc.gov.educ.isd.adaptor.dao.tsw.impl;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * An auto generated entity for the TSW_TRAN_NONGRAD_VW which is a view created
 * in the ISD database and linked to the TRAX database tsw_tran_nongrad table
 * using the database link. JPA access is on attributes directly. The attributes
 * in this entity are used to fulfill data requests for the Transcript, XML
 * Transcript services.
 *
 * @author CGI Information Management Consultants Inc.
 */

@XmlRootElement
public class TswTranNongradEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private TswTranNongradEntityPK id;

    private String nonGradDesc;
    private Long updateDt;

    public TswTranNongradEntity() {
    }

    public TswTranNongradEntity(TswTranNongradEntityPK id, String nonGradDesc, Long updateDt) {
        this.id = id;
        this.nonGradDesc = nonGradDesc;
        this.updateDt = updateDt;
    }

    public TswTranNongradEntityPK getId() {
        return this.id;
    }

    public String getStudNo() {
        return getId().getStudNo();
    }

    public String getNonGradCode() {
        return getId().getNonGradCode();
    }

    public String getNonGradDesc() {
        return this.nonGradDesc;
    }

    public Long getUpdateDt() {
        return this.updateDt;
    }
}
