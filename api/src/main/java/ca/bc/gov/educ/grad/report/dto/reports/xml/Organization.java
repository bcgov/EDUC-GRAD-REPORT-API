package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
	@JacksonXmlProperty(localName = "MutuallyDefined")
	private String MutuallyDefined;
	@JacksonXmlProperty(localName = "OrganizationName")
	private String OrganizationName;
	@JacksonXmlProperty(localName = "Contacts")
	private Contacts Contacts;
	@JacksonXmlProperty(localName = "PSIS")
	private String PSIS;
}