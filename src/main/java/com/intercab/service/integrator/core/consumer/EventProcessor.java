package com.intercab.service.integrator.core.consumer;

import java.nio.charset.Charset;

import com.intercab.service.integrator.core.commons.DomainEnvVariable;
import com.intercab.service.integrator.core.domain.MessageEventHub;
import com.intercab.service.integrator.core.service.eventhub.IEventHubService;
import com.intercab.service.integrator.core.service.log.ILogService;
import com.intercab.utils.EnvironmentVariableUtils;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventprocessorhost.CloseReason;
import com.microsoft.azure.eventprocessorhost.IEventProcessor;
import com.microsoft.azure.eventprocessorhost.PartitionContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventProcessor implements IEventProcessor {
	private static final String EVENT_PROCESSOR_PARTITION = "EVENT_PROCESSOR: Partition ";
	private static final Logger LOGGER = LogManager.getLogger(EventProcessor.class);

	@Value("error.id")
	private String errorId;

	private IEventHubService eventHubService;
	private ILogService logService;
	private String propertiesFilter;

	public EventProcessor(IEventHubService eventHubService, ILogService logService) {
		this.eventHubService = eventHubService;
		this.logService = logService;
		this.propertiesFilter = EnvironmentVariableUtils.getEnv(DomainEnvVariable.INTERCAB_EVENTHUB_GLOBE_PROPERTIES_FILTER.getDescription(), DomainEnvVariable.INTERCAB_EVENTHUB_GLOBE_PROPERTIES_FILTER.getDefaultValue());
	}

	@Override
	public void onOpen(PartitionContext context) throws Exception {
		LOGGER.info(EVENT_PROCESSOR_PARTITION + context.getPartitionId() + " is opening");
	}

	@Override
	public void onClose(PartitionContext context, CloseReason reason) throws Exception {
		LOGGER.info(EVENT_PROCESSOR_PARTITION + context.getPartitionId() + " is closing for reason " + reason.toString());
	}

	@Override
	public void onError(PartitionContext context, Throwable error) {
		StringBuilder sb = new StringBuilder(100);
		sb.append(EVENT_PROCESSOR_PARTITION).append(context.getPartitionId()).append(" onError: ").append(error.toString());
		LOGGER.error(sb.toString());
		this.logService.save(this.errorId, error.toString(), sb.toString(), error.getStackTrace(), null);
	}

	@Override
	public void onEvents(PartitionContext context, Iterable<EventData> events) {
		LOGGER.info(EVENT_PROCESSOR_PARTITION + context.getPartitionId() + " got event batch");

		for (EventData receivedEvent : events) {
			try {
				LOGGER.info(EVENT_PROCESSOR_PARTITION + context.getPartitionId() + " checkpointing at " + receivedEvent.getSystemProperties().getOffset() + "," + receivedEvent.getSystemProperties().getSequenceNumber());
				MessageEventHub messageEventHub = createMessageEventHub(receivedEvent);
				this.eventHubService.processMessage(messageEventHub);

				context.checkpoint(receivedEvent).get();
			} catch (Exception e) {
				StringBuilder sb = new StringBuilder(100);
				sb.append("Checkpoint Error: Partition ").append(context.getPartitionId()).append(" checkpointing at ").append(receivedEvent.getSystemProperties().getOffset()).append(",").append(receivedEvent.getSystemProperties().getSequenceNumber());
				LOGGER.error("Processing failed for an event: " + e.toString());
				LOGGER.error(sb.toString());
				String json = new String(receivedEvent.getBytes(), Charset.defaultCharset());
				this.logService.save(this.errorId, e.toString(), sb.toString(), e.getStackTrace(), json);
			}
		}
	}

	private MessageEventHub createMessageEventHub(EventData receivedEvent) {
		if (receivedEvent.getBytes() != null) {
			String eventName = StringUtils.EMPTY;

			if (receivedEvent.getProperties().containsKey(this.propertiesFilter)) {
				eventName = String.valueOf(receivedEvent.getProperties().get(this.propertiesFilter));
			}

			String message = new String(receivedEvent.getBytes(), Charset.defaultCharset());

			return new MessageEventHub(eventName, message);
		}

		return null;
	}
}