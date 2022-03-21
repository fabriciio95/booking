package com.intercab.service.integrator.core.service.log;

import com.intercab.keyvault.KeyVaultUtils;
import com.intercab.log.core.mapper.LogMapper;
import com.intercab.log.core.service.MonitoringService;
import com.intercab.service.integrator.core.application.mappers.businesspartner.BusinessPartnerMapper;
import com.intercab.service.integrator.core.application.mappers.city.CityMapper;
import com.intercab.service.integrator.core.application.mappers.country.CountryMapper;
import com.intercab.service.integrator.core.application.mappers.state.StateMapper;
import com.intercab.service.integrator.core.domain.log.modal.BusinessPartnerLog;
import com.intercab.service.integrator.core.domain.log.modal.CityLog;
import com.intercab.service.integrator.core.domain.log.modal.CountryLog;
import com.intercab.service.integrator.core.domain.log.modal.FacilityLog;
import com.intercab.service.integrator.core.domain.log.modal.IntercabLog;
import com.intercab.service.integrator.core.domain.log.modal.StateLog;
import com.intercab.service.integrator.core.infrastructure.repository.log.LogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LogService extends MonitoringService implements ILogService {
	@Autowired
	private LogRepository logRepository;
	@Autowired
	private Environment env;

	@Async("threadPoolTaskExecutor")
	@Override
	public void save(String errorId, String errorTitle, String errorMessage, StackTraceElement[] stackTrace, Object moreDetails) {
		String evironment = this.env.getProperty("server.environment");
		String apiId = this.env.getProperty("api.id");
		String apiName = this.env.getProperty("api.name");

		if (moreDetails instanceof BusinessPartnerLog) {
			moreDetails = BusinessPartnerMapper.createMoreDetails((BusinessPartnerLog) moreDetails);
		} else if (moreDetails instanceof FacilityLog) {
			//moreDetails = FacilityMapper.createMoreDetails((FacilityLog) moreDetails);
		} else if (moreDetails instanceof CountryLog) {
			moreDetails = CountryMapper.createMoreDetails((CountryLog) moreDetails);
		} else if (moreDetails instanceof StateLog) {
			moreDetails = StateMapper.createMoreDetails((StateLog) moreDetails);
		} else if (moreDetails instanceof CityLog) {
			moreDetails = CityMapper.createMoreDetails((CityLog) moreDetails);
		}

		try {
			IntercabLog log = new IntercabLog(LogMapper.createLog(errorId, errorTitle, errorMessage, stackTrace, moreDetails, evironment, apiId, apiName));
			logRepository.save(log);
		} catch (Exception e) {
			e.getStackTrace();
		}

		try {
			String eventConnectionString = KeyVaultUtils.getSecretValue(this.env, this.env.getProperty("eventhub.monitoring.connectionstring"));

			IntercabLog log = new IntercabLog(LogMapper.createLog(errorId, errorTitle, errorMessage, stackTrace, moreDetails, evironment, apiId, apiName));
			this.sendMonitoring(eventConnectionString, log);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}