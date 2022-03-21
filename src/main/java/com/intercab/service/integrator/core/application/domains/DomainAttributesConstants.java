package com.intercab.service.integrator.core.application.domains;

public enum DomainAttributesConstants {
	APPROVED("APPROVED"), BRAZIL("BRAZIL"), CNPJ("CNPJ"), CORPORATE_BRAZIL("CORPORATE_BRAZIL"),
	CPF("CPF"), DATA("DATA"), EXPIRY_DATE("expiryDate"), GLOBE_BP("Globe BP"), GLOBE_SFC("globeSfc"),
	ISENTO("ISENTO"), LE("LE"), P("P"), STATE_REGISTRATION_NO("STATE REGISTRATION NO");

	private String description;

	private DomainAttributesConstants(String description) {
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}