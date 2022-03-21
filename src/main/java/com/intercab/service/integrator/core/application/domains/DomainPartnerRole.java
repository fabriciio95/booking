package com.intercab.service.integrator.core.application.domains;

public enum DomainPartnerRole {
	CUSTOMER("910", "intercab.service.businesspartner.customer.url"),
	VENDOR("915", "intercab.service.businesspartner.vendor.url");

	private String code;
	private String attribute;

	private DomainPartnerRole(String code, String attribute) {
		this.setCode(code);
		this.setAttribute(attribute);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
}