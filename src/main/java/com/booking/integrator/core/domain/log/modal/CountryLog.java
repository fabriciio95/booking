package com.booking.integrator.core.domain.log.modal;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryLog implements Serializable {
	private static final long serialVersionUID = -5365944178111670675L;

	private Long countryId;
	private Date startDate;
	private Date endDate;
	private String action;
	private Object data;
	private Object error;
	private String status;

	public CountryLog(Date startDate, String strData) {
		this.startDate = startDate;
		this.data = strData;
	}

}