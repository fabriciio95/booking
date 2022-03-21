package com.intercab.service.integrator.core.domain.vendor.modal;

import java.io.Serializable;

public class PartnerFunctions implements Serializable {
	private static final long serialVersionUID = 8486775412794632865L;

	private long version;
	private String functionTypeCode;
	private String approvalStatus;
	private String effectiveDate;
	private String expiryDate;

	public PartnerFunctions() {
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getFunctionTypeCode() {
		return functionTypeCode;
	}

	public void setFunctionTypeCode(String functionTypeCode) {
		this.functionTypeCode = functionTypeCode;
	}

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
}