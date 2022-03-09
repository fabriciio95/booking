package com.booking.integrator.core.consumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.booking.integrator.core.application.domains.DomainStatus;
import com.booking.integrator.core.domain.log.modal.ErrorLog;
import com.booking.integrator.core.service.log.ILogService;
import com.intercab.log.core.model.LogMoreDetails;
import com.microsoft.applicationinsights.boot.dependencies.apachecommons.lang3.StringUtils;
import com.microsoft.azure.eventhubs.ConnectionStringBuilder;
import com.microsoft.azure.eventprocessorhost.EventProcessorHost;
import com.microsoft.azure.eventprocessorhost.EventProcessorHost.EventProcessorHostBuilder.ManagerStep;

import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import net.jodah.failsafe.event.ExecutionAttemptedEvent;

@Component
public class EventHubConsumer {

	private static final Logger LOGGER = LogManager.getLogger(EventHubConsumer.class);

	@Autowired
	private Environment env;

	@Autowired
	private EventProcessorFactory eventProcessorFactory;

	@Autowired
	private ILogService logService;

	@Value("${integrador.eventhub.globe.connstring}")
	private String connString;

	@Value("${integrador.eventhub.globe.consumergroupname}")
	private String consumerGroupName;

	@Value("${integrador.eventhub.globe.hostnameprefix}")
	private String hostNamePrefix;

	@Value("${integrador.eventhub.globe.storagecontainername}")
	private String storageContainerName;

	@Value("${integrador.eventhub.globe.storageconnstring}")
	private String storageConnectionString;

	@PostConstruct
	void setup() {
		final ConnectionStringBuilder connStrBuilder = new ConnectionStringBuilder(connString);
		registerEventHubConsumer(consumerGroupName, hostNamePrefix, storageConnectionString, storageContainerName,
				connStrBuilder);
	}

	private void registerEventHubConsumer(String consumerGroupName,
			String hostNamePrefix,
			String storageConnectionString,
			String storageContainerName,
			ConnectionStringBuilder connStr) {
		final EventProcessorHost eventProcessorHost = buildEventProcessorHost(consumerGroupName, hostNamePrefix,
				storageConnectionString, storageContainerName, connStr);

		final RetryPolicy<Object> retryPolicy = new RetryPolicy<>()
				.handle(InterruptedException.class, ExecutionException.class, CancellationException.class)
				.onRetry(e -> this.unregisterHostAndSendLog(eventProcessorHost, e)).withDelay(Duration.ofSeconds(1));

		Failsafe.with(retryPolicy).get(() -> startListenerEventHub(eventProcessorHost));
	}

	private void unregisterHostAndSendLog(final EventProcessorHost eventProcessorHost, final ExecutionAttemptedEvent<Object> e) {
		if (eventProcessorHost != null) {
			eventProcessorHost.unregisterEventProcessor();
		}

		LOGGER.info("Failure after registered: " + e.getLastFailure().getMessage());
		List<LogMoreDetails> errorList = new ArrayList<>();
		errorList.add(new LogMoreDetails(DomainStatus.FAIL_REGISTER_EVENT_PROCESSOR.getDescription(), "Failure after registered: " + e.toString()));
		ErrorLog log = new ErrorLog(DomainStatus.FAIL_REGISTER_EVENT_PROCESSOR.getDescription(), errorList);
		this.logService.save(this.env.getProperty("error.id"), "Failure after registered",
				e.getLastFailure().getMessage(), e.getLastFailure().getStackTrace(), log);
	}

	private CompletableFuture<Void> startListenerEventHub(final EventProcessorHost eventProcessorHost) {
		LOGGER.info("Registering host named " + eventProcessorHost.getHostName());
		return eventProcessorHost.registerEventProcessorFactory(eventProcessorFactory).whenComplete((unused, e) -> {
			if (e != null) {
				LOGGER.error("Failure while registering: " + e.toString());
				LOGGER.error("Restart the Service Integrator");
				List<LogMoreDetails> errorList = new ArrayList<>();
				errorList.add(new LogMoreDetails(DomainStatus.UNPROCESSED.getDescription(), "Failure while registering: " + e));
				ErrorLog log = new ErrorLog(DomainStatus.UNPROCESSED.getDescription(), errorList);
				logService.save(env.getProperty("error.id"),
						"Failure while registering: " + e.getMessage() + "Restart the Service Integrator",
						e.getCause() != null ? e.getCause().toString() : null, e.getStackTrace(), log);
			}
		});
	}

	private static EventProcessorHost buildEventProcessorHost(String consumerGroupName, String hostNamePrefix,
			String storageConnectionString, String storageContainerName, ConnectionStringBuilder connStr) {
		final String hostName = EventProcessorHost.createHostName(hostNamePrefix);
		final ManagerStep managerStep = EventProcessorHost.EventProcessorHostBuilder.newBuilder(hostName, consumerGroupName);
		return managerStep
				.useAzureStorageCheckpointLeaseManager(storageConnectionString, storageContainerName, StringUtils.EMPTY)
				.useEventHubConnectionString(connStr.toString()).build();
	}
}