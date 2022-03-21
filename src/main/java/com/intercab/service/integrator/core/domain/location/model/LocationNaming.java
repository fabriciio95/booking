package com.intercab.service.integrator.core.domain.location.model;

import java.io.Serializable;

public class LocationNaming implements Serializable {
	private static final long serialVersionUID = 2805471563568211138L;

	private String code;
	private String name;
	private String effectiveFrom;
	private String effectiveTo;
	private Long version;

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