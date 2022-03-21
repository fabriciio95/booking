package com.intercab.service.integrator.core.domain.facility.modal;

import java.io.Serializable;

import com.intercab.service.integrator.core.domain.facility.enums.DomainFacilityAccessMode;

public class FacilityAccess implements Serializable {
	private static final long serialVersionUID = -1928930985735385187L;
	
	private DomainFacilityAccessMode accessMode;
	private Long version;
	
	public DomainFacilityAccessMode getAccessMode() {
		return accessMode;
	}
	
	public void setAccessMode(DomainFacilityAccessMode accessMode) {
		this.accessMode = accessMode;
	}
	
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
}