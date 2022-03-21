package com.intercab.service.integrator.core.domain.facility.request;

import java.io.Serializable;

import com.intercab.service.integrator.core.domain.facility.enums.DomainFacilityHourType;

public class FacilityHourRequest implements Serializable {
	private static final long serialVersionUID = -3283218872866896591L;

	private DomainFacilityHourType facilityHourType;
	private Long version;

	public DomainFacilityHourType getFacilityHourType() {
		return facilityHourType;
	}

	public void setFacilityHourType(DomainFacilityHourType facilityHourType) {
		this.facilityHourType = facilityHourType;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}