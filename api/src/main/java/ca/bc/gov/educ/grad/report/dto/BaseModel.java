package ca.bc.gov.educ.grad.report.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {
	private String createdBy;	
	private Date createdTimestamp;	
	private String updatedBy;	
	private Date updatedTimestamp;
}
