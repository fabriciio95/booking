package com.booking.integrator.core.application.domains;

import lombok.Getter;
import lombok.Setter;

public enum DomainStatus {
	APPLICATION_ERROR("0", "APPLICATION ERROR"), PROCESSED("1", "PROCCESSED"), UNPROCESSED("0", "UNPROCESSED"),
	FAIL_REGISTER_EVENT_PROCESSOR("2", "FAIL_REGISTER_EVENT_PROCESSOR");

	@Getter
	@Setter
	private String code;
	@Getter
	@Setter
	private String description;

	private DomainStatus(String code, String description) {
		this.code = code;
		this.description = description;
	}

}