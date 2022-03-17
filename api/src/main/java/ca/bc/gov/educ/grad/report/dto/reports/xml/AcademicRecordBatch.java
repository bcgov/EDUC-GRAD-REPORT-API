package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicRecordBatch { 
	private HighSchoolTranscript HighSchoolTranscript;
	private String AcRecBat;
	private String xsi;
	private String schemaLocation;
	private String text;
}
