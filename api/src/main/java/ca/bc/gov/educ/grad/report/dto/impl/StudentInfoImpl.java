/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education and Child Care, BC.
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
package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.graduation.OtherProgram;
import ca.bc.gov.educ.grad.report.model.student.StudentInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.model.common.Constants.DATE_TRAX_YMD;
import static ca.bc.gov.educ.grad.report.model.common.support.VerifyUtils.asBoolean;
import static ca.bc.gov.educ.grad.report.model.common.support.VerifyUtils.trimSafe;

/**
 * A TRAX data object containing data from the TRAX database for the given
 * student PEN. The data provides student information
 *
 * @author CGI Information Management Consultants Inc.
 */

public class StudentInfoImpl implements StudentInfo {

    private static final long serialVersionUID = 5L;

    private static final String CLASSNAME = StudentInfoImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    // basic student info
    private String pen;
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private Date birthdate = new Date();

    // student transcript info
    private String schoolId = "";
    private Date reportDate = new Date(0L);
    private Date lastUpdateDate = new Date(0L);
    private String logo = "";
    private String gender = "";
    private String status = "";
    private Boolean honourFlag = Boolean.FALSE;
    private Boolean dogwoodFlag = Boolean.FALSE;
    private String grade = "";
    private Date gradDate = new Date(0L);
    private String gradProgram = "";
    private String gradReqYear = "";
    private final List<String> academicProgram = new ArrayList<>();
    private Map<String, String> nonGradReasons = new HashMap<>();
    private String gradMessage = "";

    private String localId = "";
    private String hasOtherProgram = "";
    private List<OtherProgram> otherProgramParticipation = new ArrayList<>();

    // student address
    private String studentAddress1 = "";
    private String studentAddress2 = "";
    private String studentCity = "";
    private String studentProv = "";
    private String studentPostalCode = "";
    private String traxStudentCountry = "";
    private String isoStudentCountry;

    // school information
    private String mincode = "";
    private String schoolName = "";
    private String schoolStreet = "";
    private String schoolStreet2 = "";
    private String schoolCity = "";
    private String schoolPostalCode = "";
    private String schoolProv = "";
    private String schoolPhone = "";
    private String schoolTypeIndicator = "";
    private String schoolTypeBanner = "";

    public StudentInfoImpl() {
    }

