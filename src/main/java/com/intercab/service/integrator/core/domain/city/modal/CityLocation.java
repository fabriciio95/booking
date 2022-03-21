package com.intercab.service.integrator.core.domain.city.modal;

import java.io.Serializable;

public class CityLocation implements Serializable {
	private static final long serialVersionUID = -7544460784419559599L;

	private String code;
	private Long version;
	private String effectiveFrom;
	private String effectiveTo;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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