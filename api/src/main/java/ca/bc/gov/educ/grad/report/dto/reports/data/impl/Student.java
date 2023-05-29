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
package ca.bc.gov.educ.grad.report.dto.reports.data.impl;

import ca.bc.gov.educ.grad.report.dto.impl.OtherProgramImpl;
import ca.bc.gov.educ.grad.report.dto.reports.data.BusinessEntity;
import ca.bc.gov.educ.grad.report.model.common.SignatureBlockType;
import ca.bc.gov.educ.grad.report.model.common.support.StringUtils;
import jakarta.validation.constraints.NotNull;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ca.bc.gov.educ.grad.report.dto.reports.data.impl.TranscriptResult.*;

/**
 * Represents a Student and the information about this student that pertains to
 * creating transcripts and other types of reports. The methods that return a
 * List of items should never return null, but could return empty lists.
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlRootElement(name = "academicRecord")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Student extends BusinessEntity {

    private static final long serialVersionUID = 2L;

    /**
     * Personal Education Number, the unique key for this student.
     */
    private String pen;

    /**
     * Student's first given name.
     */
    private String firstName;

    /**
     * Student's surname.
     */
    private String lastName;

    /**
     * Space-separated list of middle names.
     */
    private String middleNames;

    /**
     * Student''s gender
     */
    private String gender;

    /**
     * Student''s citizenship
     */
    private String citizenship;

    /**
     * Grade level of a student. Used in an XML but not paper transcript.
     */
    private String grade;

    /**
     * When the student was born.
     */
    @NotNull(message = "DoB is null")
    private LocalDate birthdate;

    /**
     * The school of issuance.
     */
    @XmlElement(name = "school")
    private School school = new School();

    /**
     * Current mailing address.
     */
    @XmlElement(name = "address")
    private PostalAddress address;

    /**
     * Graduation program code (SCCP, 2004, 1986, etc.) and description.
     */
    @XmlElement(name = "graduationProgram")
    private GraduationProgram graduationProgram = new GraduationProgram();

    /**
     * Includes permissions and other information about this student, such as
     * the graduation reason notes and whether the student is current or former.
     */
    @XmlElement(name = "status")
    private Status status = new Status();

    /**
     * Used for the provincial examination results report.
     */
    @XmlElementWrapper(name = "examinationResults")
    @XmlElement(name = "examinationResult")
    private List<ExaminationResult> examinationResults = new ArrayList<>();

    /**
     * Used for the transcript of grades reports.
     */
    @XmlTransient
    private List<TranscriptResult> transcriptResults = new ArrayList<>();

    /**
     * Used for the achievement of grades reports.
     */
    @XmlTransient
    private List<AchievementResult> achievementResults = new ArrayList<>();

    /**
     * Used for the certificate report.
     */
    @XmlElementWrapper(name = "certificates")
    @XmlElement(name = "certificate")
    private List<Certificate> certificates = new ArrayList<>();

    /**
     * Used for the transcript of grades XML reports.
     */
    @XmlElement(name = "academicSession")
    private List<AcademicSession> academicSession = new ArrayList<>();

    /**
     * Used for the transcript of grades XML reports.
     */
    private AcademicAward academicAward;

    private Map<String, SignatureBlockType> signatureBlockTypes;

    private String localId = "";
    private String hasOtherProgram = "";
    private List<OtherProgramImpl> otherProgramParticipation = new ArrayList<>();

    /**
     * Default (empty) constructor.
     */
    public Student() {
    }

    /**
     * Returns the student's Personal Education Number (PEN). Never returns
     * null.
     *
     * @return The PEN used to uniquely identify the student, or the empty
     * string.
     */
    public String getPEN() {
        return nullSafe(this.pen);
    }

    /**
     * Returns the student's first name. Typically the calling client will
     * display the name exactly as returned by this method.
     *
     * @return The student's first name, or an empty string, never null.
     */
    public String getFirstName() {
        return nullSafe(this.firstName);
    }

    /**
     * Returns the student's last name. Typically the calling client will
     * display the name exactly as returned by this method.
     *
     * @return The student's last name, or an empty string, never null.
     */
    public String getLastName() {
        return nullSafe(this.lastName);
    }

    /**
     * Returns a space-separated list of middle names.
     *
     * @return A string, possibly empty, that contains middle names, but never
     * null.
     */
    public String getMiddleNames() {
        return nullSafe(this.middleNames);
    }

    public String getFullName() {
        return (getLastName().trim() + (isBlank() ? "" : ", " ) + getFirstName().trim() + " " + getMiddleNames().trim()).toUpperCase();
    }

    public String getGender() {
        return nullSafe(this.gender);
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Returns a student's grade.
     *
     * @return A string, possibly empty, that contains a student's grade.
     */
    public  String getGrade() {
        return nullSafe(this.grade);
    }

    /**
     * Returns the student's birthdate.
     *
     * @return The student's birthdate, or Jan 1, 1900, never null.
     */
    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    public String getCitizenship() {
        return citizenship;
    }

    /**
     * Answers true if the student has graduated.
     *
     * @return false The student has not completed all the required courses.
     */
    public boolean hasGraduated() {
        return getStatus().getGraduated();
    }

    /**
     * Returns a student's graduation program, which contains the graduation
     * program code and a description.
     *
     * @return The student's graduation program, never null.
     */
    public GraduationProgram getGraduationProgram() {
        return this.graduationProgram == null ? createGraduationProgram()
                : this.graduationProgram;
    }

    /**
     * Returns a student's status information, which contains the graduation
     * notes.
     *
     * @return Status information about the student, never null.
     */
    public Status getStatus() {
        return this.status == null ? createStatus() : this.status;
    }

    /**
     * Answers whether the Student's first name is equal to the given parameter.
     * Performs a case-sensitive comparison.
     *
     * @param name The first name to compare against.
     * @return true The first name is an exact match.
     */
    public boolean isFirstName(final String name) {
        return getFirstName().equals(name);
    }

    /**
     * Answers whether the Student's last name is equal to the given parameter.
     * Performs a case-sensitive comparison.
     *
     * @param name The last name to compare against.
     * @return true The last name is an exact match.
     */
    public boolean isLastName(final String name) {
        return getLastName().equals(name);
    }

    /**
     * Used by the builder to set the First Name.
     *
     * @param firstName Passed in by the builder.
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Used by the builder to set the Last Name.
     *
     * @param lastName Passed in by the builder.
     */
    protected void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Used by the builder to set the Middle Name.
     *
     * @param middleNames Passed in by the builder.
     */
    protected void setMiddleNames(final String middleNames) {
        this.middleNames = middleNames;
    }

    /**
     * Used by the builder to set the grade level of the student.
     *
     * @param grade Passed in by the builder.
     */
    protected  void setGrade(final String grade) {
        this.grade = grade;
    }

    /**
     * Used by the builder to set the birthdate.
     *
     * @param birthdate Passed in by the builder.
     */
    protected void setBirthdate(final LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    protected void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    /**
     * Changes the student's graduation program, which includes a code and
     * description.
     *
     * @param graduationProgram The student's new graduation program.
     */
    public void setGraduationProgram(final GraduationProgram graduationProgram) {
        this.graduationProgram = graduationProgram;
    }

    public void setAcademicAward(AcademicAward academicAward) {
        this.academicAward = academicAward;
    }

    /**
     * Returns a generic address object that contains the mailing address for
     * the student.
     *
     * @return Returns the address associated with this student, or null if
     * there is no address assigned.
     */
    public PostalAddress getAddress() {
        return this.address;
    }

    /**
     * School that retains a student's course grades (usually the same school
     * from where the student graduated).
     *
     * Lazily initialized.
     *
     * @return The student's school.
     */
    public School getSchool() {
        if (this.school == null) {
            this.school = createSchool();
        }

        return this.school;
    }

    /**
     * Returns the list of examination results associated with this student.
     * Note that in the future, the examination results might also include
     * assessment results. From a design perspective, it might be more flexible
     * to separate assessment results from exam results.
     *
     * Lazily initialized.
     *
     * @return A list of courses and grades this student achieved, never null
     * (possibly empty).
     */
    public List<ExaminationResult> getExaminationResults() {
        if (this.examinationResults == null) {
            this.examinationResults = createEmptyList();
        }

        return this.examinationResults;
    }

    /**
     * Returns a list of transcript marks (courses and grades) used to populate
     * the Transcript of Grades report.
     *
     * Lazily initialized.
     *
     * @return A list of courses and corresponding grades that the student
     * achieved, never null (possibly empty).
     */
    public List<TranscriptResult> getTranscriptResults() {
        if (this.transcriptResults == null) {
            this.transcriptResults = createEmptyList();
        }

        return this.transcriptResults;
    }

    /**
     * Returns a list of achievement marks (courses and grades) used to populate
     * the Transcript of Grades report.
     *
     * Lazily initialized.
     *
     * @return A list of courses and corresponding grades that the student
     * achieved, never null (possibly empty).
     */
    public List<AchievementResult> getAchievementResults() {
        if (this.achievementResults == null) {
            this.achievementResults = createEmptyList();
        }

        return this.achievementResults;
    }

    /**
     * Returns a list of transcript marks (courses and grades) used to populate
     * the Transcript of Grades report.
     *
     * Lazily initialized.
     *
     * @return A list of courses and corresponding grades that the student
     * achieved, never null (possibly empty).
     */
    public  List<AcademicSession> getAcademicSession() {
        if (this.academicSession == null) {
            this.academicSession = createEmptyList();
        }

        return this.academicSession;
    }

    /**
     * Returns the set of transcript results that are provincially examinable
     * courses. Ultimately, This checks the "sort order" for a value of "1".
     * This list is re-created each time the method is called.
     *
     * @return A list of provincially examinable courses.
     */
    public List<TranscriptResult> getProvinciallyExaminableCourses() {
        return getTranscriptCourses(COURSE_TYPE_PROVINCIAL);
    }

    /**
     * Returns the set of transcript results that are provincially examinable
     * courses or assessments. Ultimately, This checks the "sort order" for a
     * value of COURSE_TYPE_NONPROVINCIAL or COURSE_TYPE_ASSESSMENT. This list
     * is re-created each time the method is called. This will also return the
     * non-examinable grade 11 courses.
     *
     * @return A non-null list.
     */
    public List<TranscriptResult> getNonProvinciallyExaminableCourses() {
        final List<TranscriptResult> examinables = getTranscriptCourses(
                COURSE_TYPE_NONPROVINCIAL);
        final List<TranscriptResult> assessments = getAssessments();

        final int examinableSize = examinables.size();
        final int assessmentSize = assessments.size();

        final List<TranscriptResult> results = new ArrayList<>(
                examinableSize + assessmentSize
        );

        results.addAll(examinables);
        results.addAll(assessments);

        return results;
    }

    /**
     * Returns the set of transcript results that are assessments. Ultimately,
     * This checks the "sort order" for a value of "3". This list is re-created
     * each time the method is called.
     *
     * @return A list of assessments.
     */
    public List<TranscriptResult> getAssessments() {
        return getTranscriptCourses(COURSE_TYPE_ASSESSMENT);
    }

    /**
     * Helper method to avoid duplicating the code in
     * getProvinciallyExaminableCourses and getNonProvinciallyExaminableCourses.
     * This list is re-created each time the method is called.
     *
     * @param reportCourseType One of the COURSE_TYPE_* constants.
     *
     * @return A non-null, possibly empty list.
     */
    private List<TranscriptResult> getTranscriptCourses(final String reportCourseType) {
        final List<TranscriptResult> result = createEmptyList();

        for (final TranscriptResult tr : getTranscriptResults()) {
            if (tr.isTranscriptReportCourseType(reportCourseType)) {
                result.add(tr);
            }
        }

        return result;
    }

    /**
     * TODO: Finish this.
     *
     * Lazily initialized.
     *
     * @return Unused at the moment.
     */
    public List<Certificate> getCertificates() {
        if (this.certificates == null) {
            this.certificates = createEmptyList();
        }

        return this.certificates;
    }

    /**
     * Sets the list of examination results that the student achieved.
     *
     * @param examinationResults Student grades.
     */
    public void setExaminationResults(final List<ExaminationResult> examinationResults) {
        this.examinationResults = examinationResults;
    }

    /**
     * Helper method to add an examination result to the student's list of
     * examination results.
     *
     * @param examinationResult The examination result to add to the internal
     * list of examination results.
     */
    public void addExaminationResult(final ExaminationResult examinationResult) {
        getExaminationResults().add(examinationResult);
    }

    /**
     * Sets the list of transcript results that the student achieved.
     *
     * @param transcriptResults Student grades.
     */
    public void setTranscriptResults(final List<TranscriptResult> transcriptResults) {
        this.transcriptResults = transcriptResults;
    }

    /**
     * Sets the list of achievement results that the student achieved.
     *
     * @param achievementResults Student grades.
     */
    public void setAchievementResults(final List<AchievementResult> achievementResults) {
        this.achievementResults = achievementResults;
    }

    /**
     * Sets the list of academic sessions that contains student marks by
     * session.
     *
     * @param academicSession Student academic session.
     */
    public  void setAcademicSessions(List<AcademicSession> academicSession) {
        this.academicSession = academicSession;
    }

    /**
     * Helper method to add a transcript result to the student's list of
     * transcript results.
     *
     * @param transcriptResult The transcript result to add to the internal list
     * of transcript results.
     */
    public void addTranscriptResult(final TranscriptResult transcriptResult) {
        getTranscriptResults().add(transcriptResult);
    }

    /**
     * Helper method to add a transcript result to the student's list of
     * transcript results.
     *
     * @param achievementResult The transcript result to add to the internal list
     * of transcript results.
     */
    public void addAchievementResult(final AchievementResult achievementResult) {
        getAchievementResults().add(achievementResult);
    }

    /**
     * Helper method to add a academic session to the student's list of academic
     * sessions.
     *
     * @param academicSession The academic session to add to the internal list
     * of academic session.
     */
    public void addAcademicSession(final AcademicSession academicSession) {
        getAcademicSession().add(academicSession);
    }

    /**
     * Sets the list of certificates awarded to this student.
     *
     * @param certificates
     */
    public void setCertificates(final List<Certificate> certificates) {
        this.certificates = certificates;
    }

    /**
     * Returns a reference to this Student instance. This is used by reports to
     * reference fields in this object starting with "student."
     *
     * @return A reference to "this", never null.
     */
    public Student getStudent() {
        return this;
    }

    /**
     * Used by the builder to set the student personal education number.
     *
     * @param pen Passed in by the builder.
     */
    protected void setPEN(final String pen) {
        this.pen = pen;
    }

    /**
     * Used by the builder to set the student school.
     *
     * @param school Passed in by the builder.
     */
    public void setSchool(final School school) {
        this.school = school;
    }

    /**
     * Used by the builder to set the student address.
     *
     * @param address Passed in by the builder.
     */
    protected void setAddress(final PostalAddress address) {
        this.address = address;
    }

    /**
     * Used by the builder to set the student status.
     *
     * @param status Passed in by the builder.
     */
    public void setStatus(final Status status) {
        this.status = status;
    }

    public  void setAcademicSession(
            final List<AcademicSession> academicSession) {
        this.academicSession = academicSession;
    }

    /**
     * Returns a new School instance for use when no school has been set.
     *
     * @return A School instance, never null;
     */
    protected School createSchool() {
        return new School();
    }

    /**
     * Returns a new GraduationProgram instance for use when no graduation
     * program has been set.
     *
     * @return A GraduationProgram instance, never null.
     */
    protected GraduationProgram createGraduationProgram() {
        return new GraduationProgram();
    }

    /**
     * Returns a new Status instance for use when no status information has been
     * set.
     *
     * @return A Status instance, never null.
     */
    protected Status createStatus() {
        return new Status();
    }

    /**
     * Answers whether the given personal education number matches the given
     * PEN.
     *
     * @param pen The personal education number to compare against.
     * @return true The Student's PEN matches the given pen.
     */
    public boolean isPEN(final String pen) {
        return getPEN().equals(pen);
    }

    /**
     * Returns the number of entries (size) in the transcript results list.
     *
     * @return A whole number.
     */
    public int countTranscriptResults() {
        return getTranscriptResults().size();
    }

    /**
     * Returns the number of entries (size) in the transcript results list that
     * match provincially examinable courses.
     *
     * @return A whole number.
     */
    public int countProvinciallyExaminableCourses() {
        return getProvinciallyExaminableCourses().size();
    }

    /**
     * Returns the number of entries (size) in the transcript results list that
     * match provincially examinable courses.
     *
     * @return A whole number.
     */
    public int countNonProvinciallyExaminableCourses() {
        return getNonProvinciallyExaminableCourses().size();
    }

    /**
     * Returns the number of entries (size) in the transcript results list that
     * match assessments.
     *
     * @return A whole number.
     */
    public int countAssessments() {
        return getAssessments().size();
    }

    /**
     * Returns the number of entries (size) in the examination results list.
     *
     * @return A whole number.
     */
    public int countExaminationResults() {
        return getExaminationResults().size();
    }

    /**
     * Changes the graduation message text. This does not include the non-grad
     * reasons. Forwards the call to the student's status.
     *
     * @param text The message text to include on the report.
     */
    public void setGraduationMessageText(final String text) {
        getStatus().setGraduationMessage(text);
    }

    /**
     * Answers whether this student's graduation program matches the given
     * graduation program code.
     *
     * @param code The graduation program code to compare against.
     * @return true The given code matches the graduation program code.
     */
    public boolean isRequirementYear(final String code) {
        return getGraduationProgram().isCode(code);
    }

    public Map<String, SignatureBlockType> getSignatureBlockTypes() {
        return signatureBlockTypes;
    }

    public void setSignatureBlockTypes(Map<String, SignatureBlockType> signatureBlockTypes) {
        this.signatureBlockTypes = signatureBlockTypes;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getHasOtherProgram() {
        return hasOtherProgram;
    }

    public void setHasOtherProgram(String hasOtherProgram) {
        this.hasOtherProgram = hasOtherProgram;
    }

    public List<OtherProgramImpl> getOtherProgramParticipation() {
        return otherProgramParticipation;
    }

    public void setOtherProgramParticipation(List<OtherProgramImpl> otherProgramParticipation) {
        this.otherProgramParticipation = otherProgramParticipation;
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder
            extends BusinessEntity.Builder<Student, Builder> {

        /**
         * Returns the builder used to construct outer class instances.
         *
         * @return this
         */
        @Override
        protected Builder thisBuilder() {
            return this;
        }

        /**
         * Returns an outer class instance without attributes initialized.
         *
         * @return New Student instance.
         */
        @Override
        protected Student createObject() {
            return new Student();
        }

        /**
         * Sets the personal education number for a student.
         *
         * @param pen The personal education number to uniquely identify the
         * student.
         * @return thisBuilder
         */
        public Builder withPEN(final String pen) {
            getObject().setPEN(pen);
            return thisBuilder();
        }

        /**
         * Sets the personal education number for a student.
         *
         * @param pen The PEN that uniquely identifies the student.
         * @return thisBuilder
         */
        public Builder withPEN(final Long pen) {
            final String p = nullSafe(pen).toString();
            getObject().setPEN(p);
            return thisBuilder();
        }

        /**
         * Sets the first name, typically associated with a student.
         *
         * @param firstName A person's first name.
         * @return thisBuilder
         */
        public Builder withFirstName(final String firstName) {
            getObject().setFirstName(firstName);
            return thisBuilder();
        }

        /**
         * Sets the last name, typically associated with a student.
         *
         * @param lastName A person's surname.
         * @return thisBuilder
         */
        public Builder withLastName(final String lastName) {
            getObject().setLastName(lastName);
            return thisBuilder();
        }

        /**
         * Sets the middle names, typically associated with a student.
         *
         * @param middleNames A person's middle names.
         * @return thisBuilder
         */
        public Builder withMiddleNames(final String middleNames) {
            getObject().setMiddleNames(middleNames);
            return thisBuilder();
        }

        /**
         * Sets the grade of a student.
         *
         * @param grade A student's grade level.
         * @return thisBuilder
         */
        public Builder withGrade(final String grade) {
            getObject().setGrade(grade);
            return thisBuilder();
        }

        /**
         * Sets the date the person was born, typically associated with a
         * student.
         *
         * @param birthdate Date the person was born.
         * @return thisBuilder
         */
        public Builder withBirthdate(final LocalDate birthdate) {
            getObject().setBirthdate(birthdate);
            return thisBuilder();
        }

        /**
         * Convenience method to create a new birthdate using a given year,
         * month, and day.
         *
         * @param citizenship
         * @return thisBuilder
         */
        public Builder withCitizenship(final String citizenship) {
            getObject().setCitizenship(citizenship);
            return thisBuilder();
        }

        /**
         * Sets the graduation program year that the student graduated.
         *
         * @param gp The student's graduation program.
         * @return thisBuilder
         */
        public Builder withGraduationProgram(final GraduationProgram gp) {
            getObject().setGraduationProgram(gp);
            return thisBuilder();
        }

        /**
         * Sets the school from where the student graduated.
         *
         * @param school School the student graduated.
         * @return thisBuilder
         */
        public Builder withSchool(final School school) {
            getObject().setSchool(school);
            return thisBuilder();
        }

        /**
         * Sets the mailing address.
         *
         * @param address Mailing address for antiquated paper delivery.
         * @return thisBuilder
         */
        public Builder withAddress(final PostalAddress address) {
            getObject().setAddress(address);
            return thisBuilder();
        }

        /**
         * Sets the status.
         *
         * @param status Contains information about graduation, former/current,
         * whether examinations are in session, etc.
         * @return thisBuilder
         */
        public Builder withStatus(final Status status) {
            getObject().setStatus(status);
            return thisBuilder();
        }

        /**
         * Sets the list of transcript results.
         *
         * @param transcriptResults Course names and grades.
         * @return thisBuilder
         */
        public Builder withTranscriptResults(final List<TranscriptResult> transcriptResults) {
            getObject().setTranscriptResults(transcriptResults);
            return thisBuilder();
        }

        /**
         * Sets the list of certificates.
         *
         * @param certificates The list of certificates.
         * @return thisBuilder
         */
        public Builder withCertificates(final List<Certificate> certificates) {
            getObject().setCertificates(certificates);
            return thisBuilder();
        }

        public Builder withSignatureBlockTypes(final Map<String, SignatureBlockType> signatureBlockTypes) {
            getObject().setSignatureBlockTypes(signatureBlockTypes);
            return thisBuilder();
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "pen=" + pen + ", firstName=" + firstName + ", lastName=" + lastName + ", middleNames=" + middleNames + ", grade=" + grade + ", birthdate=" + birthdate + ", school=" + school + ", address=" + address + ", graduationProgram=" + graduationProgram + ", status=" + status + ", examinationResults=" + examinationResults + ", transcriptResults=" + transcriptResults + ", certificates=" + certificates + ", academicSession=" + academicSession + ", academicAward=" + academicAward + '}';
    }

    private boolean isBlank() {
        return !StringUtils.StringUtilsIsNotBlank(getFirstName() + getLastName());
    }

}
