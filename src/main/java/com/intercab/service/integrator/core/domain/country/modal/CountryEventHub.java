package com.intercab.service.integrator.core.domain.country.modal;

import java.io.Serializable;

import com.intercab.service.integrator.core.domain.ProcessDataEventHub;

public class CountryEventHub implements Serializable {
	private static final long serialVersionUID = -3085521980290233030L;

	private Country businessObject;
	private ProcessDataEventHub processData;

	public Country getBusinessObject() {
		return businessObject;
	}

	public void setBusinessObject(Country businessObject) {
		this.businessObject = businessObject;
	}

	public ProcessDataEventHub getProcessData() {
		return processData;
	}

	public void setProcessData(ProcessDataEventHub processData) {
		this.processData = processData;
	}
}