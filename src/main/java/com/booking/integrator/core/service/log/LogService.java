package com.booking.integrator.core.service.log;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.booking.integrator.core.domain.log.modal.BusinessPartnerLog;
import com.intercab.log.core.service.MonitoringService;

@Service
public class LogService extends MonitoringService implements ILogService {

	@Async("threadPoolTaskExecutor")
	@Override
	public void save(String errorId, String errorTitle, String errorMessage, StackTraceElement[] stackTrace,
			Object moreDetails) {

		if (moreDetails instanceof BusinessPartnerLog) {
		}
	}
}