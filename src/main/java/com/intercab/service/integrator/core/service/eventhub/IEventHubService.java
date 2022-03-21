package com.intercab.service.integrator.core.service.eventhub;

import org.springframework.stereotype.Component;

import com.intercab.service.integrator.core.domain.MessageEventHub;

@Component
public interface IEventHubService {
	void processMessage(MessageEventHub messageEventHub);
}