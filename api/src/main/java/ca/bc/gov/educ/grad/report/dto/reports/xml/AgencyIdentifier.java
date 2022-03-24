package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgencyIdentifier {
	@JacksonXmlProperty(localName = "AgencyAssignedID")
	private String AgencyAssignedID;
	@JacksonXmlProperty(localName = "AgencyCode")
	private String AgencyCode;
	@JacksonXmlProperty(localName = "StateProvinceCode")
	private String StateProvinceCode;
}
