package com.intercab.service.integrator.core.domain.location.model;

import java.io.Serializable;
import java.util.Date;

public class LocationCsCodeRequest implements Serializable {
	private static final long serialVersionUID = 8977909439121747055L;

	private Integer ecosPortCode;
	private Date effectiveFrom;
	private Date effectiveTo;
	private Long version;

	public Integer getEcosPortCode() {
		return ecosPortCode;
	}

	public void setEcosPortCode(Integer ecosPortCode) {
		this.ecosPortCode = ecosPortCode;
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