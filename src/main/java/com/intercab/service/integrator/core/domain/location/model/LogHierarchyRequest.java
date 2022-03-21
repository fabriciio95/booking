package com.intercab.service.integrator.core.domain.location.model;

import java.io.Serializable;
import java.util.Date;

public class LogHierarchyRequest implements Serializable {
	private static final long serialVersionUID = 8977909439121747055L;

	private String parent;
	private Date effectiveFrom;
	private Date effectiveTo;
	private Long version;

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}