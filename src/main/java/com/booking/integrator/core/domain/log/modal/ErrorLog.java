package com.booking.integrator.core.domain.log.modal;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorLog implements Serializable {
	private static final long serialVersionUID = -3335026474354654738L;
	private Object error;
	private String status;

	public ErrorLog(String status, Object error) {
		this.status = status;
		this.error = error;
	}
}