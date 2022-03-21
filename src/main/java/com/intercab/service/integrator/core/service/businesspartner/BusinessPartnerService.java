package com.intercab.service.integrator.core.service.businesspartner;

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
import com.intercab.service.integrator.core.application.domains.DomainPartnerRole;
import com.intercab.service.integrator.core.application.domains.DomainStatus;
import com.intercab.service.integrator.core.application.mappers.businesspartner.BusinessPartnerMapper;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerEventHub;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerRequest;
import com.intercab.service.integrator.core.domain.businesspartner.validator.BusinessPartnerValidator;
import com.intercab.service.integrator.core.domain.log.modal.BusinessPartnerLog;
import com.intercab.service.integrator.core.service.log.ILogService;
import com.intercab.service.integrator.core.service.token.ITokenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
@Service
public class BusinessPartnerService implements IBusinessPartnerService {
	private final Logger logger = LoggerFactory.getLogger(BusinessPartnerService.class);

	@Autowired
	private ILogService logService;

	@Autowired
	private ITokenService tokenService;

	@Autowired
	private Environment env;

	@Override
	public void processData(String message) {
		this.logger.info("Globe-Business-Partner - Process Message");

		BusinessPartnerLog businessPartnerLog = new BusinessPartnerLog(new Date(), message);
		Integer countryCod = Integer.valueOf(KeyVaultUtils.getSecretValue(this.env, "country.cod.brazil"));

		try {
			Gson gson = new Gson();
			BusinessPartnerEventHub businessPartner = gson.fromJson(message, BusinessPartnerEventHub.class);
			businessPartnerLog.setAction(businessPartner.getProcessData().getChangeType());

			this.prepareToSend(businessPartner, businessPartnerLog, countryCod);
			this.logger.info("Globe-Business-Partner - Integrated Message");
		} catch (JsonSyntaxException error) {
			List<LogMoreDetails> errorList = new ArrayList<LogMoreDetails>();
			errorList.add(new LogMoreDetails(DomainStatus.UNPROCESSED.getDescription(), error.getMessage()));

			businessPartnerLog.setError(errorList);
			businessPartnerLog.setStatus(DomainStatus.UNPROCESSED.getDescription());

			this.logService.save(this.env.getProperty("error.id"), DomainStatus.UNPROCESSED.getDescription(), DomainStatus.UNPROCESSED.getDescription(), error.getStackTrace(), businessPartnerLog);

			this.logger.info("Globe-Business-Partner - Exception found and sent to CosmosDB - {}", businessPartnerLog.getError());
		}
	}

	private void prepareToSend(BusinessPartnerEventHub businessPartnerEventHub, BusinessPartnerLog log, Integer countryCod) {
		try {
			List<DomainPartnerRole> partnerRoleList = BusinessPartnerValidator.partnerRole(businessPartnerEventHub, log);

			BusinessPartnerValidator.request(businessPartnerEventHub, log, countryCod, partnerRoleList);
			log.setPartnerId(Long.valueOf(businessPartnerEventHub.getBusinessObject().getCode()));

			for (DomainPartnerRole domainPartnerRole : partnerRoleList) {
				BusinessPartnerRequest request = BusinessPartnerMapper.createRequestFromServiceBus(businessPartnerEventHub, countryCod, domainPartnerRole);
				this.sendToService(request, log, domainPartnerRole);
			}
		} catch (ApplicationBusinessException error) {
			this.logService.save(this.env.getProperty("error.id"), DomainStatus.UNPROCESSED.getDescription(), error.getLocalizedMessage(), error.getStackTrace(), log);
		}
	}

	private void sendToService(BusinessPartnerRequest request, BusinessPartnerLog log, DomainPartnerRole domainPartnerRole) {
		try {
			URL url = new URL(this.env.getProperty(domainPartnerRole.getAttribute()));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("locale", "en");
			con.setRequestProperty("Consumer-Key", this.env.getProperty("consumerkey"));
			con.setRequestProperty("Authorization", tokenService.getToken());
			con.setDoOutput(true);

			ObjectMapper mapper = new ObjectMapper();
			String jsonInputString = mapper.writeValueAsString(request);

			try (OutputStream os = con.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			BusinessPartnerValidator.response(con, log);
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