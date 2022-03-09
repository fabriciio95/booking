package com.booking.integrator.core.domain.log.modal;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonLog implements Serializable{
	private static final long serialVersionUID = -8463225146871150459L;
	
	private String action;
	private Object data;
	private Object error;
	private String status;
	
}
