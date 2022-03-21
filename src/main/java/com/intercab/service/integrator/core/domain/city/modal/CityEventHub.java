package com.intercab.service.integrator.core.domain.city.modal;

import java.io.Serializable;

import com.intercab.service.integrator.core.domain.ProcessDataEventHub;

public class CityEventHub implements Serializable {
	private static final long serialVersionUID = 8421348226531726174L;

	private City businessObject;
	private ProcessDataEventHub processData;

	public City getBusinessObject() {
		return businessObject;
	}

	public void setBusinessObject(City businessObject) {
		this.businessObject = businessObject;
	}

	public ProcessDataEventHub getProcessData() {
		return processData;
	}

	public void setProcessData(ProcessDataEventHub processData) {
		this.processData = processData;
	}
}