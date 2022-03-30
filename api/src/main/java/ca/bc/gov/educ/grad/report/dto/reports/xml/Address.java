package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	@JacksonXmlProperty(localName = "AddressLine")
	private String AddressLine;
	@JacksonXmlProperty(localName = "City")
	private String City;
	@JacksonXmlProperty(localName = "StateProvinceCode")
	private String StateProvinceCode;
	@JacksonXmlProperty(localName = "PostalCode")
	private String PostalCode;
}