    /**
     * JPQL constructor for the student information related to transcripts.
     * @param studNo
     * @param firstName
     * @param middleName
     * @param lastName
     * @param birthdate
     * @param localId
     * @param studGender
     * @param mincode
     * @param studGrade
     * @param gradDate
     * @param gradReqtYear
     * @param gradMessage
     * @param updateDt
     * @param logoType
     * @param studentAddress1
     * @param studentAddress2
     * @param studentCity
     * @param studentProv
     * @param studentPostalCode
     * @param traxStudentCountry
     * @param studStatus
     * @param honourFlag
     * @param dogwoodFlag
     * @param prgmCode
     * @param prgmCode2
     * @param prgmCode3
     * @param prgmCode4
     * @param prgmCode5
     * @param schoolName
     * @param schoolStreet
     * @param schoolStreet2
     * @param schoolCity
     * @param schoolProv
     * @param schoolPostalCode
     * @param schoolPhone
     * @param schlIndType
     */
    public StudentInfoImpl(
            final String studNo,
            final String firstName,
            final String middleName,
            final String lastName,
            final Date birthdate,
            final String localId,
            final String studGender,
            final String mincode,
            final String studGrade,
            final Date gradDate,
            final String gradProgram,
            final String gradReqYear,
            final String gradMessage,
            final Date updateDt,
            final String logoType,
            final String studentAddress1,
            final String studentAddress2,
            final String studentCity,
            final String studentProv,
            final String studentPostalCode,
            final String traxStudentCountry,
            final String studStatus,
            final Character honourFlag,
            final Character dogwoodFlag,
            final String prgmCode,
            final String prgmCode2,
            final String prgmCode3,
            final String prgmCode4,
            final String prgmCode5,
            final String schoolName,
            final String schoolStreet,
            final String schoolStreet2,
            final String schoolCity,
            final String schoolProv,
            final String schoolPostalCode,
            final String schoolPhone,
            final String schlIndType) {

        this.pen = trimSafe(studNo);
        this.firstName = trimSafe(firstName);
        this.middleName = trimSafe(middleName);
        this.lastName = trimSafe(lastName);
        this.birthdate = birthdate;
        this.localId = trimSafe(localId);
        this.gender = studGender;
        this.mincode = trimSafe(mincode);
        this.grade = trimSafe(studGrade);
        this.gradDate = gradDate;
        this.gradProgram = trimSafe(gradProgram);
        this.gradReqYear = trimSafe(gradReqYear);
        this.gradMessage = trimSafe(gradMessage);
        this.reportDate = updateDt;
        this.logo = trimSafe(logoType);
        this.studentAddress1 = trimSafe(studentAddress1);
        this.studentAddress2 = trimSafe(studentAddress2);
        this.studentCity = trimSafe(studentCity);
        this.studentProv = trimSafe(studentProv);
        this.studentPostalCode = trimSafe(studentPostalCode);
        this.status = studStatus;

        this.honourFlag = asBoolean(honourFlag);
        this.dogwoodFlag = asBoolean(dogwoodFlag);

        this.schoolName = trimSafe(schoolName);
        this.schoolStreet = trimSafe(schoolStreet);
        this.schoolStreet2 = trimSafe(schoolStreet2);
        this.schoolCity = trimSafe(schoolCity);
        this.schoolProv = trimSafe(schoolProv);
        this.schoolPostalCode = trimSafe(schoolPostalCode);
        this.schoolPhone = trimSafe(schoolPhone);
        this.schoolTypeIndicator = trimSafe(schlIndType);

        //setSchoolTypeBanner();

        this.academicProgram.add(trimSafe(prgmCode));
        this.academicProgram.add(trimSafe(prgmCode2));
        this.academicProgram.add(trimSafe(prgmCode3));
        this.academicProgram.add(trimSafe(prgmCode4));
        this.academicProgram.add(trimSafe(prgmCode5));
        this.traxStudentCountry = trimSafe(traxStudentCountry);
    }

