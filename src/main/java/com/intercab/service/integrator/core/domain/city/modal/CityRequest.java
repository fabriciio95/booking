package com.intercab.service.integrator.core.domain.city.modal;

import java.io.Serializable;
import java.util.List;

import com.intercab.service.integrator.core.domain.ProcessDataRequest;

public class CityRequest implements Serializable {
	private static final long serialVersionUID = 4754614788605712097L;

	private String name;
	private Long busuCityId;
	private Long busuStateId;
	private Long busuLocation;
	private Long busuIbgeCode;
	private ProcessDataRequest processData;
	private List<CityLocation> location;
	private List<CityNaming> naming;
	private String validFrom;
	private String validTo;

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

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public List<CityLocation> getLocation() {
		return location;
	}

	public void setLocation(List<CityLocation> location) {
		this.location = location;
	}

	public List<CityNaming> getNaming() {
		return naming;
	}

	public void setNaming(List<CityNaming> naming) {
		this.naming = naming;
	}
}