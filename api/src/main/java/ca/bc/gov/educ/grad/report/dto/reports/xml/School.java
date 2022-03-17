package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class School { 
	private String OrganizationName;
	private LocalOrganizationID LocalOrganizationID;
	private Contacts Contacts;
}