package com.booking.integrator.core.service.eventhub;

import org.springframework.stereotype.Component;

import com.booking.integrator.core.domain.model.MessageEventHub;

@Component
public interface IEventHubService {
	void processMessage(MessageEventHub messageEventHub);
}