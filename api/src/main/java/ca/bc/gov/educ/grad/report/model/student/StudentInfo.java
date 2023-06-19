/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
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
package ca.bc.gov.educ.grad.report.model.student;

import ca.bc.gov.educ.grad.report.model.graduation.OtherProgram;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Defines the public methods to access the Student information used by the
 * transcript services
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentInfo {

    /**
     * get the PEN which the data corresponds to.
     *
     * @return PEN pen
     */
    String getPen();

    /**
     * get the student's first name.
     *
     * @return first name
     */
    String getFirstName();

    /**
     * get the student's middle name.
     *
     * @return middle name
     */
    String getMiddleName();

    /**
     * get the student's last name.
     *
     * @return last name
     */
    String getLastName();

    /**
     * get the student's date of birth. Format YYYYMMDD.
     *
     * @return date of birth
     */
    LocalDate getBirthdate();

    /**
     * get the student's address field #1.
     *
     * @return first student address field
     */
    String getStudentAddress1();

    /**
     * get the student's address field #2.
     *
     * @return second student address field
     */
    String getStudentAddress2();

    /**
     * get the student's address city.
     *
     * @return address city
     */
    String getStudentCity();

    /**
     * get the student's address province name.
     *
     * @return address province
     */
    String getStudentProv();

    /**
     * get the student's living status.
     *
     * @return status student status
     */
    String getStudentStatus();

    /**
     * get the student gender.
     *
     * @return M for male or F for female
     */
    String getGender();

    /**
     * get the student citizenship.
     *
     * @return citiz
     */
    String getCitizenship();

    /**
     * Gets local id.
     *
     * @return the local id
     */
    String getLocalId();

    /**
     * Gets other program participation.
     *
     * @return the other program participation
     */
    List<OtherProgram> getOtherProgramParticipation();

    /**
     * Gets has other program.
     *
     * @return the has other program
     */
    String getHasOtherProgram();

    /**
     * get the student honours title.
     *
     * @return True if student is an honours student
     */
    Boolean isHonourFlag();

    /**
     * get the students completion indicator.
     *
     * @return True if student completed the requirements for a diploma
     */
    Boolean isDogwoodFlag();

    /**
     * get the student's grade.
     *
     * @return grade grade
     */
    String getGrade();

    /**
     * get the student's grade date. Format YYYYMM
     *
     * @return graduation date
     */
    Date getGradDate();

    /**
     * get the graduation program the student is associated with. This outlines
     * what requirements are used to determine graduation requirements.
     *
     * @return graduation requirements year
     */
    String getGradProgram();

    /**
     * get the code reference for the student's secondary school.
     *
     * @return ministry school code
     */
    String getMincode();

    /**
     * get the secondary school name.
     *
     * @return school name
     */
    String getSchoolName();

    /**
     * get the secondary school street address.
     *
     * @return school street address
     */
    String getSchoolStreet();

    /**
     * get the secondary school street address field #2.
     *
     * @return school street 2
     */
    String getSchoolStreet2();

    /**
     * get the secondary school city.
     *
     * @return school city
     */
    String getSchoolCity();

    /**
     * get the secondary school postal code.
     *
     * @return postal code
     */
    String getSchoolPostalCode();

    /**
     * get the secondary school province name.
     *
     * @return province school prov
     */
    String getSchoolProv();

    /**
     * get the secondary school phone number.
     *
     * @return school phone number, unformatted
     */
    String getSchoolPhone();

    /**
     * get the reasons why the student has not met the graduation requirements.
     *
     * @return reasons for not graduating
     */
    Map<String, String> getNonGradReasons();

    /**
     * get the date when the UTG record was last updated.
     *
     * @return date UTG last updated
     */
    Date getLastUpdateDate();

    /**
     * get the display logo.
     *
     * @return logo ; values are BC or YU
     */
    String getLogo();

    /**
     * get the date when the UTG record was last issued.
     *
     * @return date UTG last issued
     */
    Date getReportDate();

    /**
     * get the student address postal code.
     *
     * @return postal code
     */
    String getStudentPostalCode();

    /**
     * get the school assigned student ID.
     *
     * @return students school ID
     */
    String getSchoolId();

    /**
     * get the list of academic program name fields.
     *
     * @return List of academic program name fields
     */
    List<String> getAcademicProgram();

    /**
     * Used to indicate whether this is an independent school.
     *
     * @return Blank or digits 1 - 6, never null, no space.
     */
    String getSchoolTypeIndicator();

    /**
     * get school type banner. Value depends on school type.
     *
     * @return school type banner
     */
    String getSchoolTypeBanner();

    /**
     * get the graduation message.
     * <p>
     *
     * @return grad message
     */
    String getGradMessage();

    /**
     * get the student address ISO country code.
     *
     * @return country code
     */
    String getCountryCode();

    /**
     * Gets other program participationdata source.
     *
     * @return the other program participationdata source
     */
    JRBeanCollectionDataSource getOtherProgramParticipationdataSource();

    /**
     * Gets grad req year.
     *
     * @return the grad req year
     */
    String getGradReqYear();
}
