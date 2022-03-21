package com.intercab.service.integrator.core.domain.city.modal;

import java.io.Serializable;
import java.util.List;

import com.intercab.service.integrator.core.domain.ProcessDataRequest;

public class CityRequestService implements Serializable {

	private static final long serialVersionUID = -6184224087569466342L;

	private String name;
	private Long busuCityId;
	private Long busuStateId;
	private Long busuLocation;
	private Long busuIbgeCode;
	private ProcessDataRequest processData;
	private List<CityNaming> naming;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBusuCityId() {
		return busuCityId;
	}

	public void setBusuCityId(Long busuCityId) {
		this.busuCityId = busuCityId;
	}

	public Long getBusuStateId() {
		return busuStateId;
	}

	public void setBusuStateId(Long busuStateId) {
		this.busuStateId = busuStateId;
	}

	public Long getBusuLocation() {
		return busuLocation;
	}

	public void setBusuLocation(Long busuLocation) {
		this.busuLocation = busuLocation;
	}

	public Long getBusuIbgeCode() {
		return busuIbgeCode;
	}

	public void setBusuIbgeCode(Long busuIbgeCode) {
		this.busuIbgeCode = busuIbgeCode;
	}

	public ProcessDataRequest getProcessData() {
		return processData;
	}

	public void setProcessData(ProcessDataRequest processData) {
		this.processData = processData;
	}

	public List<CityNaming> getNaming() {
		return naming;
	}

	public void setNaming(List<CityNaming> naming) {
		this.naming = naming;
	}
}