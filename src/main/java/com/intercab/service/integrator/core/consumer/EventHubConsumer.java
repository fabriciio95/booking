package com.intercab.service.integrator.core.consumer;

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
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.intercab.keyvault.KeyVaultUtils;
import com.intercab.log.core.model.LogMoreDetails;
import com.intercab.service.integrator.core.application.domains.DomainStatus;
import com.intercab.service.integrator.core.commons.DomainEnvVariable;
import com.intercab.service.integrator.core.domain.log.modal.ErrorLog;
import com.intercab.service.integrator.core.service.log.ILogService;
import com.intercab.utils.EnvironmentVariableUtils;
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

	@PostConstruct
	public void init() {
		if (Boolean.FALSE.equals(this.env.containsProperty("server.send.test")) || (Boolean.TRUE.equals(this.env.containsProperty("server.send.test")) && Boolean.FALSE.equals(Boolean.valueOf(this.env.getProperty("server.send.test"))))) {
			String secretEventHubConnString = EnvironmentVariableUtils.getEnv(DomainEnvVariable.INTERCAB_EVENTHUB_GLOBE_KEYVAULT_SECRET.getDescription(), DomainEnvVariable.INTERCAB_EVENTHUB_GLOBE_KEYVAULT_SECRET.getDefaultValue());
			String connString = KeyVaultUtils.getSecretValue(this.env, secretEventHubConnString);
			ConnectionStringBuilder connStrBuilder = new ConnectionStringBuilder(connString);
			String consumerGroupName = KeyVaultUtils.getSecretValue(this.env, "intercab.eventhub.globe.consumergroupname");
			String hostNamePrefix = KeyVaultUtils.getSecretValue(this.env, "intercab.eventhub.globe.hostnameprefix");
			String storageSecret = EnvironmentVariableUtils.getEnv(DomainEnvVariable.INTERCAB_AZURE_STORAGE_KEYVAULT_SECRET.getDescription(), DomainEnvVariable.INTERCAB_AZURE_STORAGE_KEYVAULT_SECRET.getDefaultValue());
			String storageConnectionString = KeyVaultUtils.getSecretValue(this.env, storageSecret);
			String storageContainerName = KeyVaultUtils.getSecretValue(this.env, "intercab.eventhub.globe.storagecontainername");

			registerEventHubConsumer(consumerGroupName, hostNamePrefix, storageConnectionString, storageContainerName, connStrBuilder);
		} else {
			LOGGER.info("InterCab configuration - Variable 'server.send.test' is defined in properties as 'true'.");
		}
	}

	private void registerEventHubConsumer(String consumerGroupName, String hostNamePrefix, String storageConnectionString, String storageContainerName, ConnectionStringBuilder connStr) {
		EventProcessorHost eventProcessorHost = null;

		RetryPolicy<Object> retryPolicy = new RetryPolicy<>()
				.handle(InterruptedException.class, ExecutionException.class, CancellationException.class)
				.onRetry(e -> this.unregisterHostAndSendlog(eventProcessorHost, e))
				.withDelay(Duration.ofSeconds(1));

		Failsafe.with(retryPolicy).get(() -> startListenerEventHub(eventProcessorHost, consumerGroupName, hostNamePrefix, storageConnectionString, storageContainerName, connStr));
	}

	private void unregisterHostAndSendlog(EventProcessorHost eventProcessorHost, ExecutionAttemptedEvent<Object> e) {
		if (eventProcessorHost != null) {
			eventProcessorHost.unregisterEventProcessor();
		}

		LOGGER.info("Failure after registered: " + e.getLastFailure().getMessage());
		List<LogMoreDetails> errorList = new ArrayList<>();
		errorList.add(new LogMoreDetails(DomainStatus.FAIL_REGISTER_EVENT_PROCESSOR.getDescription(), "Failure after registered: " + e.toString()));
		ErrorLog log = new ErrorLog(DomainStatus.FAIL_REGISTER_EVENT_PROCESSOR.getDescription(), errorList);
		this.logService.save(this.env.getProperty("error.id"), "Failure after registered", e.getLastFailure().getMessage(), e.getLastFailure().getStackTrace(), log);
	}

	private EventProcessorHost buildEventProcessorHost(String consumerGroupName, String hostNamePrefix, String storageConnectionString, String storageContainerName, ConnectionStringBuilder connStr) {
		String hostName = EventProcessorHost.createHostName(hostNamePrefix);
		ManagerStep managerStep = EventProcessorHost.EventProcessorHostBuilder.newBuilder(hostName, consumerGroupName);
		return managerStep.useAzureStorageCheckpointLeaseManager(storageConnectionString, storageContainerName, StringUtils.EMPTY).useEventHubConnectionString(connStr.toString()).build();
	}

	private CompletableFuture<Void> startListenerEventHub(EventProcessorHost eventProcessorHost, String consumerGroupName, String hostNamePrefix, String storageConnectionString, String storageContainerName, ConnectionStringBuilder connStr) throws InterruptedException, ExecutionException {
		eventProcessorHost = buildEventProcessorHost(consumerGroupName, hostNamePrefix, storageConnectionString, storageContainerName, connStr);
		LOGGER.info("Registering host named " + eventProcessorHost.getHostName());
		return eventProcessorHost.registerEventProcessorFactory(eventProcessorFactory).whenComplete((unused, e) -> {

			if (e != null) {
				LOGGER.error("Failure while registering: " + e.toString());
				LOGGER.error("Restart the Service Integrator");
				List<LogMoreDetails> errorList = new ArrayList<>();
				errorList.add(new LogMoreDetails(DomainStatus.UNPROCESSED.getDescription(), "Failure while registering: " + e.toString()));
				ErrorLog log = new ErrorLog(DomainStatus.UNPROCESSED.getDescription(), errorList);
				logService.save(env.getProperty("error.id"), "Failure while registering: " + e.getMessage() + "Restart the Service Integrator", e.getCause() != null ? e.getCause().toString() : null, e.getStackTrace(), log);
			}
		});
	}
}