package com.intercab.service.integrator.core.domain.location.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LocationRequest implements Serializable {
	private static final long serialVersionUID = -7702858785949196068L;

	private String locationNumber;
	private String countryCode;
	private String stateCode;
	private boolean portFlag;
	private Date validFrom;
	private Date validTo;
	private String timeZone;
	private List<LocationNamingRequest> naming;
	private List<LocationCsCodeRequest> locationCsCode;
	private List<LogHierarchyRequest> logHierarchy;

	public String getLocationNumber() {
		return locationNumber;
	}

	public void setLocationNumber(String locationNumber) {
		this.locationNumber = locationNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public boolean isPortFlag() {
		return portFlag;
	}

	public void setPortFlag(boolean portFlag) {
		this.portFlag = portFlag;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public List<LocationNamingRequest> getNaming() {
		return naming;
	}

	public void setNaming(List<LocationNamingRequest> naming) {
		this.naming = naming;
	}

	public List<LocationCsCodeRequest> getLocationCsCode() {
		return locationCsCode;
	}

	public void setLocationCsCode(List<LocationCsCodeRequest> locationCsCode) {
		this.locationCsCode = locationCsCode;
	}

	public List<LogHierarchyRequest> getLogHierarchy() {
		return logHierarchy;
	}

	public void setLogHierarchy(List<LogHierarchyRequest> logHierarchy) {
		this.logHierarchy = logHierarchy;
	}
}