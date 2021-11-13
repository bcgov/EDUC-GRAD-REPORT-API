package ca.bc.gov.educ.api.report.model.dto;

import ca.bc.gov.educ.api.report.util.ReportApiUtils;
import lombok.Data;

import java.text.ParseException;
import java.util.Date;

@Data
public class Student {
    private String pen;
    private String firstName;
    private String middleName;
    private String lastName;
    private String grade;
    private String gender;
    private Date birthdate;
    private String localId;
    private String program;

    private void setBirthdate(String birthdate) {
        try {
            this.birthdate = ReportApiUtils.parseDate(birthdate, "yyyy/MM/dd");
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
