package com.intercab.service.integrator.core.domain.state.modal;

import java.io.Serializable;

public class State implements Serializable {
	private static final long serialVersionUID = -6761737554244913555L;

	private String code;
	private String country;
	private StateNaming[] naming;

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

	public StateNaming[] getNaming() {
		return naming;
	}

	public void setNaming(StateNaming[] naming) {
		this.naming = naming;
	}
}