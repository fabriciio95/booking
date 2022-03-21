package com.intercab.service.integrator.core.domain.state.modal;

import java.io.Serializable;
import java.util.List;

import com.intercab.service.integrator.core.domain.ProcessDataRequest;

public class StateRequestForGeneralRegister implements Serializable {
	private static final long serialVersionUID = 2421054582499474097L;

	private String code;
	private List<StateNamingRequest> naming;
	private Long busuStateId;
	private Long busuCountryId;
	private ProcessDataRequest processData;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<StateNamingRequest> getNaming() {
		return naming;
	}

	public void setNaming(List<StateNamingRequest> naming) {
		this.naming = naming;
	}

	public Long getBusuStateId() {
		return busuStateId;
	}

	public void setBusuStateId(Long busuStateId) {
		this.busuStateId = busuStateId;
	}

	public Long getBusuCountryId() {
		return busuCountryId;
	}

	public void setBusuCountryId(Long busuCountryId) {
		this.busuCountryId = busuCountryId;
	}

	public ProcessDataRequest getProcessData() {
		return processData;
	}

	public void setProcessData(ProcessDataRequest processData) {
		this.processData = processData;
	}
}