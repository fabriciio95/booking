package com.intercab.service.integrator.core.domain.facility.enums;

public enum DomainFacilityHourType {
	GENERAL_HOURS("GENERAL_HOURS", "General Hours"),
	GATE_OPENING_HOURS("GATE_OPENING_HOURS", "Gate Opening Hours"),
	LIFTING_HOURS("LIFTING_HOURS", "Lifting Hours"),
	MORNING_SHIFT("MORNING_SHIFT", "Mornig Shift"),
	DAY_SHIFT("DAY_SHIFT", "Day Shift"),
	EVENING_SHIFT("EVENING_SHIFT", "Evening Shift"),
	NIGHT_SHIFT("NIGHT_SHIFT", "Night Shift");
	
	private String code;
	private String description;

	private DomainFacilityHourType(String code, String description) {
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