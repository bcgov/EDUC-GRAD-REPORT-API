package ca.bc.gov.educ.api.report.client.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class Student {
    private Pen pen;
    private String firstName;
    private String lastName;
    private String gender;
    private Date birthdate;
    private Address address;
    private String grade;
    private String studStatus;
    private String sccDate;
    private String mincodeGrad;
    private String englishCERT;
    private String frenchCERT;

    public Pen getPen() {
        return pen;
    }

    public void setPen(Pen value) {
        this.pen = value;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String value) {
        this.gender = value;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date value) {
        this.birthdate = value;
    }

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

    public String getEnglishCERT() {
        return englishCERT;
    }

    public void setEnglishCERT(String value) {
        this.englishCERT = value;
    }

    public String getFrenchCERT() {
        return frenchCERT;
    }

    public void setFrenchCERT(String value) {
        this.frenchCERT = value;
    }
}
