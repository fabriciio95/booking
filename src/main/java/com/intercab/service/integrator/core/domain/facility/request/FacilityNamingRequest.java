package com.intercab.service.integrator.core.domain.facility.request;

import java.io.Serializable;

public class FacilityNamingRequest implements Serializable {
	private static final long serialVersionUID = 4076434110558823252L;
	
	private String code;
	private String name;
	
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
}