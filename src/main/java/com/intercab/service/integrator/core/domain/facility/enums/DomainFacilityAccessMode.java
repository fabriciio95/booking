package com.intercab.service.integrator.core.domain.facility.enums;

public enum DomainFacilityAccessMode {
	RAIL("RAIL", "Rail"),
	FEEDER("FEEDER", "Feeder"),
	TRUCK("TRUCK", "Truck"),
	BARGE("BARGE", "Barge"),
	DEEP_SEA("DEEP_SEA", "Deep Sea");
	
	private String code;
	private String description;

	private DomainFacilityAccessMode(String code, String description) {
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