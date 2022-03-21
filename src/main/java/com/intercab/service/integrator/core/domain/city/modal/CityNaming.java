package com.intercab.service.integrator.core.domain.city.modal;

import java.io.Serializable;

public class CityNaming implements Serializable {
	private static final long serialVersionUID = -7259663622573653324L;

	private String name;
	private Long version;
	private String effectiveFrom;
	private String effectiveTo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
	
	
}