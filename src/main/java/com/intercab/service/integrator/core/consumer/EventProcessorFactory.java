package com.intercab.service.integrator.core.consumer;

import com.intercab.service.integrator.core.service.eventhub.IEventHubService;
import com.intercab.service.integrator.core.service.log.ILogService;
import com.microsoft.azure.eventprocessorhost.IEventProcessorFactory;
import com.microsoft.azure.eventprocessorhost.PartitionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventProcessorFactory implements IEventProcessorFactory<EventProcessor> {
	@Autowired
	private IEventHubService eventHubService;

	@Autowired
	private ILogService logService;

	@Override
	public EventProcessor createEventProcessor(PartitionContext context) throws Exception {
		return new EventProcessor(eventHubService, logService);
	}
}