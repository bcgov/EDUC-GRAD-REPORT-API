package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.SneakyThrows;

import java.io.Serializable;


@JsonPropertyOrder({ "options", "data"})
public class ReportRequest implements Serializable, Cloneable {

	private ReportData data;
	private ReportOptions options;

	@JsonProperty("data")
	public ReportData getData() {
		return data;
	}
	public void setData(ReportData data) {
		this.data = data;
	}

	@JsonProperty("options")
	public ReportOptions getOptions() {
		return options;
	}
	public void setOptions(ReportOptions options) {
		this.options = options;
	}

	@Override
	@SneakyThrows
	public ReportRequest clone() {
		return (ReportRequest) super.clone();
	}
}
