package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSupplementalAcademicGrade {
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<CourseSupplementalGrade> CourseSupplementalGrade;
}