package ca.bc.gov.educ.grad.report.api.client;

import ca.bc.gov.educ.grad.report.api.client.utils.NonGradReasonListDeserializer;
import ca.bc.gov.educ.grad.report.api.client.utils.OtherProgramListDeserializer;
import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student implements Serializable {

    public static final Pen NULL = new Pen();

    private Pen pen = new Pen();
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private String gender = "";
    private String citizenship = "";
    @NotNull(message = "DoB is null")
    private LocalDate birthdate;
    private Address address = new Address();
    private String grade = "";
    private String gradProgram = "";
    private String gradReqYear = "";
    private String studStatus = "";
    private String sccDate = "";
    private String mincodeGrad = "";
    private String englishCert = "";
    private String frenchCert = "";

    private String localId = "";
    private String hasOtherProgram = "";
    private LocalDateTime lastUpdateDate;
    private List<OtherProgram> otherProgramParticipation = new ArrayList<>();
    private List<NonGradReason> nonGradReasons = new ArrayList<>();

    @JsonDeserialize(as = GraduationData.class)
    private GraduationData graduationData = new GraduationData();
    @JsonDeserialize(as = GraduationStatus.class)
    private GraduationStatus graduationStatus = new GraduationStatus();

    @JsonDeserialize(as = Pen.class)
    public Pen getPen() {
        return pen == null ? NULL : pen;
    }

    public void setPen(Pen value) {
        this.pen = value;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? "" : firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName == null ? "" : middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? "" : lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String value) {
        this.gender = value;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    @JsonFormat(pattern= ReportApiConstants.BIRTHDATE_FORMAT)
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate value) {
        this.birthdate = value;
    }

    @JsonDeserialize(as = Address.class)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address value) {
        this.address = value;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String value) {
        this.grade = value;
    }

    public String getStudStatus() {
        return studStatus;
    }

    public void setStudStatus(String value) {
        this.studStatus = value;
    }

    public String getSccDate() {
        return sccDate;
    }

    public void setSccDate(String value) {
        this.sccDate = value;
    }

    public String getMincodeGrad() {
        return mincodeGrad;
    }

    public void setMincodeGrad(String value) {
        this.mincodeGrad = value;
    }

    public String getEnglishCert() {
        return englishCert;
    }

    public void setEnglishCert(String value) {
        this.englishCert = value;
    }

    public String getFrenchCert() {
        return frenchCert;
    }

    public void setFrenchCert(String value) {
        this.frenchCert = value;
    }

    public String getGradProgram() {
        return gradProgram;
    }

    public void setGradProgram(String gradProgram) {
        this.gradProgram = gradProgram == null ? "" : gradProgram;
    }

    public String getGradReqYear() {
        return gradReqYear;
    }

    public void setGradReqYear(String gradReqYear) {
        this.gradReqYear = gradReqYear;
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

    @JsonDeserialize(using = OtherProgramListDeserializer.class)
    public List<OtherProgram> getOtherProgramParticipation() {
        return otherProgramParticipation;
    }

    public void setOtherProgramParticipation(List<OtherProgram> otherProgramParticipation) {
        this.otherProgramParticipation = otherProgramParticipation;
    }

    @JsonDeserialize(using = NonGradReasonListDeserializer.class)
    public List<NonGradReason> getNonGradReasons() {
        return nonGradReasons;
    }

    public void setNonGradReasons(List<NonGradReason> nonGradReasons) {
        this.nonGradReasons = nonGradReasons;
    }

    public GraduationData getGraduationData() {
        return graduationData;
    }

    public void setGraduationData(GraduationData graduationData) {
        this.graduationData = graduationData;
    }

    public GraduationStatus getGraduationStatus() {
        return graduationStatus;
    }

    public void setGraduationStatus(GraduationStatus graduationStatus) {
        this.graduationStatus = graduationStatus;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getPen().equals(student.getPen()) && getFirstName().equals(student.getFirstName()) && getMiddleName().equals(student.getMiddleName()) && getLastName().equals(student.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPen(), getFirstName(), getMiddleName(), getLastName());
    }
}
