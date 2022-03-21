package com.intercab.service.integrator.core.domain.vendor.modal;

import java.io.Serializable;

public class ReferencingFiAccounts implements Serializable {
	private static final long serialVersionUID = 1348226742396534L;

	private long version;
	private String territoryNumber;
	private String fiAccountNumber;
	private String effectiveDate;

	public ReferencingFiAccounts() {
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

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}