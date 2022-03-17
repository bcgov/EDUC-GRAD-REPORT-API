package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address { 
	private String AddressLine;
	private String City;
	private String StateProvinceCode;
	private String PostalCode;
}
