package com.intercab.service.integrator.core.service.state;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.intercab.exception.core.ApplicationBusinessException;
import com.intercab.keyvault.KeyVaultUtils;
import com.intercab.log.core.model.LogMoreDetails;
import com.intercab.service.integrator.core.application.domains.DomainStatus;
import com.intercab.service.integrator.core.application.mappers.state.StateMapper;
import com.intercab.service.integrator.core.domain.log.modal.StateLog;
import com.intercab.service.integrator.core.domain.state.modal.StateEventHub;
import com.intercab.service.integrator.core.domain.state.modal.StateRequest;
import com.intercab.service.integrator.core.domain.state.modal.StateRequestForGeneralRegister;
import com.intercab.service.integrator.core.domain.state.validator.StateValidator;
import com.intercab.service.integrator.core.service.log.ILogService;
import com.intercab.service.integrator.core.service.token.ITokenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class StateService implements IStateService {
	private final Logger logger = LoggerFactory.getLogger(StateService.class);

	@Autowired
	private ILogService logService;

	@Autowired
	private ITokenService tokenService;

	@Autowired
	private Environment env;

	@Override
	public void processData(String message) {
		this.logger.info("Globe-State - Process Message");

		StateLog log = new StateLog(new Date(), message);

		try {
			Gson gson = new Gson();
			StateEventHub state = gson.fromJson(message, StateEventHub.class);
			log.setAction(state.getProcessData().getChangeType());

			this.sendToService(state, log);
			this.sendToServiceGeneralRegister(state, log);

			this.logger.info("Globe-State - Integrated Message");
		} catch (JsonSyntaxException error) {
			List<LogMoreDetails> errorList = new ArrayList<LogMoreDetails>();
			errorList.add(new LogMoreDetails(DomainStatus.UNPROCESSED.getCode(), error.getMessage()));

			log.setError(errorList);
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());

			this.logService.save(this.env.getProperty("error.id"), DomainStatus.UNPROCESSED.getDescription(), DomainStatus.UNPROCESSED.getDescription(), error.getStackTrace(), log);

			this.logger.info("Globe-State - Exception found and sent to CosmosDB - {}", log.getError());
		}
	}

	private void sendToService(StateEventHub stateEventHub, StateLog log) {
		try {
			StateValidator.request(stateEventHub, log);

			StateRequest request = StateMapper.createRequestFromServiceBus(stateEventHub);

			URL url = new URL(KeyVaultUtils.getSecretValue(this.env, "intercab.service.state.integrator.url"));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Consumer-Key", this.env.getProperty("consumerkey"));
			con.setRequestProperty("Authorization", tokenService.getToken());
			con.setRequestProperty("locale", "en");

			con.setDoOutput(true);

			ObjectMapper mapper = new ObjectMapper();
			String jsonInputString = mapper.writeValueAsString(request);

			try (OutputStream os = con.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			StateValidator.response(con, log);
		} catch (ApplicationBusinessException error) {
			this.logService.save(this.env.getProperty("error.id"), DomainStatus.UNPROCESSED.getDescription(), error.getLocalizedMessage(), error.getStackTrace(), log);
		} catch (Exception error) {
			List<LogMoreDetails> errorList = new ArrayList<LogMoreDetails>();
			errorList.add(new LogMoreDetails(DomainStatus.UNPROCESSED.getDescription(), error.getMessage()));

			log.setError(errorList);
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());

			this.logService.save(this.env.getProperty("error.id"), DomainStatus.UNPROCESSED.getDescription(), error.getLocalizedMessage(), error.getStackTrace(), log);
		}
	}

	private void sendToServiceGeneralRegister(StateEventHub state, StateLog log) {
		try {
			StateValidator.requestForGeneralRegister(state, log);

			StateRequestForGeneralRegister request = StateMapper.createRequestFromServiceBusForGeneralRegister(state);

			URL url = new URL(KeyVaultUtils.getSecretValue(this.env, "intercab.service.state.generalregister.integrator.url"));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Consumer-Key", this.env.getProperty("consumerkey"));
			con.setRequestProperty("Authorization", tokenService.getToken());
			con.setRequestProperty("locale", "en");

			con.setDoOutput(true);

			ObjectMapper mapper = new ObjectMapper();
			String jsonInputString = mapper.writeValueAsString(request);

			try (OutputStream os = con.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			StateValidator.response(con, log);
		} catch (ApplicationBusinessException error) {
			this.logService.save(this.env.getProperty("error.id"), DomainStatus.UNPROCESSED.getDescription(), error.getLocalizedMessage(), error.getStackTrace(), log);
		} catch (Exception error) {
			List<LogMoreDetails> errorList = new ArrayList<LogMoreDetails>();
			errorList.add(new LogMoreDetails(DomainStatus.UNPROCESSED.getDescription(), error.getMessage()));

			log.setError(errorList);
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());

			this.logService.save(this.env.getProperty("error.id"), DomainStatus.UNPROCESSED.getDescription(), error.getLocalizedMessage(), error.getStackTrace(), log);
		}
	}
}