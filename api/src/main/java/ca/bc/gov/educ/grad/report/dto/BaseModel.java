package ca.bc.gov.educ.grad.report.dto;

import ca.bc.gov.educ.grad.report.utils.GradLocalDateTimeDeserializer;
import ca.bc.gov.educ.grad.report.utils.GradLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseModel {
	private String createdBy;	
	@JsonSerialize(using = GradLocalDateTimeSerializer.class)
	@JsonDeserialize(using = GradLocalDateTimeDeserializer.class)
	private LocalDateTime createdTimestamp;	
	private String updatedBy;	
	@JsonSerialize(using = GradLocalDateTimeSerializer.class)
	@JsonDeserialize(using = GradLocalDateTimeDeserializer.class)
	private LocalDateTime updatedTimestamp;
	public LocalDateTime getUpdatedTimestamp() {
		return updatedTimestamp == null ? LocalDateTime.now() : updatedTimestamp;
	}
	public LocalDateTime getCreatedTimestamp() {
		return createdTimestamp == null ? LocalDateTime.now() : createdTimestamp;
	}
}
