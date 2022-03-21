package com.intercab.service.integrator.core.domain.state.modal;

import java.io.Serializable;

import com.intercab.service.integrator.core.domain.ProcessDataEventHub;

public class StateEventHub implements Serializable {
	private static final long serialVersionUID = 8421348226531726174L;

	private State businessObject;
	private ProcessDataEventHub processData;

	public State getBusinessObject() {
		return businessObject;
	}

	public void setBusinessObject(State businessObject) {
		this.businessObject = businessObject;
	}

	public ProcessDataEventHub getProcessData() {
		return processData;
	}

	public void setProcessData(ProcessDataEventHub processData) {
		this.processData = processData;
	}
}