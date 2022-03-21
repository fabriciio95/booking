package com.intercab.service.integrator.core.domain.facility.request;

import java.io.Serializable;
import java.util.List;

public class FacilityDetailsRequest implements Serializable {
	private static final long serialVersionUID = -6186191438442080829L;

	private List<FacilityAccessRequest> access;
	private List<FacilityFunctionRequest> function;
	private List<FacilityHourRequest> facilityHour;

	public List<FacilityAccessRequest> getAccess() {
		return access;
	}

	public void setAccess(List<FacilityAccessRequest> access) {
		this.access = access;
	}

	public List<FacilityFunctionRequest> getFunction() {
		return function;
	}

	public void setFunction(List<FacilityFunctionRequest> function) {
		this.function = function;
	}

	public List<FacilityHourRequest> getFacilityHour() {
		return facilityHour;
	}

	public void setFacilityHour(List<FacilityHourRequest> facilityHour) {
		this.facilityHour = facilityHour;
	}
}