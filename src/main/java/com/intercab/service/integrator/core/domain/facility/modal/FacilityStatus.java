package com.intercab.service.integrator.core.domain.facility.modal;

import java.io.Serializable;

import com.intercab.service.integrator.core.domain.facility.enums.DomainFacilityStatus;

public class FacilityStatus implements Serializable {
	private static final long serialVersionUID = -7586283535614223489L;

	private String effectiveFrom;
	private String effectiveTo;
	private DomainFacilityStatus status;
	private Long version;

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

	public DomainFacilityStatus getStatus() {
		return status;
	}

	public void setStatus(DomainFacilityStatus status) {
		this.status = status;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}


}