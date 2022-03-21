package com.intercab.service.integrator.core.domain.vendor.modal;

import java.io.Serializable;

public class BoycottStatuses implements Serializable {
	private static final long serialVersionUID = 8421334856744498732L;

	private long version;
	private String status;
	private String effectiveDate;
	private String expiryDate;

	public BoycottStatuses() {
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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
}