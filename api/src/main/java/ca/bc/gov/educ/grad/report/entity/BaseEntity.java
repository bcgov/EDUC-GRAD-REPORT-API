package ca.bc.gov.educ.grad.report.entity;

import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import jakarta.persistence.*;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {
	@Column(name = "CREATE_USER", nullable = true)
    private String createdBy;
	
	@Column(name = "CREATE_DATE", columnDefinition="datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Date createdTimestamp;
	
	@Column(name = "UPDATE_USER", nullable = true)
    private String updatedBy;
	
	@Column(name = "UPDATE_DATE", columnDefinition="datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Date updatedTimestamp;
	
	@PrePersist
	protected void onCreate() {
		if (StringUtils.isBlank(createdBy)) {
			this.createdBy = ReportRequestDataThreadLocal.getCurrentUser();
		}		
		if (StringUtils.isBlank(updatedBy)) {
			this.updatedBy = ReportRequestDataThreadLocal.getCurrentUser();
		}
		if (StringUtils.isBlank(createdBy)) {
			this.createdBy = "GRADUATION";
		}
		if (StringUtils.isBlank(updatedBy)) {
			this.updatedBy = "GRADUATION";
		}
		this.createdTimestamp = new Date(System.currentTimeMillis());
		this.updatedTimestamp = new Date(System.currentTimeMillis());

	}

	@PreUpdate
	protected void onPersist() {
		if (StringUtils.isBlank(createdBy)) {
			this.createdBy = ReportRequestDataThreadLocal.getCurrentUser();
		}
		if (StringUtils.isBlank(updatedBy)) {
			this.updatedBy = ReportRequestDataThreadLocal.getCurrentUser();
		}
		if (StringUtils.isBlank(createdBy)) {
			this.createdBy = "GRADUATION";
		}
		if (StringUtils.isBlank(updatedBy)) {
			this.updatedBy = "GRADUATION";
		}
		if (this.createdTimestamp == null) {
			this.createdTimestamp = new Date(System.currentTimeMillis());
		}
		this.updatedTimestamp = new Date(System.currentTimeMillis());
	}
}
