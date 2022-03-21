package com.intercab.service.integrator.core.domain.location.model;

import java.io.Serializable;
import java.util.Date;

public class LocationNamingRequest implements Serializable {
	private static final long serialVersionUID = 2805471563568211138L;

	private String code;
	private String name;
	private Date effectiveFrom;
	private Date effectiveTo;
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

	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
}