    @Override
    public String getPen() {
        return this.pen;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getMiddleName() {
        return this.middleName == null ? "" : this.middleName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getBirthdate() {
        return this.birthdate;
    }

    @Override
    public String getStudentAddress1() {
        return this.studentAddress1;
    }

    @Override
    public String getStudentAddress2() {
        return this.studentAddress2;
    }

    @Override
    public String getStudentCity() {
        return this.studentCity;
    }

    @Override
    public String getStudentProv() {
        return this.studentProv;
    }

    @Override
    public String getStudentPostalCode() {
        return this.studentPostalCode;
    }

    @Override
    public String getStudentStatus() {
        return this.status;
    }

    @Override
    public String getGender() {
        return this.gender;
    }

    @Override
    public Boolean isHonourFlag() {
        return this.honourFlag;
    }

    @Override
    public Boolean isDogwoodFlag() {
        return this.dogwoodFlag;
    }

    @Override
    public String getGrade() {
        return this.grade;
    }

    @Override
    public String getGradProgram() {
        return this.gradProgram;
    }

    public void setGradProgram(String gradProgram) {
        this.gradProgram = gradProgram;
    }

    @Override
    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) { this.localId = localId; }

    @Override
    public List<OtherProgram> getOtherProgramParticipation() {
        return otherProgramParticipation;
    }

    @Override
    public String getHasOtherProgram() {
        return this.hasOtherProgram;
    }

    @Override
    public String getMincode() {
        return this.mincode;
    }

    @Override
    public String getSchoolProv() {
        return this.schoolProv;
    }

    @Override
    public String getSchoolPhone() {
        return this.schoolPhone;
    }

    @Override
    public Map<String, String> getNonGradReasons() {
        return this.nonGradReasons;
    }

    /**
     * set the reason why the student failed to graduate.
     *
     * @param reasons
     */
    public void setNonGradReasons(final Map<String, String> reasons) {
        this.nonGradReasons = reasons;
    }

    @Override
    public Date getGradDate() {
        return this.gradDate;
    }

    @Override
    public String getSchoolName() {
        return this.schoolName;
    }

    @Override
    public String getSchoolStreet() {
        return this.schoolStreet;
    }

    @Override
    public String getSchoolCity() {
        return this.schoolCity;
    }

    @Override
    public String getSchoolPostalCode() {
        return this.schoolPostalCode;
    }

    @Override
    public Date getReportDate() {
        return this.reportDate;
    }

    @Override
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    public void setLastUpdateDate(final Date lastUpdated) {
        this.lastUpdateDate = lastUpdated;
    }

    @Override
    public String getLogo() {
        return this.logo;
    }

    @Override
    public String getSchoolId() {
        return this.schoolId;
    }

    @Override
    public List<String> getAcademicProgram() {
        return this.academicProgram;
    }

    @Override
    public String getSchoolTypeIndicator() {
        return this.schoolTypeIndicator;
    }

    @Override
    public String getSchoolTypeBanner() {
        return this.schoolTypeBanner;
    }

    public void setHasOtherProgram(String hasOtherProgram) {
        this.hasOtherProgram = hasOtherProgram;
    }

    public void setOtherProgramParticipation(List<OtherProgram> otherProgramParticipation) {
        this.otherProgramParticipation = otherProgramParticipation;
    }

    /**
     * set the school type banner.
     *
     */
    private void setSchoolTypeBanner() {
        switch (getSchoolTypeIndicator()) {
            case "1":
                this.schoolTypeBanner = "B.C. INDEPENDENT SCHOOLS - GROUP 1";
                break;
            case "2":
                this.schoolTypeBanner = "B.C. INDEPENDENT SCHOOLS - GROUP 2";
                break;
            case "4":
                this.schoolTypeBanner = "B.C. INDEPENDENT SCHOOLS - GROUP 4";
                break;
            default:
                this.schoolTypeBanner = "";
        }
    }

    @Override
    public String getSchoolStreet2() {
        return schoolStreet2;
    }

    @Override
    public String getGradMessage() {
        return gradMessage;
    }

    @Override
    public String getCountryCode() {
        return isoStudentCountry;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.pen);
        hash = 13 * hash + Objects.hashCode(this.firstName);
        hash = 13 * hash + Objects.hashCode(this.lastName);
        hash = 13 * hash + Objects.hashCode(this.birthdate);
        hash = 13 * hash + Objects.hashCode(this.schoolId);
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
        final StudentInfoImpl other = (StudentInfoImpl) obj;
        if (!(this.pen.equals(other.pen))) {
            return false;
        }
        if (!(this.firstName.equals(other.firstName))) {
            return false;
        }
        if (!(this.lastName.equals(other.lastName))) {
            return false;
        }
        if (!(this.birthdate.getTime() == other.birthdate.getTime())) {
            return false;
        }
        return this.schoolId.equals(other.schoolId);
    }

    /**
     * Creates a date instance from a date in DATE_TRAX_YMD format.
     *
     * @param traxDate The TRAX date to convert to a string before parsing.
     * @return The given TRAX date as a Date instance.
     */
    private Date createDate(final Long traxDate) {
        return traxDate == null ? new Date() : createDate(traxDate.toString());
    }

    /**
     * Creates a date instance from a date in DATE_TRAX_YMD format.
     *
     * @param traxDate The date in DATE_TRAX_YMD format.
     * @return A new date instance.
     */
    private Date createDate(final String traxDate) {
        return createDate(traxDate, DATE_TRAX_YMD);
    }

    /**
     * Create a Date data type from the given String value using the specified
     * pattern.
     *
     * @param d The unparsed date to convert into a Date instance.
     * @param pattern The date/time pattern to use for parsing the date.
     * @return The given formatted date value as a date instance.
     */
    private Date createDate(final String d, final String pattern) {
        Date date = new Date();

        try {
            if (d != null && !d.isEmpty() && !"0".equals(d)) {
                final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                date = sdf.parse(d);
            }
        } catch (final ParseException e) {
            date = new Date();
            LOG.log(Level.WARNING,
                    "Failed to parse date {0} using {1}",
                    new Object[]{d, pattern});
        }

        return date;
    }

    public JRBeanCollectionDataSource getOtherProgramParticipationdataSource() {
        return new JRBeanCollectionDataSource(otherProgramParticipation, false);
    }

    @Override
    public String getGradReqYear() {
        return this.gradReqYear;
    }
}
