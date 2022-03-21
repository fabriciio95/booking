package com.intercab.service.integrator.core.domain.facility.enums;

public enum DomainDayType {
	WEEKDAYS("WEEKDAYS", "Week Days"),
	WEEKEND1("WEEKEND1", "Weekend 1"),
	WEEKEND2("WEEKEND2", "Weekend 2"),
	PUBLIC_HOLIDAYS("PUBLIC_HOLIDAYS", "Public Holidays");
	
	private String code;
	private String description;

	private DomainDayType(String code, String description) {
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