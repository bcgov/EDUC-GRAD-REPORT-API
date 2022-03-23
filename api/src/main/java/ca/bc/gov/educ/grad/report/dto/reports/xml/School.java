package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
	private String OrganizationName;
	@JacksonXmlProperty(localName = "LocalOrganizationID")
	private LocalOrganizationID LocalOrganizationID;
	@JacksonXmlProperty(localName = "Contacts")
	private Contacts Contacts;
}