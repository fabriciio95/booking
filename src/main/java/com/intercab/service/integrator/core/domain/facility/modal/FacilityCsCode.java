package com.intercab.service.integrator.core.domain.facility.modal;

import java.io.Serializable;

public class FacilityCsCode implements Serializable {
	private static final long serialVersionUID = 5990838485007898286L;
	
	private String csCode;
	private String effectiveFrom;
	private String effectiveTo;
	private int version;
	
	public String getCsCode() {
		return csCode;
	}
	
	public void setCsCode(String csCode) {
		this.csCode = csCode;
	}
	
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
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
}