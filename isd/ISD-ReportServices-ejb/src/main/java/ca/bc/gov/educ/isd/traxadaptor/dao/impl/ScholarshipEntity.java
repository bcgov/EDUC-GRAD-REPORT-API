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
package ca.bc.gov.educ.isd.traxadaptor.dao.impl;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * An auto generated entity for the student scholarship view which is a view
 * created in the ISD database and linked to the TRAX database stud_award table
 * using the database link. The view definition is as follows;
 * <p>
 * CREATE OR REPLACE FORCE VIEW "ISD"."STUDENT_SCHOLARSHIP_VW" ("STUD_NO",
 * "AWARD_CODE", "AWARD_YEAR", "AWARD_CASH_DATE", "AWARD_AMT") AS ( SELECT
 * stud_no, award_code, award_year, award_cash_date, award_amt FROM
 * stud_award@traxlink.world where award_year > 2011 ) with read only;
 * </p><br>
 * JPA access is on attributes directly. The attributes in this list are used to
 * fulfill the data requests for the Scholarship service.
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlRootElement
public class ScholarshipEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private ScholarshipId primaryKey;

    private Integer awardYear;
    private Date awardCashDate;
    private Integer awardAmt;

    public ScholarshipEntity() {
    }

    /**
     * Return the compound key consisting of studNo and awardCode.
     *
     * @return entity primary key object
     */
    public ScholarshipId getPrimaryKey() {
        return this.primaryKey;
    }

    public Integer getAwardYear() {
        return this.awardYear;
    }

    public Date getAwardCashDate() {
        return this.awardCashDate == null ? null : new Date(this.awardCashDate.getTime());
    }

    public Integer getAwardAmt() {
        return this.awardAmt;
    }

    public void setAwardAmt(final Integer awardAmt) {
        this.awardAmt = awardAmt;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.primaryKey);
        hash = 13 * hash + Objects.hashCode(this.awardYear);
        hash = 13 * hash + Objects.hashCode(this.awardCashDate);
        hash = 13 * hash + Objects.hashCode(this.awardAmt);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ScholarshipEntity other = (ScholarshipEntity) obj;
        if (!Objects.equals(this.primaryKey, other.primaryKey)) {
            return false;
        }
        if (!Objects.equals(this.awardYear, other.awardYear)) {
            return false;
        }
        if (!Objects.equals(this.awardCashDate, other.awardCashDate)) {
            return false;
        }
        return Objects.equals(this.awardAmt, other.awardAmt);
    }

}
