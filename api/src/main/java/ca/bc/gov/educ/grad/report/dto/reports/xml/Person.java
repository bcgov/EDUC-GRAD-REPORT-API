package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	@JacksonXmlProperty(localName = "SchoolAssignedPersonID")
	private String SchoolAssignedPersonID;
	@JacksonXmlProperty(localName = "AgencyIdentifier")
	private AgencyIdentifier AgencyIdentifier;
	@JacksonXmlProperty(localName = "Birth")
	private Birth Birth;
	@JacksonXmlProperty(localName = "Name")
	private Name Name;
	@JacksonXmlProperty(localName = "Contacts")
	private Contacts contacts;
	@JacksonXmlProperty(localName = "Gender")
	private Gender Gender;
	@JacksonXmlProperty(localName = "Deceased")
	private Deceased Deceased;
	@JacksonXmlProperty(localName = "NoteMessage")
	private String NoteMessage;
}