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
 *  File:                AcademicSessionDetail.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.dto.reports.data.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * TODO: Comment how this class is used.
 *
 * @author CGI Information Management Consultants
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AcademicSessionDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sessionDesignator;
    private String sessionName;
    private String sessionSchoolYear;

    public String getSessionDesignator() {
        return sessionDesignator;
    }

    public void setSessionDesignator(final String sessionDesignator) {
        this.sessionDesignator = sessionDesignator;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(final String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionSchoolYear() {
        return sessionSchoolYear;
    }

    public void setSessionSchoolYear(final String sessionSchoolYear) {
        this.sessionSchoolYear = sessionSchoolYear;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "sessionDesignator=" + sessionDesignator + ", sessionName=" + sessionName + ", sessionSchoolYear=" + sessionSchoolYear + '}';
    }
}
