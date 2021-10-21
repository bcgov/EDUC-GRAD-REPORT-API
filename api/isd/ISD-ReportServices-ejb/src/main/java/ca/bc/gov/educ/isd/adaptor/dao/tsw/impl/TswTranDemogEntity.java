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

import java.io.Serializable;
import java.util.Date;

/**
 * An auto generated entity for the TSW_TRAN_DEMOG_VW view which is a view
 * created in the ISD database and linked to the TRAX database tsw_tran_demog
 * table using the database link. JPA access is on attributes directly. The
 * attributes in this entity are used to fulfill data requests for the
 * Transcript, XML Transcript services, and Exam services.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TswTranDemogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String studNo;

    private String firstName;

    private String middleName;

    private String lastName;

    private Date birthdate;

    private String localId;

    private Character studGender;

    private String mincode;

    private String studGrade;

    private String gradDate;

    private String gradReqtYear;

    private Date updateDt;

    private String logoType;

    private String gradMsgTxt;

    private Character gradFlag;

    private Character currentFormerFlag;

    public TswTranDemogEntity() {
    }

    public TswTranDemogEntity(
            String studNo,
            String firstName,
            String middleName,
            String lastName,
            Date birthdate,
            String localId,
            Character studGender,
            String mincode,
            String studGrade,
            String gradDate,
            String gradReqtYear,
            Date updateDt,
            String logoType,
            String gradMsgTxt,
            Character gradFlag,
            Character currentFormerFlag
    ) {
        this.studNo = studNo;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.localId = localId;
        this.studGender = studGender;
        this.mincode = mincode;
        this.studGrade = studGrade;
        this.gradDate = gradDate;
        this.gradReqtYear = gradReqtYear;
        this.updateDt = updateDt;
        this.logoType = logoType;
        this.gradMsgTxt = gradMsgTxt;
        this.gradFlag = gradFlag;
        this.currentFormerFlag = currentFormerFlag;
    }

    public String getStudNo() {
        return studNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getLocalId() {
        return localId;
    }

    public Character getStudGender() {
        return studGender;
    }

    public String getMincode() {
        return mincode;
    }

    public String getStudGrade() {
        return studGrade;
    }

    public String getGradDate() {
        return gradDate;
    }

    public String getGradReqtYear() {
        return gradReqtYear;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public String getLogoType() {
        return logoType;
    }

    public String getGradMsg() {
        return gradMsgTxt;
    }

    public Character getGradFlag() {
        return gradFlag;
    }

    public Character getCurrentFormerFlag() {
        return currentFormerFlag;
    }
}
