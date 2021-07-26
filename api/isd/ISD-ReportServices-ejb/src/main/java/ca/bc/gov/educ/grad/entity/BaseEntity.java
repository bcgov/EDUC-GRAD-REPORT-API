package ca.bc.gov.educ.grad.entity;

import ca.bc.gov.educ.grad.utils.AuditingUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {
	@Column(name = "CREATE_USER", nullable = true)
    private String createdBy;
	
	@Column(name = "CREATE_DATE", nullable = true)
    private Date createdTimestamp;
	
	@Column(name = "UPDATE_USER", nullable = true)
    private String updatedBy;
	
	@Column(name = "UPDATE_DATE", nullable = true)
    private Date updatedTimestamp;
	
	@PrePersist
	protected void onCreate() {
		if (StringUtils.isBlank(createdBy)) {
			this.createdBy = AuditingUtils.getCurrentUserId();
		}		
		if (StringUtils.isBlank(updatedBy)) {
			this.updatedBy = AuditingUtils.getCurrentUserId();
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
			this.createdBy = AuditingUtils.getCurrentUserId();
		}
		if (StringUtils.isBlank(updatedBy)) {
			this.updatedBy = AuditingUtils.getCurrentUserId();
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
