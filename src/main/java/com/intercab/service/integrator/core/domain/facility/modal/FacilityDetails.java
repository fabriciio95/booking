package com.intercab.service.integrator.core.domain.facility.modal;

import java.io.Serializable;
import java.util.List;

public class FacilityDetails implements Serializable {
	private static final long serialVersionUID = -6186191438442080829L;
	
	private List<FacilityAccess> access;
	private List<FacilityFunction> function;
	private List<FacilityHour> facilityHour;
	
	public List<FacilityAccess> getAccess() {
		return access;
	}
	
	public void setAccess(List<FacilityAccess> access) {
		this.access = access;
	}
	
	public List<FacilityFunction> getFunction() {
		return function;
	}
	
	public void setFunction(List<FacilityFunction> function) {
		this.function = function;
	}
	
	public List<FacilityHour> getFacilityHour() {
		return facilityHour;
	}
	
	public void setFacilityHour(List<FacilityHour> facilityHour) {
		this.facilityHour = facilityHour;
	}
}