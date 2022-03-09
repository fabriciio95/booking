package com.booking.integrator.core.service.businesspartner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.booking.integrator.core.application.domains.DomainStatus;
import com.booking.integrator.core.domain.log.modal.BusinessPartnerLog;
import com.booking.integrator.core.service.log.ILogService;
import com.google.gson.JsonSyntaxException;
import com.intercab.log.core.model.LogMoreDetails;

@Service
public class BusinessPartnerService implements IBusinessPartnerService {
	private final Logger logger = LoggerFactory.getLogger(BusinessPartnerService.class);

	@Autowired
	private ILogService logService;

	@Autowired
	private Environment env;

	@Override
	public void processData(String message) {
		this.logger.info("Globe-Business-Partner - Process Message");

		BusinessPartnerLog businessPartnerLog = new BusinessPartnerLog(new Date(), message);

		try {
			this.logger.info("Globe-Business-Partner - Integrated Message");
		} catch (JsonSyntaxException error) {
			List<LogMoreDetails> errorList = new ArrayList<LogMoreDetails>();
			errorList.add(new LogMoreDetails(DomainStatus.UNPROCESSED.getDescription(), error.getMessage()));

			businessPartnerLog.setError(errorList);
			businessPartnerLog.setStatus(DomainStatus.UNPROCESSED.getDescription());

			this.logService.save(this.env.getProperty("error.id"), DomainStatus.UNPROCESSED.getDescription(),
					DomainStatus.UNPROCESSED.getDescription(), error.getStackTrace(), businessPartnerLog);
			this.logger.info("Globe-Business-Partner - Exception found and sent to CosmosDB - {}",
					businessPartnerLog.getError());
		}
	}
}