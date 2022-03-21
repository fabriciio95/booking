package com.intercab.service.integrator.core.domain.location.model;

import java.io.Serializable;

public class LocationCsCode implements Serializable {
	private static final long serialVersionUID = 8977909439121747055L;

	private String ecosPortCode;
	private String effectiveFrom;
	private String effectiveTo;
	private Long version;

	public String getEcosPortCode() {
		return ecosPortCode;
	}

	public void setEcosPortCode(String ecosPortCode) {
		this.ecosPortCode = ecosPortCode;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}