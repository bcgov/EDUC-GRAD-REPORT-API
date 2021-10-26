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
 *  File:                ScholarshipId.java
 *  Date of Last Commit: $Date::                   $
 *  Revision Number:     $Rev::                    $
 *  Last Commit by:      $Author::                 $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.adaptor.dao.impl;

import java.io.Serializable;
import java.util.Objects;

/**
 * Embeddable JPA Entity composite primary key consisting of student number and
 * scholarship code. This class is used as an embedded primary key for the
 * entities which do not have a unique primary key defined by the database view
 * they mirror. The Entities must have these named attributes or have mapped
 * attributes which override these names in order to use this class.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ScholarshipId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String studNo;
    private Character awardCode;

    public ScholarshipId() {
    }

    /**
     * Constructor method used by JPA to create a composite primary key.
     *
     * @param studNo
     * @param awardCode
     */
    public ScholarshipId(final String studNo, final Character awardCode) {
        this.studNo = studNo;
        this.awardCode = awardCode;
    }

    /**
     * get the student number.
     *
     * @return student PEN
     */
    public String getStudNo() {
        return studNo;
    }

    /**
     * get the award code.
     *
     * @return award code
     */
    public Character getAwardCode() {
        return awardCode;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.studNo);
        hash = 71 * hash + Objects.hashCode(this.awardCode);
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
        final ScholarshipId other = (ScholarshipId) obj;
        if (!Objects.equals(this.studNo, other.studNo)) {
            return false;
        }
        return Objects.equals(this.awardCode, other.awardCode);
    }

}
