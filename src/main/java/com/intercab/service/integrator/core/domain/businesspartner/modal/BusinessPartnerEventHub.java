package com.intercab.service.integrator.core.domain.businesspartner.modal;

import java.io.Serializable;

import com.intercab.service.integrator.core.domain.ProcessDataEventHub;

public class BusinessPartnerEventHub implements Serializable {
	private static final long serialVersionUID = -5959454717977010984L;

	private BusinessPartner businessObject;
	private ProcessDataEventHub processData;

	public BusinessPartner getBusinessObject() {
		return businessObject;
	}

	public void setBusinessObject(BusinessPartner businessObject) {
		this.businessObject = businessObject;
	}

	public ProcessDataEventHub getProcessData() {
		return processData;
	}

	public void setProcessData(ProcessDataEventHub processData) {
		this.processData = processData;
	}
}