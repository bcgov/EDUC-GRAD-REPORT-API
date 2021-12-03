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
package ca.bc.gov.educ.grad.dto.impl;

import ca.bc.gov.educ.grad.model.cert.CertificateCategoryEnum;
import ca.bc.gov.educ.grad.model.graduation.GraduationProgramCode;
import ca.bc.gov.educ.grad.model.student.StudentDemographic;
import ca.bc.gov.educ.grad.model.student.StudentDetails;
import ca.bc.gov.educ.grad.model.student.StudentTypeEnum;

import java.io.Serializable;
import java.util.Date;

import static ca.bc.gov.educ.grad.model.graduation.GraduationProgramCode.PROGRAM_SCCP;
import static ca.bc.gov.educ.grad.model.graduation.GraduationProgramCode.valueFrom;
import static ca.bc.gov.educ.grad.model.student.MincodeEnum.CODE_098;
import static java.lang.Boolean.TRUE;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class StudentDetailsImpl implements StudentDetails, Serializable {

    //--------------------------- Constant(s)
    private static final long serialVersionUID = 3L;

    //--------------------------- Variable(s)
    private String pen;

    // basic student info
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private Date birthDate = null;

    // extended student info
    private Character status = ' ';
    private String grade = "";
    private String gradProgram = "";
    private Boolean transcriptEligible = Boolean.FALSE;
    private Character studentType = ' ';
    private Date sccDate;
    private String dogwoodFlag = "";

    /**
     * When not empty, this determines transcript eligibility.
     */
    private String mincode = "";

    /**
     * When not empty, this is used to determine the certificate.
     */
    private String gradMincode = "";

    /**
     * Determines certificate based on whether the school is independent.
     */
    private String schoolTypeIndicator = "";
    private String schoolCategory = "";

    private String schoolName = "";
    private String transEligibilityFlag = "";

    // graduation certificate info
    private Boolean certificateEligible = Boolean.FALSE;
    private String certificateCategory = "";
    private String englishCertificate = "";
    private String enCertType = "";
    private String enCertMedia = "";
    private String frenchCertificate = "";
    private String frCertType = "";
    private String frCertMedia = "";
    private Date certificateDate = null;
    private String certificateSignature = "";
    private String truePen = "";

    // Interface validation rules
    // STEP2
    private Boolean showSendInterim = evalSendInterim();
    private Boolean showSendFinalMarks = evalSendFinalMark();
    private Boolean showSendTransNow = evalSendTransNow();
    // DASHBOARD
    private Boolean ordersGraduationCERTDisable = Boolean.FALSE;
    private Boolean gradRequestYearEval = Boolean.FALSE;
    private Boolean studentStatusError = Boolean.FALSE;
    private Boolean isSCCP = Boolean.FALSE;
    private Boolean isAdult = Boolean.FALSE;

    //--------------------------- Constructor(s)
    /**
     * Default (empty) constructor.
     */
    public StudentDetailsImpl() {
    }

    public StudentDetailsImpl(final StudentDemographic student) {
        if (student != null) {
            pen = student.getPen();
            firstName = student.getFirstName();
            middleName = student.getMiddleName();
            lastName = student.getLastName();
            birthDate = student.getBirthDate();
            status = student.getStatus();
            grade = student.getGrade();
            gradProgram = student.getGradProgram();
            studentType = student.getStudentType();
            sccDate = student.getSccDate();
            mincode = student.getMincode();
            gradMincode = student.getGradMincode();

            // FIXME: Populate using the student's dogwood flag.
            dogwoodFlag = student.getDogwoodFlag();

            // Comes from the school data; this is false when dogwood != "Y",
            // gradDate == 0, or mincode == "098".
            certificateEligible = student.isPrintCertEligible();

            // Comes from the school data XCRIPT_ELIG flag (see checkSchool in
            // StudentDataBean).
            transcriptEligible = student.isTranscriptEligible();

            // Derived from ProvExamEntity and StudXcrseEntity and school's
            // isTranscriptEligible flag.
            transEligibilityFlag = student.getTransEligibilityFlag();

            // Derived from mincode or gradmincode school type indicator.
            schoolTypeIndicator = student.getSchoolTypeIndicator();

            // Derived from school.
            schoolCategory = student.getSchoolCategory();

            // Derived from school.
            schoolName = student.getSchoolName();

            certificateCategory = student.getCertificateCategory();
            englishCertificate = student.getEnglishCertificate();
            enCertType = student.getEnCertType();
            enCertMedia = student.getEnCertMedia();
            frenchCertificate = student.getFrenchCertificate();
            frCertType = student.getFrCertType();
            frCertMedia = student.getFrCertMedia();
            certificateDate = student.getCertificateDate();
            certificateSignature = student.getCertificateSignature();
            truePen = student.getTruePen();

            showSendInterim = evalSendInterim();
            showSendFinalMarks = evalSendFinalMark();
            showSendTransNow = evalSendTransNow();

            gradRequestYearEval = evalGradRequestYearEval();
            studentStatusError = evalStudentStatus();

            // FIXME: Can this be derived?
            isSCCP = evalIfSCCP();
            ordersGraduationCERTDisable = evalOrdGradCert();

            // FIXME: This can be derived.
            isAdult = student.isAdultProgram();
        }
    }

    //--------------------------- Method(s)
    private boolean evalSendInterim() {
        return !(studentType == StudentTypeEnum.FORMER.getValue().charAt(0) || (CertificateCategoryEnum.SCCP.getValue().equalsIgnoreCase(certificateCategory)));
    }

    private boolean evalSendFinalMark() {
        return evalSendInterim();
    }

    private boolean evalSendTransNow() {
        return this.studentType != 'F';
    }

    private boolean evalOrdGradCert() {
        boolean response = false;

        if (this.studentStatusError || !this.certificateEligible) {
            response = true;
        } else if (gradRequestYearEval) {
            if (this.certificateDate == null) {
                response = true;
            } else if (CODE_098.isCode(this.mincode)) {
                response = true;
            } else if (!CertificateCategoryEnum.DOGWOOD.getValue().equalsIgnoreCase(this.certificateCategory)) {
                response = true;
            }
        }

        return response;
    }

    private boolean evalGradRequestYearEval() {
        boolean response = false;

        if (this.gradProgram != null) {
            final GraduationProgramCode code = valueFrom(this.gradProgram);

            response = code != PROGRAM_SCCP;
        }

        return response;
    }

    private boolean evalIfSCCP() {
        // FIXME: Should this use the student's graduation program code,
        // rather than the certificate category?
        return CertificateCategoryEnum.SCCP.getValue().equalsIgnoreCase(certificateCategory);
    }

    private boolean evalStudentStatus() {
        return status == 'M' || status == 'D';
    }

    public boolean isCurrent() {
        Character stuType = getStudentType();
        return stuType != null && stuType == 'C';
    }

    private boolean showCurrentStudOptions(final boolean overwrite) {
        Boolean response = TRUE;

        if (isCurrent()) {
            response = overwrite;
        }
        return response;
    }

    @Override
    public String toString() {
        return "StudentDetailsImpl{"
                + "pen=" + pen
                + ", firstName=" + firstName
                + ", middleName=" + middleName
                + ", lastName=" + lastName
                + ", birthDate=" + birthDate
                + ", status=" + status
                + ", grade=" + grade
                + ", gradProgram=" + gradProgram
                + ", transcriptEligible=" + transcriptEligible
                + ", studentType=" + studentType
                + ", sccDate=" + sccDate
                + ", dogwoodFlag=" + dogwoodFlag
                + ", mincode=" + mincode
                + ", gradMincode=" + gradMincode
                + ", schoolTypeIndicator=" + schoolTypeIndicator
                + ", schoolCategory=" + schoolCategory
                + ", schoolName=" + schoolName
                + ", transEligibilityFlag=" + transEligibilityFlag
                + ", certificateEligible=" + certificateEligible
                + ", certificateCategory=" + certificateCategory
                + ", englishCertificate=" + englishCertificate
                + ", enCertType=" + enCertType
                + ", enCertMedia=" + enCertMedia
                + ", frenchCertificate=" + frenchCertificate
                + ", frCertType=" + frCertType
                + ", frCertMedia=" + frCertMedia
                + ", certificateDate=" + certificateDate
                + ", certificateSignature=" + certificateSignature
                + ", truePen=" + truePen
                + ", showSendInterim=" + showSendInterim
                + ", showSendFinalMarks=" + showSendFinalMarks
                + ", showSendTransNow=" + showSendTransNow
                + ", ordersGraduationCERTDisable=" + ordersGraduationCERTDisable
                + ", gradRequestYearEval=" + gradRequestYearEval
                + ", studentStatusError=" + studentStatusError
                + ", isSCCP=" + isSCCP
                + ", isAdult=" + isAdult + '}';
    }

    //--------------------------- Getter(s) and Setter(s)
    @Override
    public String getPen() {
        return pen;
    }

    public void setPen(final String pen) {
        this.pen = pen;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public Character getStatus() {
        return status;
    }

    public void setStatus(final Character status) {
        this.status = status;
    }

    /**
     * Answers true if the student is deceased.
     *
     * @return false The student status is not 'D'.
     */
    @Override
    public Boolean isDeceased() {
        return getStatus() == 'D';
    }

    @Override
    public Boolean isCurrentStudent() {
        return getStudentType() == 'C';
    }

    @Override
    public String getGrade() {
        return grade;
    }

    public void setGrade(final String grade) {
        this.grade = grade;
    }

    @Override
    public String getGradProgram() {
        return gradProgram;
    }

    public void setGradProgram(final String gradProgram) {
        this.gradProgram = gradProgram;
    }

    @Override
    public Boolean isTranscriptEligible() {
        return transcriptEligible;
    }

    public void setTranscriptEligible(final Boolean transcriptEligible) {
        this.transcriptEligible = transcriptEligible;
    }

    @Override
    public Character getStudentType() {
        return studentType;
    }

    public void setStudentType(final Character studentType) {
        this.studentType = studentType;
    }

    @Override
    public Date getSccDate() {
        return sccDate;
    }

    public void setSccDate(final Date sccDate) {
        this.sccDate = sccDate;
    }

    @Override
    public String getDogwoodFlag() {
        return dogwoodFlag;
    }

    public void setDogwoodFlag(final String dogwoodFlag) {
        this.dogwoodFlag = dogwoodFlag;
    }

    @Override
    public String getMincode() {
        return mincode;
    }

    public void setMincode(final String mincode) {
        this.mincode = mincode;
    }

    @Override
    public String getGradMincode() {
        return gradMincode;
    }

    public void setGradMincode(final String gradMincode) {
        this.gradMincode = gradMincode;
    }

    @Override
    public String getSchoolCategory() {
        return schoolCategory;
    }

    public void setSchoolCategory(final String schoolCategory) {
        this.schoolCategory = schoolCategory;
    }

    @Override
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(final String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String getTransEligibilityFlag() {
        return transEligibilityFlag;
    }

    public void setTransEligibilityFlag(final String transEligibilityFlag) {
        this.transEligibilityFlag = transEligibilityFlag;
    }

    @Override
    public Boolean isPrintCertEligible() {
        return certificateEligible;
    }

    public void setCertificateEligible(final Boolean certificateEligible) {
        this.certificateEligible = certificateEligible;
    }

    @Override
    public String getCertificateCategory() {
        return certificateCategory;
    }

    public void setCertificateCategory(final String certificateCategory) {
        this.certificateCategory = certificateCategory;
    }

    @Override
    public String getEnglishCertificate() {
        return englishCertificate;
    }

    public void setEnglishCertificate(final String englishCertificate) {
        this.englishCertificate = englishCertificate;
    }

    @Override
    public String getEnCertType() {
        return enCertType;
    }

    public void setEnCertType(final String enCertType) {
        this.enCertType = enCertType;
    }

    @Override
    public String getEnCertMedia() {
        return enCertMedia;
    }

    public void setEnCertMedia(final String enCertMedia) {
        this.enCertMedia = enCertMedia;
    }

    @Override
    public String getFrenchCertificate() {
        return frenchCertificate;
    }

    public void setFrenchCertificate(final String frenchCertificate) {
        this.frenchCertificate = frenchCertificate;
    }

    @Override
    public String getFrCertType() {
        return frCertType;
    }

    public void setFrCertType(final String frCertType) {
        this.frCertType = frCertType;
    }

    @Override
    public String getFrCertMedia() {
        return frCertMedia;
    }

    public void setFrCertMedia(final String frCertMedia) {
        this.frCertMedia = frCertMedia;
    }

    @Override
    public Date getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(final Date certificateDate) {
        this.certificateDate = certificateDate;
    }

    @Override
    public String getCertificateSignature() {
        return certificateSignature;
    }

    public void setCertificateSignature(final String certificateSignature) {
        this.certificateSignature = certificateSignature;
    }

    @Override
    public String getTruePen() {
        return truePen;
    }

    public void setTruePen(final String truePen) {
        this.truePen = truePen;
    }

    @Override
    public Boolean getShowSendInterim() {
        return showSendInterim;
    }

    public void setShowSendInterim(final Boolean showSendInterim) {
        this.showSendInterim = showSendInterim;
    }

    @Override
    public Boolean getShowSendFinalMarks() {
        return showSendFinalMarks;
    }

    public void setShowSendFinalMarks(final Boolean showSendFinalMarks) {
        this.showSendFinalMarks = showSendFinalMarks;
    }

    @Override
    public Boolean getShowSendTransNow() {
        return showSendTransNow;
    }

    public void setShowSendTransNow(final Boolean showSendTransNow) {
        this.showSendTransNow = showSendTransNow;
    }

    public void setOrdersGraduationCERTDisable(final Boolean ordersGraduationCERTDisable) {
        this.ordersGraduationCERTDisable = ordersGraduationCERTDisable;
    }

    public void setGradRequestYearEval(final Boolean gradRequestYearEval) {
        this.gradRequestYearEval = gradRequestYearEval;
    }

    @Override
    public Boolean getStudentStatusError() {
        return studentStatusError;
    }

    public void setStudentStatusError(final Boolean studentStatusError) {
        this.studentStatusError = studentStatusError;
    }

    /**
     * FIXME: Can this be derived from the graduation program code?
     *
     * @return true The student is in the SCCP program code?
     */
    @Override
    public Boolean getIsSCCP() {
        return isSCCP;
    }

    /**
     * FIXME: Can this be derived from the graduation program code?
     *
     * @param isSCCP true The student is in the SCCP program code?
     */
    public void setIsSCCP(final Boolean isSCCP) {
        this.isSCCP = isSCCP;
    }

    @Override
    public String getSchoolTypeIndicator() {
        return this.schoolTypeIndicator;
    }

    @Override
    public Boolean getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(final Boolean isAdult) {
        this.isAdult = isAdult;
    }

}
