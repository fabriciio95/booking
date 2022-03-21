package com.intercab.service.integrator.core.domain.common;

import java.io.Serializable;

import com.intercab.service.integrator.core.domain.ProcessDataEventHub;

public class EntityEventHub<T> implements Serializable {
	private static final long serialVersionUID = -6101340093814333330L;
	
	private T businessObject;
	private ProcessDataEventHub processData;

	public T getBusinessObject() {
		return businessObject;
	}

	public void setBusinessObject(T businessObject) {
		this.businessObject = businessObject;
	}

	public ProcessDataEventHub getProcessData() {
		return processData;
	}

	public void setProcessData(ProcessDataEventHub processData) {
		this.processData = processData;
	}
}