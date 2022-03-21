package com.intercab.service.integrator.core.domain.location.model;

import java.io.Serializable;
import java.util.List;

public class Location implements Serializable {
	private static final long serialVersionUID = 6722560642692218308L;

	private String code;
	private String country;
	private String state;
	private Boolean isPort;
	private String validFrom;
	private String validTo;
	private LocationDetails locationDetails;
	private List<LocationNaming> naming;
	private List<LocationCsCode> locationCsCode;
	private List<LogHierarchy> logHierarchy;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<LocationNaming> getNaming() {
		return naming;
	}

	public void setNaming(List<LocationNaming> naming) {
		this.naming = naming;
	}

	public List<LocationCsCode> getLocationCsCode() {
		return locationCsCode;
	}

	public void setLocationCsCode(List<LocationCsCode> locationCsCode) {
		this.locationCsCode = locationCsCode;
	}

	public Boolean getIsPort() {
		return isPort;
	}

	public void setIsPort(Boolean isPort) {
		this.isPort = isPort;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public LocationDetails getLocationDetails() {
		return locationDetails;
	}

	public void setLocationDetails(LocationDetails locationDetails) {
		this.locationDetails = locationDetails;
	}

	public List<LogHierarchy> getLogHierarchy() {
		return logHierarchy;
	}

	public void setLogHierarchy(List<LogHierarchy> logHierarchy) {
		this.logHierarchy = logHierarchy;
	}
}