package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicSession {
	@JacksonXmlProperty(localName = "AcademicSessionDetail")
	private AcademicSessionDetail AcademicSessionDetail = new AcademicSessionDetail();
	@JacksonXmlProperty(localName = "Course")
	private List<Course> Course = new ArrayList<>();

	public AcademicSessionDetail getAcademicSessionDetail() {
		return AcademicSessionDetail;
	}

	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Course> getCourse() {
		return Course;
	}
}
