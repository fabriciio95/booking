package com.intercab.service.integrator.core.domain.location.model;

import java.io.Serializable;

public class LocationDetails implements Serializable {
	private static final long serialVersionUID = 6722560642692218308L;

	private String timezone;

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
}