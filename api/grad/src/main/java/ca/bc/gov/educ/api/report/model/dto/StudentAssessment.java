package ca.bc.gov.educ.api.report.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class StudentAssessment {

    private String assessmentCode;
    private String assessmentName;
    private String sessionDate;
    private String gradReqMet;
    private String specialCase;
    private String exceededWriteFlag;
    private Double proficiencyScore;

    @Override
    public String toString() {
        return "StudentAssessment{" +
                "assessmentCode='" + assessmentCode + '\'' +
                ", assessmentName='" + assessmentName + '\'' +
                ", sessionDate='" + sessionDate + '\'' +
                ", gradReqMet='" + gradReqMet + '\'' +
                ", specialCase='" + specialCase + '\'' +
                ", exceededWriteFlag='" + exceededWriteFlag + '\'' +
                ", proficiencyScore=" + proficiencyScore +
                '}';
    }
}