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
package ca.bc.gov.educ.isd.traxadaptor.impl;

import ca.bc.gov.educ.isd.eis.trax.db.ScholarshipStudent;
import ca.bc.gov.educ.isd.eis.trax.db.StudentMaster;
import ca.bc.gov.educ.isd.eis.trax.db.TabSchool;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.trimSafe;

/**
 * A TRAX data object containing data from the TRAX database for the given
 * student PEN. The data is student information related to scholarships
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ScholarshipStudentImpl implements ScholarshipStudent, Serializable {

    private static final long serialVersionUID = 3L;

    /**
     * Personalized education number.
     */
    private String pen;

    /**
     * Default birthdate is today.
     */
    private Date birthdate;

    // Student's full name
    private String firstName;
    private String middleName;
    private String lastName;

    // Student address
    // TODO: Use common address class
    private String studentAddress1;
    private String studentAddress2;
    private String studentCity;
    private String studentProv;
    private String studentPostalCode;

    // School address
    // TODO: Use common address class
    private String schoolStreet;
    private String schoolStreet2;
    private String schoolCity;
    private String schoolPostalCode;
    private String schoolProv;

    /**
     * Ministry code.
     */
    private String mincode;

    /**
     * Full school name.
     */
    private String schoolName;

    /**
     * Default (empty) constructor.
     */
    public ScholarshipStudentImpl() {
    }

    /**
     * Constructs new instance with given student record and school data.
     *
     * @param student Student record read from a data source.
     * @param school School record read from a data source.
     */
    public ScholarshipStudentImpl(
            final StudentMaster student,
            final TabSchool school) {
        this.pen = trimSafe(student.getStudNo());
        this.birthdate = student.getStudBirth();
        this.firstName = trimSafe(student.getStudGiven());
        this.middleName = trimSafe(student.getStudMiddle());
        this.lastName = trimSafe(student.getStudSurname());
        this.studentAddress1 = trimSafe(student.getAddress1());
        this.studentAddress2 = trimSafe(student.getAddress2());
        this.studentCity = trimSafe(student.getCity());
        this.studentProv = trimSafe(student.getProvCode());
        this.studentPostalCode = trimSafe(student.getPostal());
        this.mincode = trimSafe(student.getMincode());
        this.schoolName = trimSafe(school.getSchlName());
        this.schoolStreet = trimSafe(school.getAddress1());
        this.schoolStreet2 = trimSafe(school.getAddress2());
        this.schoolCity = trimSafe(school.getCity());
        this.schoolProv = trimSafe(school.getProvCode());
        this.schoolPostalCode = trimSafe(school.getPostal());
    }

    /**
     * JPQL constructor to create a new unmanaged object with fields queried
     * from multiple managed entities.
     *
     * @param pen Unique identifier.
     * @param birthdate Day the student was born.
     * @param firstName Student's given name.
     * @param middleName Student's given middle name(s).
     * @param lastName Student's surname.
     * @param studentAddress1 Required first line.
     * @param studentAddress2 Optional second line.
     * @param studentCity Required city.
     * @param studentProv
     * @param studentPostalCode
     * @param mincode School's Ministry code.
     * @param schoolName
     * @param schoolStreet
     * @param schoolStreet2
     * @param schoolCity
     * @param schoolProv
     * @param schoolPostalCode
     * @deprecated Construct with student master entity and tab school entity.
     */
    public ScholarshipStudentImpl(
            final String pen,
            final Date birthdate,
            final String firstName,
            final String middleName,
            final String lastName,
            final String studentAddress1,
            final String studentAddress2,
            final String studentCity,
            final String studentProv,
            final String studentPostalCode,
            final String mincode,
            final String schoolName,
            final String schoolStreet,
            final String schoolStreet2,
            final String schoolCity,
            final String schoolProv,
            final String schoolPostalCode) {
        this.pen = pen;
        this.birthdate = birthdate;
        this.firstName = trimSafe(firstName);
        this.middleName = trimSafe(middleName);
        this.lastName = trimSafe(lastName);
        this.studentAddress1 = trimSafe(studentAddress1);
        this.studentAddress2 = trimSafe(studentAddress2);
        this.studentCity = trimSafe(studentCity);
        this.studentProv = trimSafe(studentProv);
        this.studentPostalCode = trimSafe(studentPostalCode);
        this.mincode = trimSafe(mincode);
        this.schoolName = trimSafe(schoolName);
        this.schoolStreet = trimSafe(schoolStreet);
        this.schoolStreet2 = trimSafe(schoolStreet2);
        this.schoolCity = trimSafe(schoolCity);
        this.schoolProv = trimSafe(schoolProv);
        this.schoolPostalCode = trimSafe(schoolPostalCode);
    }

    /**
     * get the PEN which the data corresponds to.
     *
     * @return PEN
     */
    @Override
    public String getPen() {
        return pen;
    }

    /**
     * get the student's birthdate
     *
     * @return
     */
    @Override
    public Date getBirthdate() {
        return this.birthdate;
    }

    /**
     * get the student's first name.
     *
     * @return first name
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * get the student's middle name.
     *
     * @return middle name
     */
    @Override
    public String getMiddleName() {
        return middleName;
    }

    /**
     * get the student's last name.
     *
     * @return last name
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * get the student's address field #1.
     *
     * @return first student address field
     */
    @Override
    public String getStudentAddress1() {
        return studentAddress1;
    }

    /**
     * get the student's address field #2.
     *
     * @return second student address field
     */
    @Override
    public String getStudentAddress2() {
        return studentAddress2;
    }

    /**
     * get the student's address city.
     *
     * @return address city
     */
    @Override
    public String getStudentCity() {
        return studentCity;
    }

    /**
     * get the student's address province name.
     *
     * @return address province
     */
    @Override
    public String getStudentProv() {
        return studentProv;
    }

    /**
     * get the student address postal code.
     *
     * @return postal code
     */
    @Override
    public String getStudentPostalCode() {
        return studentPostalCode;
    }

    /**
     * get the secondary school name.
     *
     * @return school name
     */
    @Override
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * get the secondary school street address.
     *
     * @return school street address
     */
    @Override
    public String getSchoolStreet() {
        return schoolStreet;
    }

    /**
     * get the secondary school city.
     *
     * @return school city
     */
    @Override
    public String getSchoolCity() {
        return schoolCity;
    }

    /**
     * get the secondary school postal code.
     *
     * @return postal code
     */
    @Override
    public String getSchoolPostalCode() {
        return schoolPostalCode;
    }

    /**
     * get the secondary school province name.
     *
     * @return province
     */
    @Override
    public String getSchoolProv() {
        return schoolProv;
    }

    /**
     * get the ministry reference code identifying the school.
     *
     * @return
     */
    @Override
    public String getMincode() {
        return mincode;
    }

    /**
     * get the school street address (second line).
     *
     * @return
     */
    @Override
    public String getSchoolStreet2() {
        return schoolStreet2;
    }

    /**
     * calculate the unique hashcode for this object using the PEN, first name,
     * last name, and school's ministry code.
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.pen);
        hash = 67 * hash + Objects.hashCode(this.firstName);
        hash = 67 * hash + Objects.hashCode(this.lastName);
        hash = 67 * hash + Objects.hashCode(this.mincode);
        return hash;
    }

    /**
     * compare two ScholarshipStudentImpl objects for equality based on the PEN,
     * first name, last name, and school's ministry code.
     *
     * @param obj
     * @return True if the objects are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ScholarshipStudentImpl other = (ScholarshipStudentImpl) obj;
        if (!Objects.equals(this.pen, other.pen)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return Objects.equals(this.mincode, other.mincode);
    }
}
