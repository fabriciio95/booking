package com.intercab.service.integrator.core.domain.state.modal;

import java.io.Serializable;

import com.intercab.service.integrator.core.domain.ProcessDataRequest;

public class StateRequest implements Serializable {
	private static final long serialVersionUID = -2981930484890604310L;

	private String code;
	private String name;
	private Long busuStateId;
	private Long busuCountryId;
	private ProcessDataRequest processData;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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