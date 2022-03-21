package com.intercab.service.integrator.core.domain.country.modal;

import java.io.Serializable;

import com.intercab.service.integrator.core.domain.ProcessDataRequest;

public class CountryRequest implements Serializable {
	private static final long serialVersionUID = 3900734006925750595L;

	private Long busuCountryId;
	private String name;
	private ProcessDataRequest processData;

	public Long getBusuCountryId() {
		return busuCountryId;
	}

	public void setBusuCountryId(Long busuCountryId) {
		this.busuCountryId = busuCountryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProcessDataRequest getProcessData() {
		return processData;
	}

	public void setProcessData(ProcessDataRequest processData) {
		this.processData = processData;
	}
}