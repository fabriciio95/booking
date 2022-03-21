package com.intercab.service.integrator.core.application.domains;

public enum DomainStatus {
	APPLICATION_ERROR("0", "APPLICATION ERROR"), PROCESSED("1", "PROCCESSED"), UNPROCESSED("0", "UNPROCESSED"),
	FAIL_REGISTER_EVENT_PROCESSOR("2", "FAIL_REGISTER_EVENT_PROCESSOR");

	private String code;
	private String description;

	private DomainStatus(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}