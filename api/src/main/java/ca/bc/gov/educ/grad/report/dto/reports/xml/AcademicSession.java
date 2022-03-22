package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicSession { 
	private AcademicSessionDetail AcademicSessionDetail = new AcademicSessionDetail();
	private List<Course> Course = new ArrayList<>();
}
