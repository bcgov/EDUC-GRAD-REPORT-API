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

import java.io.Serializable;
import java.util.Objects;

/**
 * Embeddable JPA Entity composite primary key consisting of student number,
 * course code, course level and course session. This class is used as an
 * embedded primary key for the entities which do not have a unique primary key
 * defined by the database view they mirror. The Entities must have these named
 * attributes or have mapped attributes which override these names in order to
 * use this class.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class CourseId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String studNo;
    private String crseCode;
    private String crseLevel;
    private String crseSession;

    public CourseId() {
    }

    /**
     * Constructor method used by JPA to create a composite primary key.
     *
     * @param studNo
     * @param crseCode
     * @param crseLevel
     * @param crseSession
     */
    public CourseId(String studNo, String crseCode, String crseLevel, String crseSession) {
        this.studNo = studNo;
        this.crseCode = crseCode;
        this.crseLevel = crseLevel;
        this.crseSession = crseSession;
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
     * get the course code.
     *
     * @return course code
     */
    public String getCrseCode() {
        return crseCode;
    }

    /**
     * get the course level.
     *
     * @return course level
     */
    public String getCrseLevel() {
        return crseLevel;
    }

    /**
     * get the session the course was taken. Format yyyyMM
     *
     * @return session date
     */
    public String getCrseSession() {
        return crseSession;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.studNo);
        hash = 47 * hash + Objects.hashCode(this.crseCode);
        hash = 47 * hash + Objects.hashCode(this.crseLevel);
        hash = 47 * hash + Objects.hashCode(this.crseSession);
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
        final CourseId other = (CourseId) obj;
        if (!Objects.equals(this.studNo, other.studNo)) {
            return false;
        }
        if (!Objects.equals(this.crseCode, other.crseCode)) {
            return false;
        }
        if (!Objects.equals(this.crseLevel, other.crseLevel)) {
            return false;
        }
        return Objects.equals(this.crseSession, other.crseSession);
    }
}
