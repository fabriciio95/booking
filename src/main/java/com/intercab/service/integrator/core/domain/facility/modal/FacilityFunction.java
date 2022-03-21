package com.intercab.service.integrator.core.domain.facility.modal;

import java.io.Serializable;

import com.intercab.service.integrator.core.domain.facility.enums.DomainFacilityFunctionType;

public class FacilityFunction implements Serializable {
	private static final long serialVersionUID = -7822838563205470617L;
	
	private DomainFacilityFunctionType facilityFunctionType;
	private Long version;
	
	public DomainFacilityFunctionType getFacilityFunctionType() {
		return facilityFunctionType;
	}
	
	public void setFacilityFunctionType(DomainFacilityFunctionType facilityFunctionType) {
		this.facilityFunctionType = facilityFunctionType;
	}
	
	public Long getVersion() {
		return version;
	}
	
	public void setVersion(Long version) {
		this.version = version;
	}
}