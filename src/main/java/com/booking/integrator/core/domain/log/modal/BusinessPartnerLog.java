package com.booking.integrator.core.domain.log.modal;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessPartnerLog implements Serializable {
	private static final long serialVersionUID = -3227800601023534036L;
	
	private Long partnerId;
	private Date startDate;
	private Date endDate;
	private String action;
	private Object data;
	private Object error;
	private String status;

	public BusinessPartnerLog(Date startDate, String strData) {
		this.startDate = startDate;
		this.data = strData;
	}

}