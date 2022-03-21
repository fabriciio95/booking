package com.intercab.service.integrator.core.domain.businesspartner.modal;

import java.io.Serializable;

public class BusinessPartnerFunctions implements Serializable {
	private static final long serialVersionUID = 4293876004963179960L;

	private String approvalStatus;
	private String effectiveDate;
	private String expiryDate;
	private String functionTypeCode;
	private Long version;

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
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

	public String getFunctionTypeCode() {
		return functionTypeCode;
	}

	public void setFunctionTypeCode(String functionTypeCode) {
		this.functionTypeCode = functionTypeCode;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}