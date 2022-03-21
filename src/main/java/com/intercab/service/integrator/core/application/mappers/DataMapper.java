package com.intercab.service.integrator.core.application.mappers;

import com.intercab.service.integrator.core.domain.ProcessDataRequest;
import com.intercab.service.integrator.core.domain.ProcessDataEventHub;

public class DataMapper {
	public static ProcessDataRequest createProcessDataFromRequest(ProcessDataEventHub processData) {
		ProcessDataRequest processDate = new ProcessDataRequest();
		processDate.setChangeType(processData.getChangeType());
		processDate.setVersionNumber(processData.getVersionNumber());

		return processDate;
	}
}