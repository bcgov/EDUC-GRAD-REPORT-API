package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HighSchoolTranscript { 
	private TransmissionData TransmissionData;
	private Student Student;
	private String HSTrn;
	private String xsi;
	private String core;
	private String text;
}