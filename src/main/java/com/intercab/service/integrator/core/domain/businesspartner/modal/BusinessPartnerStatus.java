package com.intercab.service.integrator.core.domain.businesspartner.modal;

public enum BusinessPartnerStatus {
	A("A", "Active"), I("I", "Inactive"), E("E", "Excluded"), P("P", "Pending");

	private String code;
	private String description;

	private BusinessPartnerStatus(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}
}