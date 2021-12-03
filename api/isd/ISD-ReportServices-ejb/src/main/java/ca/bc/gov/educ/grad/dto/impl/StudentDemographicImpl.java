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

import ca.bc.gov.educ.grad.model.codes.GraduationProgramCode;
import ca.bc.gov.educ.grad.model.common.Constants;
import ca.bc.gov.educ.grad.model.school.TabSchool;
import ca.bc.gov.educ.grad.model.student.StudentDemographic;
import ca.bc.gov.educ.grad.model.student.StudentMaster;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.model.codes.GraduationProgramCode.PROGRAM_SCCP;
import static ca.bc.gov.educ.grad.model.common.Constants.DATE_TRAX_YM;

/**
 * A TRAX data object containing data from the TRAX database for the given
 * student PEN. The data is student demographic information.
 *
 * TODO: Refactor this to use entities, instead of copying the data from those
 * entities. Use delegation to retrieve the values.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class StudentDemographicImpl implements StudentDemographic {

    private static final long serialVersionUID = 4L;

    private static final String CLASSNAME = StudentDemographicImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final Character DEFAULT_STATUS = 'C';

    private String pen;
    private String truePen;

    // basic student info
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private Date birthDate;

    // extended student info
    private String status = " ";
    private String grade = "";
    private String gradProgram = "";
    private Boolean transcriptEligible = Boolean.FALSE;
    private Character studentType = ' ';
    private Date sccDate;
    private String dogwoodFlag = "";

    /**
     * Represents school of mincode grad, when present, otherwise mincode. Many
     * of the school variables below can be replaced with delegation to the
     * TabSchool entity.
     */
    private TabSchool school;

    // school info
    private String schoolSignatureDistrictNumber = "";
    private String mincode = "";
    private String gradMincode = "";
    private String schoolName = "";
    private String transEligibilityFlag = "";
    private String schoolTypeIndicator = "";
    private String schoolCategory = "";

    // graduation certificate info
    private Boolean certificateEligible = Boolean.FALSE;
    private String certificateCategory = "";
    private String englishCertificate = "";
    private String enCertType = "";
    private String enCertMedia = "";
    private String frenchCertificate = "";
    private String frCertType = "";
    private String frCertMedia = "";
    private Date certificateDate;
    private String certificateSignature = "";

    /**
     * Default (empty) constructor.
     */
    public StudentDemographicImpl() {
    }

    /**
     * Delegates to StudentDemographicImpl constructor with two TabSchool
     * instances.
     *
     * @param student
     * @param school
     */
    public StudentDemographicImpl(
            final StudentMaster student,
            final TabSchool school) {
        this(student, school, null);
    }

    public StudentDemographicImpl(
            final StudentMaster student,
            final TabSchool recentSchool,
            final TabSchool graduationSchool) {
        this(
                student.getStudNo(),
                student.getStudGiven(),
                student.getStudMiddle(),
                student.getStudSurname(),
                student.getStudBirth(),
                student.getStudStatus(),
                student.getStudGrade(),
                student.getGradReqtYear(),
                student.getMincodeGrad(),
                student.getMincode(),
                student.getGradDate(),
                recentSchool.getDogwoodElig(),
                student.getFrenchCert(),
                student.getEnglishCert(),
                student.getSccDate(),
                student.getStud_true_no()
        );

        // If the student's graduation school is not set, it means that the
        // student graduated from the school they most recently attended.
        this.school = graduationSchool == null ? recentSchool : graduationSchool;

        setSchoolTypeIndicator(this.school.getSchlIndType());
        setSchoolSignatureDistrictNumber(this.school.getSignatureDistno());
        setCertificateSignature();
        setCertificatePrintRules();
    }

    /**
     * TODO: Instead of delegating to this constructor, remove it entirely and
     * merge the code into the preceding constructor. Then update the unit tests
     * accordingly. This will lead naturally to delegating using the
     * StudentMaster and TabSchool entity instances.
     *
     * @param pen
     * @param given
     * @param middle
     * @param surname
     * @param birthdate
     * @param status
     * @param grade
     * @param gradProgram
     * @param mincodeGrad
     * @param mincode
     * @param gradDate
     * @param dogwoodFlag
     * @param frenchCert
     * @param englishCert
     * @param sccDate
     * @param truePen
     */
    public StudentDemographicImpl(
            final String pen,
            final String given,
            final String middle,
            final String surname,
            final Date birthdate,
            final String status,
            final String grade,
            final String gradProgram,
            final String mincodeGrad,
            final String mincode,
            final Date gradDate,
            final Character dogwoodFlag,
            final String frenchCert,
            final String englishCert,
            final Date sccDate,
            final String truePen) {
        this.pen = pen;
        this.firstName = trimSafe(given);
        this.middleName = trimSafe(middle);
        this.lastName = trimSafe(surname);
        this.birthDate = (birthdate == null ? new Date() : birthdate);
        this.status = nullSafe(status);
        this.grade = trimSafe(grade);
        this.gradProgram = trimSafe(gradProgram);
        this.gradMincode = trimSafe(mincodeGrad);
        this.mincode = trimSafe(mincode);
        this.certificateCategory = "Dogwood";

        if (PROGRAM_SCCP.isCode(gradProgram)) {
            this.certificateCategory = PROGRAM_SCCP.toString();
        }

        // determine student dogwood flag
        this.dogwoodFlag = dogwoodFlag == null ? "" : dogwoodFlag.toString();
        LOG.log(Level.FINE, "pen {0}, dogwoodFlag value = {1}", new Object[]{this.pen, this.dogwoodFlag});

        // determine certificate eligibility
        Long graduationDate = 0L;

        if (gradDate != null && gradDate.toString().length() == DATE_TRAX_YM.length()) {
            graduationDate = gradDate.getTime();
        }

        LOG.log(Level.FINE, "graduationDate value = {0}", graduationDate);

        setCertEligibility(gradDate, this.dogwoodFlag, this.mincode);

        this.frenchCertificate = trimSafe(frenchCert);
        this.englishCertificate = trimSafe(englishCert);

        Date certDate = null;

        try {
            // Determine student graduation date
            if (graduationDate.compareTo(0L) != 0) {
                final SimpleDateFormat sdf = new SimpleDateFormat(DATE_TRAX_YM);
                certDate = sdf.parse(graduationDate.toString());
            }
        } catch (final ParseException e) {
            LOG.log(Level.WARNING, "Failed to parse date: <{0}>", graduationDate);
        }

        this.certificateDate = certDate;
        LOG.log(Level.FINE, "certificateDate value = {0}", certificateDate);

        this.truePen = trimSafe(truePen);
        this.sccDate = sccDate;
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
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public Character getStatus() {
        return status.charAt(0);
    }

    @Override
    public String getGrade() {
        return grade;
    }

    @Override
    public String getGradProgram() {
        return this.gradProgram;
    }

    @Override
    public Boolean isPrintCertEligible() {
        return this.certificateEligible;
    }

    @Override
    public Boolean isTranscriptEligible() {
        return this.transcriptEligible;
    }

    /**
     * Set the flag indicating if this student is eligible to print/order a
     * transcript.
     *
     * @param transcriptEligible
     */
    public void setTranscriptEligible(final Boolean transcriptEligible) {
        this.transcriptEligible = transcriptEligible;
    }

    @Override
    public String getSchoolSignatureDistrictNumber() {
        return this.schoolSignatureDistrictNumber;
    }

    private void setSchoolSignatureDistrictNumber(final String schoolSignatureDistrictNumber) {
        this.schoolSignatureDistrictNumber = schoolSignatureDistrictNumber;
    }

    @Override
    public String getGradMincode() {
        return this.gradMincode;
    }

    @Override
    public String getMincode() {
        return this.mincode;
    }

    @Override
    public TabSchool getSchool() {
        return this.school;
    }

    @Override
    public String getSchoolTypeIndicator() {
        return this.schoolTypeIndicator;
    }

    /**
     * Sets the independent/non-independent indicator type code.
     *
     * @param schoolTypeIndicator Empty or null values acceptable.
     */
    public final void setSchoolTypeIndicator(final String schoolTypeIndicator) {
        this.schoolTypeIndicator = trimSafe(schoolTypeIndicator);
    }

    /**
     * Sets the independent/non-independent indicator type code.
     *
     * @param schoolTypeIndicator Empty or null values acceptable.
     */
    public final void setSchoolTypeIndicator(final Character schoolTypeIndicator) {
        setSchoolTypeIndicator(nullSafe(schoolTypeIndicator));
    }

    public void setSchoolCategory(final String schoolCategory) {
        this.schoolCategory = schoolCategory;
    }

    /**
     * Sets the school of record for this student. This will always reflect
     * either the student's mincode or mincode grad.
     *
     * @param school The student's school of record.
     */
    public void setSchool(final TabSchool school) {
        this.school = school;
    }

    @Override
    public Character getStudentType() {
        return this.studentType;
    }

    /**
     * Set the indicator for a current student or a former student.
     *
     * @param type must be either a 'C' or an 'F'
     */
    public void setStudentType(final Character type) {

        Character localType = DEFAULT_STATUS;

        if (type != null && type.equals('F')) {
            localType = type;
        }

        this.studentType = localType;
    }

    @Override
    public String getCertificateCategory() {
        return this.certificateCategory;
    }

    @Override
    public String getEnglishCertificate() {
        return this.englishCertificate;
    }

    @Override
    public String getFrenchCertificate() {
        return this.frenchCertificate;
    }

    @Override
    public Date getSccDate() {
        return this.sccDate;
    }

    /**
     * determine the value of the print certificate eligible flag. If the
     * student is NOT completing a dogwood diploma then eligible = false, If the
     * dogwood student has no graduation date then eligible = false, If the
     * student attended high school with the mincode of "098" then eligible =
     * false.
     *
     * @param gradDate
     * @param dogwood
     */
    private void setCertEligibility(final Date gradDate, final String dogwood, final String mincode) {
        if (!"Y".equalsIgnoreCase(dogwood)) {
            this.certificateEligible = false;
        } else if (gradDate == null) {
            this.certificateEligible = false;
        } else {
            this.certificateEligible = !"098".equals(mincode);
        }
    }

    @Override
    public String getSchoolName() {
        return this.schoolName;
    }

    /**
     * TODO: Derive value
     *
     * @param school
     */
    public void setSchoolName(final String school) {
        this.schoolName = school;
    }

    @Override
    public String getTransEligibilityFlag() {
        return this.transEligibilityFlag;
    }

    /**
     * Sets school eligibility flag.
     *
     * @param transEligibilityFlag
     */
    public void setTransEligibilityFlag(final String transEligibilityFlag) {
        this.transEligibilityFlag = transEligibilityFlag;
    }

    @Override
    public Date getCertificateDate() {
        return this.certificateDate;
    }

    @Override
    public String getCertificateSignature() {
        return this.certificateSignature;
    }

    @Override
    public String getEnCertType() {
        return this.enCertType;
    }

    @Override
    public String getEnCertMedia() {
        return this.enCertMedia;
    }

    @Override
    public String getFrCertType() {
        return this.frCertType;
    }

    @Override
    public String getFrCertMedia() {
        return this.frCertMedia;
    }

    @Override
    public String getDogwoodFlag() {
        return this.dogwoodFlag;
    }

    /**
     * Apply the BC Mail printing rules for the certificates.
     */
    private void setCertificatePrintRules() {
        setFrenchCertificate();
        setEnglishCertificate();
    }

    /**
     * Set the printing values for BC Mail for a French certificate. This is
     * based on the frenchCertificate value.
     */
    private void setFrenchCertificate() {
        switch (this.frenchCertificate) {
            case "S":
                this.frCertType = Constants.CSF_FRENCH_DOGWOOD;
                this.frCertMedia = Constants.CERTIFICATE_YEDR_MEDIA_TYPE;
                break;
            case "F":
                this.frCertType = Constants.FRENCH_DOGWOOD;
                this.frCertMedia = Constants.CERTIFICATE_YEDR_MEDIA_TYPE;
                break;
        }
    }

    /**
     * Set the printing values for BC Mail for an English certificate.
     */
    private void setEnglishCertificate() {
        String indicator;

        // SCCP takes priority over independent schools.
        if (isSCCProgram()) {
            indicator = "SC";
        } else {
            indicator = isAdultProgram() ? "A" : "E";

            if (isIndependentSchool()) {
                indicator += "I";
            }
        }

        LOG.log(Level.FINE,
                "Ceritificate Indicator {0} for PEN {1}",
                new Object[]{indicator, this.pen});

        switch (indicator) {
            case "E":
                this.enCertType = Constants.ENGLISH_DOGWOOD;
                this.enCertMedia = Constants.CERTIFICATE_YEDR_MEDIA_TYPE;
                break;
            case "EI":
                this.enCertType = Constants.ENGLISH_DOGWOOD_IND;
                this.enCertMedia = Constants.CERTIFICATE_YEDR_MEDIA_TYPE;
                break;
            case "A":
                this.enCertType = Constants.ENGLISH_DOGWOOD_ADULT;
                this.enCertMedia = Constants.CERTIFICATE_YEDB_MEDIA_TYPE;
                break;
            case "AI":
                this.enCertType = Constants.ENGLISH_DOGWOOD_ADULT_IND;
                this.enCertMedia = Constants.CERTIFICATE_YEDB_MEDIA_TYPE;
                break;
            case "SC":
                this.enCertType = Constants.SCCP_CERTIFICATE;
                this.enCertMedia = Constants.CERTIFICATE_SCCP_MEDIA_TYPE;
                break;
        }

        LOG.log(Level.FINE,
                "Ceritificate type {0}, and certificate media {1} for PEN {2}",
                new Object[]{this.enCertType, this.enCertMedia, this.pen});
    }

    /**
     * Sets the certificate signature based on the last two digits of the
     * 3-digit ministry code. The algorithm:
     * <ol>
     * <li>If present, use the signature for the school's district number.</li>
     * <li>Otherwise, if present, use the graduation ministry code.</li>
     * <li>Otherwise, use the student's ministry code.</li>
     * <li>Trim the signature code to two digits.</li>
     * </ol>
     */
    private void setCertificateSignature() {
        final String schoolDistrictNumber = getSchoolSignatureDistrictNumber();
        final String graduationMinistryCode = getGradMincode();
        final String ministryCode = getMincode();

        try {
            final String signature;

            if (schoolDistrictNumber.isEmpty()) {
                signature = graduationMinistryCode.isEmpty()
                        ? ministryCode
                        : graduationMinistryCode;
            } else {
                signature = schoolDistrictNumber;
            }
            if(!"".equals(signature))
                this.certificateSignature = signature.substring(0, 3);
        } catch (final IndexOutOfBoundsException e) {
            LOG.log(Level.WARNING, "Could not determine certificate signature.", e);
        }
    }

    @Override
    public String getTruePen() {
        return this.truePen;
    }

    /**
     * On STUDENT_MASTER_VW we have MINCODE and MINCODE_GRAD.
     *
     * We have TAB_SCHOOL_VW we have SCHL_IND_TYPE Independent Schools are
     * identified in TAB_SCHOOL. Entries in TAB_SCHOOL where the value of
     * SCHL_IND_TYPE is set to 1, 2, 3, 4, 5, or 6 (that is not blank) are
     * Independent Schools. If the SCHL_IND_TYPE is blank, the school is not an
     * independent school.
     *
     * studentMincode = mincode_grad (if mincode_grad is not blank, else =
     * mincode)
     *
     * <pre>
     * if (studentMincode has a schl_ind_type)
     * the school is independent
     * else
     * school is not independent
     * </pre>
     *
     * @return true The school is independent.
     */
    private boolean isIndependentSchool() {
        return !getSchoolTypeIndicator().isEmpty();
    }

    @Override
    public String getSchoolCategory() {
        return this.schoolCategory;
    }

    /**
     * Returns true iff the student took part in the adult program.
     *
     * @return false The student is not part of the adult program.
     */
    @Override
    public boolean isAdultProgram() {
        // FIXME: This class should use a GraduationProgramCode, not a string.
        final String graduationProgram = getGradProgram();
        final GraduationProgramCode program = GraduationProgramCode.valueFrom(graduationProgram);
        boolean isAdult = program.isAdult();

        LOG.log(Level.FINE, "PEN {0}: Adult program flag {1}, Graduation program {2}",
                new Object[]{this.pen, isAdult, graduationProgram});

        return isAdult;
    }

    /**
     * Returns true iff the student took part in the SCC program.
     *
     * @return false The student is not part of the SCC program.
     */
    private boolean isSCCProgram() {
        boolean isSCCP = PROGRAM_SCCP.isCode(getGradProgram());
        LOG.log(Level.FINE, "PEN {0}: SCCP program flag {1}, Graduation program {2}",
                new Object[]{this.pen, isSCCP, getGradProgram()});

        return isSCCP;
    }

    /**
     * Returns a trimmed version of the given string.
     *
     * @param s The string to trim.
     * @return The empty string if s is null, otherwise s.trim().
     */
    private String trimSafe(final String s) {
        return s == null ? "" : s.trim();
    }

    /**
     * Returns c or an empty space if c is null.
     *
     * @param c The character to ensure is not null.
     * @return A space or the given character, never null.
     */
    private String nullSafe(final Character c) {
        return c == null ? " " : c.toString();
    }

    /**
     * Returns c or an empty space if c is null.
     *
     * @param c The character to ensure is not null.
     * @return A space or the given character, never null.
     */
    private String nullSafe(final String c) {
        return c == null ? "" : c.toString();
    }

    private Long nullSafe(final Long l) {
        return l == null ? 0L : l;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.pen != null ? this.pen.hashCode() : 0);
        hash = 31 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 31 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 31 * hash + (this.birthDate != null ? this.birthDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudentDemographicImpl other = (StudentDemographicImpl) obj;
        if ((this.pen == null) ? (other.pen != null) : !this.pen.equals(other.pen)) {
            return false;
        }
        if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
            return false;
        }
        if ((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName)) {
            return false;
        }
        return !((this.birthDate == null) ? (other.birthDate != null) : !this.birthDate.equals(other.birthDate));
    }
}
