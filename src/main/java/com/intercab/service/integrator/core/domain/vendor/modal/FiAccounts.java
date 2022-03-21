package com.intercab.service.integrator.core.domain.vendor.modal;

import java.io.Serializable;

public class FiAccounts implements Serializable {
	private static final long serialVersionUID = 8421348221245795130L;

	private long version;
	private String territoryNumber;
	private String fiAccountNumber;
	private boolean isNonGlobe;
	private String effectiveDate;
	private String expiryDate;
	private String partnerRole;

	public FiAccounts() {
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getTerritoryNumber() {
		return territoryNumber;
	}

	public void setTerritoryNumber(String territoryNumber) {
		this.territoryNumber = territoryNumber;
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

	public void setNonGlobe(boolean nonGlobe) {
		isNonGlobe = nonGlobe;
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

	public String getPartnerRole() {
		return partnerRole;
	}

	public void setPartnerRole(String partnerRole) {
		this.partnerRole = partnerRole;
	}
}
