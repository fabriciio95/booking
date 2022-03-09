package com.booking.integrator.core.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microsoft.azure.eventprocessorhost.IEventProcessorFactory;
import com.microsoft.azure.eventprocessorhost.PartitionContext;

@Component
public class EventProcessorFactory implements IEventProcessorFactory<EventProcessor> {

	@Autowired
	private EventProcessor eventProcessor;

	@Override
	public EventProcessor createEventProcessor(PartitionContext context) {
		return this.eventProcessor;
	}
}