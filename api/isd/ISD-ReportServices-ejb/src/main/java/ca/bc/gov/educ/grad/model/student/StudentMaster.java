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
package ca.bc.gov.educ.grad.model.student;

import java.util.Date;

/**
 * Represents the student master entity attributes that are used by
 * StudentDemographicImpl.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentMaster {

    /**
     * Alias for getStudNo().
     *
     * @return getStudNo().
     */
    public String getPen();

    /**
     * @deprecated Use getPen()
     * @return Student's personal education number.
     */
    public String getStudNo();

    public String getStudGiven();

    public String getStudMiddle();

    public String getStudSurname();

    /**
     * This will return the student's birthdate or DATE_UNKNOWN_BIRTHDATE (Jan
     * 1, 1900) in the event that there was no birthdate or the value couldn't
     * be parsed.
     *
     * @return The student's birthdate or Jan 1, 1900.
     */
    public Date getStudBirth();

    public String getAddress1();

    public String getAddress2();

    public String getCity();

    public String getProvCode();

    public String getCountryCode();

    public String getPostal();

    public String getStudStatus();

    public String getStudGrade();

    public Date getGradDate();

    public String getGradReqtYear();

    public Character getHonourFlag();

    public Character getDogwoodFlag();

    public Date getSccDate();

    public String getMincode();

    public String getMincodeGrad();

    public String getPrgmCode();

    public String getPrgmCode2();

    public String getPrgmCode3();

    public String getPrgmCode4();

    public String getPrgmCode5();

    public String getEnglishCert();

    public String getFrenchCert();

    public String getStud_true_no();
}
