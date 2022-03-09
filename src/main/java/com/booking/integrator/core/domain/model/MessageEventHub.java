package com.booking.integrator.core.domain.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageEventHub implements Serializable {
	private static final long serialVersionUID = 3876427859020382997L;

	private String eventName;
	private String message;

	public MessageEventHub(String eventName, String message) {
		this.eventName = eventName;
		this.message = message;
	}
}