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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.adaptor.dao.impl;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * An auto generated entity for the STS_TRAN_CRSE_VW view which is a view
 * created in the ISD database and linked to the TRAX database STS_TRAN_CRSE
 * table using the database link. An embedded composite primary key has been
 * added since this data does not have a unique primary key column. The view
 * definition is as follows:
 * <p>
 * CREATE OR REPLACE FORCE EDITIONABLE VIEW ISD.STS_TRAN_CRSE_VW AS SELECT *
 * FROM STS_TRAN_CRSE@TRAXLINK.WORLD; with read only;
 * </p><br>
 * JPA access is on attributes directly. The attributes in this entity are used
 * to fulfill data requests for the Transcript, XML Transcript services.
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlRootElement
public class TranCourseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private CourseId primaryKey;
    private String courseName;
    private String numCredits;
    private String examPct;
    private String schoolPct;
    private String finalPct;
    private String finalLg;
    private String interimMark;
    private String interimLetterGrade;
    private String foundationReq;
    private String specialCase;
    private Long updateDt;
    private String rptCrsType;
    private String crsType;
    private String relatedCrse;
    private String relatedLevel;
    private String usedForGrad;

    public TranCourseEntity() {
    }

    /**
     * get the entity primary key. It is a compound key of type CourseId.
     *
     * @return entity primary key object
     */
    public CourseId getPrimaryKey() {
        return primaryKey;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getNumCredits() {
        return numCredits;
    }

    public String getExamPct() {
        return examPct;
    }

    public String getSchoolPct() {
        return schoolPct;
    }

    public String getFinalPct() {
        return finalPct;
    }

    public String getFinalLg() {
        return finalLg;
    }

    public String getInterimMark() {
        return interimMark;
    }

    public String getInterimLetterGrade() {
        return interimLetterGrade;
    }

    public String getFoundationReq() {
        return foundationReq;
    }

    public String getSpecialCase() {
        return specialCase;
    }

    public Long getUpdateDt() {
        return updateDt;
    }

    public String getRptCrsType() {
        return rptCrsType;
    }

    public String getCrsType() {
        return crsType;
    }

    public String getUsedForGrad() {
        return usedForGrad;
    }

    public String getRelatedCrse() {
        return relatedCrse;
    }

    public String getRelatedLevel() {
        return relatedLevel;
    }

    public void setPrimaryKey(CourseId primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setNumCredits(String numCredits) {
        this.numCredits = numCredits;
    }

    public void setExamPct(String examPct) {
        this.examPct = examPct;
    }

    public void setSchoolPct(String schoolPct) {
        this.schoolPct = schoolPct;
    }

    public void setFinalPct(String finalPct) {
        this.finalPct = finalPct;
    }

    public void setFinalLg(String finalLg) {
        this.finalLg = finalLg;
    }

    public void setInterimMark(String interimMark) {
        this.interimMark = interimMark;
    }

    public void setInterimLetterGrade(String interimLetterGrade) {
        this.interimLetterGrade = interimLetterGrade;
    }

    public void setFoundationReq(String foundationReq) {
        this.foundationReq = foundationReq;
    }

    public void setSpecialCase(String specialCase) {
        this.specialCase = specialCase;
    }

    public void setUpdateDt(Long updateDt) {
        this.updateDt = updateDt;
    }

    public void setRptCrsType(String rptCrsType) {
        this.rptCrsType = rptCrsType;
    }

    public void setCrsType(String crsType) {
        this.crsType = crsType;
    }

    public void setRelatedCrse(String relatedCrse) {
        this.relatedCrse = relatedCrse;
    }

    public void setRelatedLevel(String relatedLevel) {
        this.relatedLevel = relatedLevel;
    }

    public void setUsedForGrad(String usedForGrad) {
        this.usedForGrad = usedForGrad;
    }
}
