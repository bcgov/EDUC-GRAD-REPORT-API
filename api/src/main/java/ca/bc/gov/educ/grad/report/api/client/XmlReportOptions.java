package ca.bc.gov.educ.grad.report.api.client;

import lombok.Data;

import java.io.Serializable;

@Data

public class XmlReportOptions implements Serializable {

	private boolean cacheReport;
	private String convertTo;
	private boolean overwrite;
	private String reportName;
	private String reportFile;

	public XmlReportOptions() {
	}

}
