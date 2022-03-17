package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization { 
	private String MutuallyDefined;
	private String OrganizationName;
	private Contacts Contacts;
	private int PSIS;
}