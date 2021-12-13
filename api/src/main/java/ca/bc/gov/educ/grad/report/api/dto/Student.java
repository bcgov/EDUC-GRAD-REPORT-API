package ca.bc.gov.educ.grad.report.api.dto;

import ca.bc.gov.educ.grad.report.api.util.ReportApiUtils;
import lombok.Data;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

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
    private List<OtherProgram> otherProgramParticipation;

    private void setBirthdate(String birthdate) {
        try {
            this.birthdate = ReportApiUtils.parseDate(birthdate, "yyyy/MM/dd");
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String hasOtherProgram;
    private JRBeanCollectionDataSource otherProgramParticipationdataSource;

    public JRBeanCollectionDataSource getOtherProgramParticipationdataSource() {
        return new JRBeanCollectionDataSource(otherProgramParticipation, false);
    }
}
