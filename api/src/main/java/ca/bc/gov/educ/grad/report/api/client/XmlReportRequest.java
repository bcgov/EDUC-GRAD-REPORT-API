package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;


@JsonPropertyOrder({ "options", "data"})
public class XmlReportRequest implements Serializable {

	private XmlReportData data;
	private XmlReportOptions options;

	@JsonProperty("data")
	public XmlReportData getData() {
		return data;
	}
	public void setData(XmlReportData data) {
		this.data = data;
	}

	@JsonProperty("options")
	public XmlReportOptions getOptions() {
		return options;
	}
	public void setOptions(XmlReportOptions options) {
		this.options = options;
	}

}
