package com.intercab.service.integrator.core.application.domains;

public enum DomainPartnerCode {
	DEPOT(6L, "DEPOT"),
	PORT(3l, "TERMINAL_OPERATOR"),
	TERMINAL(4L, "INLAND CONTAINER TERMINAL");

	private Long id;
	private String code;

	private DomainPartnerCode(Long id, String code) {
		this.setId(id);
		this.setCode(code);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static Long idToCode(String code) {
		for (DomainPartnerCode partnerCod : DomainPartnerCode.values()) {
			if (partnerCod.getCode().equals(code)) {
				return partnerCod.getId();
			}
		}

		return null;
	}
}