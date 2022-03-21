package com.intercab.service.integrator.core.domain.businesspartner.modal;

import java.io.Serializable;

public class BusinessPartnerFiAccounts implements Serializable {
	private static final long serialVersionUID = -1972099381774346857L;

	private String effectiveDate;
	private String expiryDate;
	private String fiAccountNumber;
	private boolean isNonGlobe;
	private String partnerRole;
	private String territoryNumber;
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

	public String getFiAccountNumber() {
		return fiAccountNumber;
	}

	public void setFiAccountNumber(String fiAccountNumber) {
		this.fiAccountNumber = fiAccountNumber;
	}

	public boolean isNonGlobe() {
		return isNonGlobe;
	}

	public void setNonGlobe(boolean isNonGlobe) {
		this.isNonGlobe = isNonGlobe;
	}

	public String getPartnerRole() {
		return partnerRole;
	}

	public void setPartnerRole(String partnerRole) {
		this.partnerRole = partnerRole;
	}

	public String getTerritoryNumber() {
		return territoryNumber;
	}

	public void setTerritoryNumber(String territoryNumber) {
		this.territoryNumber = territoryNumber;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}