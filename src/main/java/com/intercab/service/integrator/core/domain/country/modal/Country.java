package com.intercab.service.integrator.core.domain.country.modal;

import java.io.Serializable;

public class Country implements Serializable {
	private static final long serialVersionUID = -7935951061993913089L;

	private String code;
	private CountryNaming[] naming;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CountryNaming[] getNaming() {
		return naming;
	}

	public void setNaming(CountryNaming[] naming) {
		this.naming = naming;
	}
}