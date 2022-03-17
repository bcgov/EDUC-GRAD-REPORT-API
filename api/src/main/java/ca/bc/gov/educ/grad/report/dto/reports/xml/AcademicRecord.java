package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicRecord { 
	private School School;
	private StudentLevel StudentLevel;
	private AcademicAward AcademicAward;
	private List<AcademicSession> AcademicSession;
}
