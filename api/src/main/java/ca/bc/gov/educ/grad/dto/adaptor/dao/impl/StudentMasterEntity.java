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
package ca.bc.gov.educ.grad.dto.adaptor.dao.impl;

import ca.bc.gov.educ.grad.model.student.StudentMaster;

import java.io.Serializable;
import java.util.Date;

/**
 * An auto generated entity for the STUDENT_MASTER_VW view which is a view
 * created in the ISD database and linked to the TRAX database student_master
 * table using the database link. JPA access is on attributes directly. The
 * attributes in this list are used to fulfill the data requests for the
 * Transcript, XML Transcript, Student Demographics, Grad Certificate, and
 * Scholarship services.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class StudentMasterEntity implements StudentMaster, Serializable {

    private static final long serialVersionUID = 2L;

    private String studNo;
    private String studGiven;
    private String studMiddle;
    private String studSurname;
    private Date studBirth;
    private String address1;
    private String address2;
    private String city;
    private String provCode;
    private String postal;
    private String studStatus;
    private String studGrade;
    private Date gradDate;
    private String gradReqtYear;
    private Character honourFlag;
    private Character dogwoodFlag;
    private Date sccDate;
    private String mincode;
    private String mincodeGrad;
    private String prgmCode;
    private String prgmCode2;
    private String prgmCode3;
    private String prgmCode4;
    private String prgmCode5;
    private String englishCert;
    private String frenchCert;
    private String traxCountryCode;
    private String stud_true_no;

    private transient String isoCountryCode;

    public StudentMasterEntity() {
    }

    public StudentMasterEntity(
            String studNo,
            String studGiven,
            String studMiddle,
            String studSurname,
            Date studBirth,
            String address1,
            String address2,
            String city,
            String provCode,
            String postal,
            String studStatus,
            String studGrade,
            Date gradDate,
            String gradReqtYear,
            Character honourFlag,
            Character dogwoodFlag,
            Date sccDate,
            String mincode,
            String mincodeGrad,
            String prgmCode,
            String prgmCode2,
            String prgmCode3,
            String prgmCode4,
            String prgmCode5,
            String englishCert,
            String frenchCert,
            String traxCountryCode,
            String stud_true_no,
            String isoCountryCode
    ) {
        this.studNo = studNo;
        this.studGiven = studGiven;
        this.studMiddle = studMiddle;
        this.studSurname = studSurname;
        this.studBirth = studBirth;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.provCode = provCode;
        this.postal = postal;
        this.studStatus = studStatus;
        this.studGrade = studGrade;
        this.gradDate = gradDate;
        this.gradReqtYear = gradReqtYear;
        this.honourFlag = honourFlag;
        this.dogwoodFlag = dogwoodFlag;
        this.sccDate = sccDate;
        this.mincode = mincode;
        this.mincodeGrad = mincodeGrad;
        this.prgmCode = prgmCode;
        this.prgmCode2 = prgmCode2;
        this.prgmCode3 = prgmCode3;
        this.prgmCode4 = prgmCode4;
        this.prgmCode5 = prgmCode5;
        this.englishCert = englishCert;
        this.frenchCert = frenchCert;
        this.traxCountryCode = traxCountryCode;
        this.stud_true_no = stud_true_no;
        this.isoCountryCode = isoCountryCode;
    }

    @Override
    public String getPen() {
        return getStudNo();
    }

    @Override
    public String getStudNo() {
        return studNo;
    }

    @Override
    public String getStudGiven() {
        return studGiven;
    }

    @Override
    public String getStudMiddle() {
        return studMiddle;
    }

    @Override
    public String getStudSurname() {
        return studSurname;
    }

    /**
     * This will return the student's birthdate or DATE_UNKNOWN_BIRTHDATE (Jan
     * 1, 1900) in the event that there was no birthdate or the value couldn't
     * be parsed.
     *
     * @return The student's birthdate or Jan 1, 1900.
     */
    @Override
    public Date getStudBirth() {
        return studBirth;
    }

    @Override
    public String getAddress1() {
        return address1;
    }

    @Override
    public String getAddress2() {
        return address2;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getProvCode() {
        return provCode;
    }

    @Override
    public String getPostal() {
        return postal;
    }

    @Override
    public String getStudStatus() {
        return studStatus;
    }

    @Override
    public String getStudGrade() {
        return studGrade;
    }

    @Override
    public Date getGradDate() {
        return gradDate;
    }

    @Override
    public String getGradReqtYear() {
        return gradReqtYear;
    }

    @Override
    public Character getHonourFlag() {
        return honourFlag;
    }

    @Override
    public Character getDogwoodFlag() {
        return dogwoodFlag;
    }

    @Override
    public Date getSccDate() {
        return sccDate;
    }

    @Override
    public String getMincode() {
        return mincode;
    }

    @Override
    public String getMincodeGrad() {
        return mincodeGrad;
    }

    @Override
    public String getPrgmCode() {
        return prgmCode;
    }

    @Override
    public String getPrgmCode2() {
        return prgmCode2;
    }

    @Override
    public String getPrgmCode3() {
        return prgmCode3;
    }

    @Override
    public String getPrgmCode4() {
        return prgmCode4;
    }

    @Override
    public String getPrgmCode5() {
        return prgmCode5;
    }

    @Override
    public String getEnglishCert() {
        return englishCert;
    }

    @Override
    public String getFrenchCert() {
        return frenchCert;
    }

    @Override
    public String getCountryCode() {
        return isoCountryCode;
    }

}
