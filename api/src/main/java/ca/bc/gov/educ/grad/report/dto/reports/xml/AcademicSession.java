package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
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

	public AcademicSessionDetail getAcademicSessionDetail() {
		return AcademicSessionDetail;
	}

	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Course> getCourse() {
		return Course;
	}
}
