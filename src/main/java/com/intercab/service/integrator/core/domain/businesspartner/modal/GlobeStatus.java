package com.intercab.service.integrator.core.domain.businesspartner.modal;

public enum GlobeStatus {
	OPENED("OPENED"), CANCELLED("CANCELLED"), WAITLISTED("WAITLISTED"),
	APPROVED ("APPROVED"), REJECTED("REJECTED");

	private String description;

	private GlobeStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}