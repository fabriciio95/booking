package com.intercab.service.integrator.core.domain;

import java.io.Serializable;

public class MessageEventHub implements Serializable {
	private static final long serialVersionUID = 3876427859020382997L;

	private String eventName;
	private String message;

	public MessageEventHub(String eventName, String message) {
		this.eventName = eventName;
		this.message = message;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}