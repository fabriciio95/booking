package com.intercab.service.integrator.core.domain.businesspartner.modal;

import java.io.Serializable;

public class BusinessBoycottStatuses implements Serializable {
	private static final long serialVersionUID = -8527851103823706389L;

	private String effectiveDate;
	private String expiryDate;
	private String status;
	private Long version;

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}