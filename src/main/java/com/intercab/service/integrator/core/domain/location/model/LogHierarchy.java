package com.intercab.service.integrator.core.domain.location.model;

import java.io.Serializable;

public class LogHierarchy implements Serializable {
	private static final long serialVersionUID = 8977909439121747055L;

	private String parent;
	private String effectiveFrom;
	private String effectiveTo;
	private Long version;

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
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