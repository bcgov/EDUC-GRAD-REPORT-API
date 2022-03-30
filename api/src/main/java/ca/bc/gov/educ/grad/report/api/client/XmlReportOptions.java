package ca.bc.gov.educ.grad.report.api.client;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.io.Serializable;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class XmlReportOptions implements Serializable {

	private boolean cacheReport;
	private String convertTo;
	private boolean overwrite;
	private String reportName;
	private String reportFile;

	public XmlReportOptions() {
	}

}
