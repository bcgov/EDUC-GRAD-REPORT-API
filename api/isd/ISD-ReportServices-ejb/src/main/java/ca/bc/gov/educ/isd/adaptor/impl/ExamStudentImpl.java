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
package ca.bc.gov.educ.isd.adaptor.impl;

import ca.bc.gov.educ.isd.eis.trax.db.ExamStudent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.trimSafe;
import static ca.bc.gov.educ.isd.eis.trax.Constants.DATE_TRAX_YMD;

/**
 * A TRAX data object containing data from the TRAX database for the given
 * student PEN. The data is student information associated with exam results
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ExamStudentImpl implements ExamStudent {

    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = ExamStudentImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private String pen;

    // basic student info
    private String firstName = "";
    private String lastName = "";
    private String middleName = "";
    private Date birthdate;

    // student exam info
    private Date lastUpdated = new Date(0L);
    private Date reportDate = new Date(0L);
    private String logo = "";

    // school info
    private String mincode = "";
    private String schoolName = "";

    public ExamStudentImpl() {
    }

    /**
     * Constructor method. Used by the JPQL to create an object from the
     * database entities.
     *
     * @param pen
     * @param firstName
     * @param lastName
     * @param middleName
     * @param traxBirthdate
     * @param updateDt
     * @param logoType
     * @param mincode
     * @param schoolName
     */
    public ExamStudentImpl(
            final String pen,
            final String firstName,
            final String lastName,
            final String middleName,
            final Long traxBirthdate,
            final Long updateDt,
            final String logoType,
            final String mincode,
            final String schoolName) {

        this.pen = pen;
        this.firstName = trimSafe(firstName);
        this.lastName = trimSafe(lastName);
        this.middleName = trimSafe(middleName);
        this.logo = trimSafe(logoType);
        final String updated = (updateDt == null) ? "" : updateDt.toString();
        final String born = (traxBirthdate == null) ? "" : traxBirthdate.toString();

        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_TRAX_YMD);
        Date birthday;
        try {
            birthday = sdf.parse(born);
        } catch (final ParseException e) {
            LOG.log(Level.WARNING, "Failed to parse date: {0}", born);
            birthday = new Date();
        }
        this.birthdate = birthday;
        Date aDate;
        try {
            aDate = sdf.parse(updated);
        } catch (final ParseException e) {
            LOG.log(Level.WARNING, "Failed to parse date: {0}", updated);
            aDate = new Date(0L);
        }
        this.lastUpdated = aDate;
        this.reportDate = aDate;
        this.mincode = (mincode == null) ? "" : mincode;
        this.schoolName = (schoolName == null) ? "" : schoolName.trim();
    }

    @Override
    public String getPen() {
        return pen;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public Date getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public Date getReportDate() {
        return reportDate;
    }

    @Override
    public String getLogo() {
        return logo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (this.pen != null ? this.pen.hashCode() : 0);
        hash = 11 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 11 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
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
        final ExamStudentImpl other = (ExamStudentImpl) obj;
        if ((this.pen == null) ? (other.pen != null) : !this.pen.equals(other.pen)) {
            return false;
        }
        if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
            return false;
        }
        return !((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName));
    }

    @Override
    public String getMincode() {
        return this.mincode;
    }

    @Override
    public String getSchoolName() {
        return this.schoolName;
    }

    @Override
    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

}
