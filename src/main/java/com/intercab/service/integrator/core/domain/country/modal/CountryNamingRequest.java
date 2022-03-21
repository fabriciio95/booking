package com.intercab.service.integrator.core.domain.country.modal;

import java.io.Serializable;

public class CountryNamingRequest implements Serializable {
	private static final long serialVersionUID = 1370462785846071290L;

	private String code;
	private String name;
	private String effectiveFrom;
	private String effectiveTo;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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