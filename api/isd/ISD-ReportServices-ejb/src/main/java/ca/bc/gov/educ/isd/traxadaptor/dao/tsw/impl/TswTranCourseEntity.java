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
package ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl;

import ca.bc.gov.educ.isd.traxadaptor.dao.impl.CourseId;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * An auto generated entity for the TSW_TRAN_COURSE_VW view which is a view
 * created in the ISD database and linked to the TRAX database tsw_tran_crse
 * table using the database link. An embedded composite primary key has been
 * added since this data does not have a unique primary key column. The view
 * definition is as follows:
 * <p>
 * CREATE OR REPLACE FORCE VIEW "ISD"."TSW_TRAN_COURSE_VW" ("STUD_NO",
 * "COURSE_NAME", "CRSE_CODE", "CRSE_LEVEL", "COURSE_SESSION", "NUM_CREDITS",
 * "EXAM_PCT", "SCHOOL_PCT", "FINAL_PCT", "FINAL_LG", "INTERIM_MARK",
 * "FOUNDATION_REQ", "SPECIAL_CASE", "UPDATE_DT", "RPT_CRS_TYPE") AS ( SELECT
 * stud_no, course_name, crse_code, crse_level, course_session, num_credits,
 * exam_pct, school_pct, final_pct, final_lg, interim_mark, foundation_req,
 * special_case, update_dt, rpt_crs_type FROM tsw_tran_crse@traxlink.world )
 * with read only;
 * </p><br>
 * JPA access is on attributes directly. The attributes in this entity are used
 * to fulfill data requests for the Transcript, XML Transcript services.
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlRootElement
public class TswTranCourseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private CourseId primaryKey;

    private String courseName;

    private String numCredits;

    private String examPct;

    private String schoolPct;

    private String finalPct;

    private String finalLg;

    private String interimMark;

    private String foundationReq;

    private String specialCase;

    private Long updateDt;

    private Character rptCrsType;

    public TswTranCourseEntity() {
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

    public String getFoundationReq() {
        return foundationReq;
    }

    public String getSpecialCase() {
        return specialCase;
    }

    public Long getUpdateDt() {
        return updateDt;
    }

    public Character getRptCrsType() {
        return rptCrsType;
    }
}
