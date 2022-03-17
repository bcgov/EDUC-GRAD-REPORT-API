package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgencyIdentifier { 
	private int AgencyAssignedID;
	private String AgencyCode;
	private String StateProvinceCode;
}
