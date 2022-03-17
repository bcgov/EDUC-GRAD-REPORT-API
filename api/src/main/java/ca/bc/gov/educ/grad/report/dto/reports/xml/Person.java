package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person { 
	private AgencyIdentifier AgencyIdentifier;
	private Birth Birth;
	private Name Name;
	private Gender Gender;
	private Deceased Deceased;
	private String NoteMessage;
}