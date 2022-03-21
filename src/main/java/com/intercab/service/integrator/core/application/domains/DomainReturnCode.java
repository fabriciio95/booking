package com.intercab.service.integrator.core.application.domains;

public enum DomainReturnCode {
	SUCCESFUL_OPERATION("SUCCESS", "Operation successfully executed."),
	EMPTY_ATTRIBUTE("MADATORY FIELD", "The required attribute {0} is empty."),
	INVALID_DATA("INVALID DATA", "The date entered for {0} is invalid"),
	COUNTRY_COD_IS_NOT_BRAZIL("COUNTRY COD", "The Country Code informed {0} is not Brazil"),
	TERRITORY_NUMBER_INVALID("TERRITORY NUMBER INVALID", "The territory number is not from Brazil"),
	INVALID_IDENTIFICATION("INVALID IDENTIFICATION", "invalid identification for {0}."),
	FAILURE_COMUNICATION_GET_TOKEN_MICROSOFT("FAILURE COMUNICATION GET TOKEN MICROSOFT", "Failed to communicate with microsoft service"),
	DATA_NOT_BUSINESS_PARTNER("DATA NOT BUSINESS PARTNER", "Data not corresponding to a Business Partner");

	private String code;
	private String description;

	private DomainReturnCode(String code, String description) {
		this.setCode(code);
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}