package com.intercab.service.integrator.core.domain.businesspartner.modal;

import java.io.Serializable;

public class BusinessPartnerReferencingFiAccounts implements Serializable {
	private static final long serialVersionUID = 7127650634017207029L;

	private String effectiveDate;
	private String fiAccountNumber;
	private String territoryNumber;
	private Long version;

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getFiAccountNumber() {
		return fiAccountNumber;
	}

	public void setFiAccountNumber(String fiAccountNumber) {
		this.fiAccountNumber = fiAccountNumber;
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