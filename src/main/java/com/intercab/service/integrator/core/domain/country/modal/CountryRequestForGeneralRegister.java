package com.intercab.service.integrator.core.domain.country.modal;

import java.io.Serializable;
import java.util.List;

import com.intercab.service.integrator.core.domain.ProcessDataRequest;

public class CountryRequestForGeneralRegister implements Serializable {
	private static final long serialVersionUID = -5135366877705262346L;

	private Long busuCountryId;
	private List<CountryNamingRequest> naming;
	private ProcessDataRequest processData;

	public Long getBusuCountryId() {
		return busuCountryId;
	}

	public void setBusuCountryId(Long busuCountryId) {
		this.busuCountryId = busuCountryId;
	}

	public List<CountryNamingRequest> getNaming() {
		return naming;
	}

	public void setNaming(List<CountryNamingRequest> naming) {
		this.naming = naming;
	}

	public ProcessDataRequest getProcessData() {
		return processData;
	}

	public void setProcessData(ProcessDataRequest processData) {
		this.processData = processData;
	}
}