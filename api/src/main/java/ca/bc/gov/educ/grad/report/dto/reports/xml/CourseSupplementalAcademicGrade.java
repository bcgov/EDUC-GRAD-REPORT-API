package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class CourseSupplementalAcademicGrade {
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<CourseSupplementalGrade> CourseSupplementalGrade = new ArrayList<>();

	public CourseSupplementalAcademicGrade(CourseSupplementalGrade ... courseSupplementalGrade) {
		CourseSupplementalGrade = Arrays.asList(courseSupplementalGrade);
	}
}