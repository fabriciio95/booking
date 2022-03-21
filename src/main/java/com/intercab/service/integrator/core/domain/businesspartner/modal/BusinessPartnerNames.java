package com.intercab.service.integrator.core.domain.businesspartner.modal;

import java.io.Serializable;

public class BusinessPartnerNames implements Serializable {
	private static final long serialVersionUID = 8138059764005102118L;

	private String partnerName1;
	private String partnerName2;
	private String partnerName3;
	private String partnerNameML;
	private String approvalStatus;
	private String effectiveDate;
	private String expiryDate;
	private Long version;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPartnerName1() {
		return partnerName1;
	}

	public void setPartnerName1(String partnerName1) {
		this.partnerName1 = partnerName1;
	}

	public String getPartnerName2() {
		return partnerName2;
	}

	public void setPartnerName2(String partnerName2) {
		this.partnerName2 = partnerName2;
	}

	public String getPartnerName3() {
		return partnerName3;
	}

	public void setPartnerName3(String partnerName3) {
		this.partnerName3 = partnerName3;
	}

	public String getPartnerNameML() {
		return partnerNameML;
	}

	public void setPartnerNameML(String partnerNameML) {
		this.partnerNameML = partnerNameML;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}