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
package ca.bc.gov.educ.grad.report.dto.reports.data.impl;

import ca.bc.gov.educ.grad.report.dto.reports.data.BusinessEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static ca.bc.gov.educ.grad.report.dto.reports.data.impl.AcademicProgramNames.getAcademicProgramName;

/**
 * Represents a students academic information towards completing graduation
 * requirements based on their graduation program.
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlRootElement(name = "academicAward")
@XmlAccessorType(XmlAccessType.FIELD)
public final class AcademicAward extends BusinessEntity implements Serializable {

    private static final long serialVersionUID = 2L;

    /**
     * When the student graduated.
     */
    private String graduationDate;

    /**
     * If the student graduated with honours.
     */
    @XmlElement(name = "honorsTitle")
    private Boolean honorsFlag;

    /**
     * If the student's record indicates a dogwood flag.
     */
    private Boolean dogwoodFlag;

    /**
     * Total credits used for graduation
     */
    @XmlElement(name = "creditHoursEarned")
    private String totalCreditsUFG;

    /**
     * Total credits required for graduation.
     */
    @XmlElement(name = "creditHoursRequired")
    private String totalCreditsRequired = "";

    /**
     * Academic program codes.
     */
    @XmlElement(name = "academicProgramCode")
    private List<String> programCodes;

    /**
     * Academic program names.
     */
    @XmlElement(name = "academicProgramName")
    private List<String> programNames;

    /**
     * Returns a student's graduation date.
     *
     * @return A string, possibly empty, that contains a student's grade.
     */
    public synchronized String getGraduationDate() {
        return nullSafe(this.graduationDate);
    }

    /**
     * Answers whether student graduated with honours.
     *
     * @return true - The student graduation with honours.
     */
    public Boolean getHonorsFlag() {
        return honorsFlag;
    }

    /**
     * Returns the total credits used for graduation.
     *
     * @return Total credits used for graduation.
     */
    public String getTotalCreditsUFG() {
        return totalCreditsUFG;
    }

    /**
     * Returns if student graduated with dogwood certificate status.
     *
     * @return true - The student has a certificate with a dogwood flag.
     */
    public Boolean getDogwoodFlag() {
        return dogwoodFlag;
    }

    /**
     * Returns a list of the student's program codes
     *
     * @return A list of codes.
     */
    public List<String> getProgramCodes() {
        return programCodes;
    }

    /**
     * Returns a list of the student's program names
     *
     * @return A list of names.
     */
    public List<String> getProgramNames() {
        return programNames;
    }

    /**
     * Returns the total credits required for the student's graduation program.
     *
     * @return
     */
    public String getTotalCreditsRequired() {
        return totalCreditsRequired;
    }

    /**
     * Used by the builder to set the graduation date of the student.
     *
     * @param graduationDate Passed in by the builder.
     */
    protected synchronized void setGraduationDate(final String graduationDate) {
        this.graduationDate = graduationDate;
    }

    /**
     * Used by the builder to set the honours flag of the student.
     *
     * @param honorsFlag Passed in by the builder.
     */
    public void setHonorsFlag(Boolean honorsFlag) {
        this.honorsFlag = honorsFlag;
    }

    /**
     * Used by the builder to set the total credits used for graduation.
     *
     * @param totalCreditsUFG
     */
    public void setTotalCreditsUFG(String totalCreditsUFG) {
        this.totalCreditsUFG = totalCreditsUFG;
    }

    /**
     * Used by the builder to set the dogwood flag of the student.
     *
     * @param dogwoodFlag Passed in by the builder.
     */
    public void setDogwoodFlag(Boolean dogwoodFlag) {
        this.dogwoodFlag = dogwoodFlag;
    }

    /**
     * Used by the builder to set the program codes.
     *
     * @param programCodes Passed in by the builder.
     */
    public void setProgramCodes(final List<String> programCodes) {
        this.programCodes = programCodes;
    }

    /**
     * Used to set the program names for the list of set codes.
     *
     * @param programCodes To retrieve the codes with.
     */
    private void setProgramNames(final List<String> programCodes) {
        final List<String> list = new ArrayList<>();

        for (String code : programCodes) {
            code = code.replaceAll("^[\\d]+", "");
            if (!code.equals("")) {
                final String academicProgramName = getAcademicProgramName(code);
                list.add(academicProgramName);
            }
        }
        this.programNames = list;
    }

    /**
     * Set the credit hours required based on the graduation program.
     *
     * @param graduationProgram The graduation program of the student.
     */
    private void setTotalCreditsRequired(final GraduationProgram graduationProgram) {
        setTotalCreditsRequired(Integer.toString(graduationProgram.getCredits()));
    }

    public void setTotalCreditsRequired(final String totalCreditsRequired) {
        this.totalCreditsRequired = totalCreditsRequired;
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder
            extends BusinessEntity.Builder<AcademicAward, Builder> {

        /**
         * Sets the graduation date of a student.
         *
         * @param graduationDate A student's grade level.
         * @return thisBuilder
         */
        public AcademicAward.Builder withGraduationDate(final String graduationDate) {
            getObject().setGraduationDate(graduationDate);
            return thisBuilder();
        }

        /**
         * Sets the honours flag of a student.
         *
         * @param honoursFlag A student's honours flag.
         * @return thisBuilder
         */
        public AcademicAward.Builder withHonoursFlag(final Boolean honoursFlag) {
            getObject().setHonorsFlag(honoursFlag);
            return thisBuilder();
        }

        /**
         * Sets the dogwood flag of a student.
         *
         * @param dogwoodFlag A student's dogwood flag.
         * @return thisBuilder
         */
        public AcademicAward.Builder withDogwoodFlag(final Boolean dogwoodFlag) {
            getObject().setDogwoodFlag(dogwoodFlag);
            return thisBuilder();
        }

        /**
         * Sets the total credits used for graduation
         *
         * @param totalCreditsUFG Total credits used for graduation.
         * @param graduationProgram
         * @return thisBuilder
         */
        public AcademicAward.Builder withTotalCredUFG(
                final String totalCreditsUFG,
                final GraduationProgram graduationProgram) {
            getObject().setTotalCreditsUFG(totalCreditsUFG);
            getObject().setTotalCreditsRequired(graduationProgram);
            return thisBuilder();
        }

        /**
         * TODO: Add generic type to List, add import for java.util.List.
         *
         * Sets the program names of a student.
         *
         * @param programCodes A student's program codes.
         * @return thisBuilder
         */
        public AcademicAward.Builder withProgramCodeNames(final List<String> programCodes) {
            getObject().setProgramNames(programCodes);
            return thisBuilder();
        }

        /**
         * Returns an outer class instance without attributes initialized.
         *
         * @return New AcademicAward instance.
         */
        @Override
        protected AcademicAward createObject() {
            return new AcademicAward();
        }

        /**
         * Returns the builder used to construct outer class instances.
         *
         * @return this
         */
        @Override
        protected Builder thisBuilder() {
            return this;
        }
    }
}
