package com.intercab.service.integrator.core.domain.facility.request;

import java.io.Serializable;
import java.util.Date;

import com.intercab.service.integrator.core.domain.facility.enums.DomainFacilityStatus;

public class FacilityStatusRequest implements Serializable {
	private static final long serialVersionUID = -7586283535614223489L;

	private Date effectiveFrom;
	private Date effectiveTo;
	private DomainFacilityStatus status;
	private Long version;

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