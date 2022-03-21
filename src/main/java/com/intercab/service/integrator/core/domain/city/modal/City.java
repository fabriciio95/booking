package com.intercab.service.integrator.core.domain.city.modal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class City implements Serializable {
	private static final long serialVersionUID = -55861415825924611L;

	private String code;
	private String state;
	private List<CityLocation> location;
	private String cityCodeBR;
	private List<CityNaming> naming;
	private Date validFrom;
	private Date validTo;

	public String getCode() {
		return code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<CityLocation> getLocation() {
		return location;
	}

	public void setLocation(List<CityLocation> location) {
		this.location = location;
	}

	public String getCityCodeBR() {
		return cityCodeBR;
	}

	public void setCityCodeBR(String cityCodeBR) {
		this.cityCodeBR = cityCodeBR;
	}

	public List<CityNaming> getNaming() {
		return naming;
	}

	public void setNaming(List<CityNaming> naming) {
		this.naming = naming;
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

}