package com.intercab.service.integrator.core.application.mappers.state;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.intercab.log.core.model.LogMoreDetails;
import com.intercab.service.integrator.core.application.mappers.DataMapper;
import com.intercab.service.integrator.core.domain.log.modal.StateLog;
import com.intercab.service.integrator.core.domain.state.modal.StateEventHub;
import com.intercab.service.integrator.core.domain.state.modal.StateNaming;
import com.intercab.service.integrator.core.domain.state.modal.StateNamingRequest;
import com.intercab.service.integrator.core.domain.state.modal.StateRequest;
import com.intercab.service.integrator.core.domain.state.modal.StateRequestForGeneralRegister;

public class StateMapper {
	public static StateRequest createRequestFromServiceBus(StateEventHub stateEventHub) {
		StateRequest request = new StateRequest();
		request.setBusuStateId(Long.valueOf(stateEventHub.getBusinessObject().getCode()));
		request.setBusuCountryId(Long.valueOf(stateEventHub.getBusinessObject().getCountry()));
		getCurrentVersionName(request, stateEventHub.getBusinessObject().getNaming());
		request.setProcessData(DataMapper.createProcessDataFromRequest(stateEventHub.getProcessData()));

		return request;
	}

	public static StateRequestForGeneralRegister createRequestFromServiceBusForGeneralRegister(StateEventHub stateEventHub) {
		StateRequestForGeneralRegister request = new StateRequestForGeneralRegister();

		request.setBusuStateId(Long.valueOf(stateEventHub.getBusinessObject().getCode()));
		request.setBusuCountryId(Long.valueOf(stateEventHub.getBusinessObject().getCountry()));
		getNameList(request, stateEventHub.getBusinessObject().getNaming());
		request.setProcessData(DataMapper.createProcessDataFromRequest(stateEventHub.getProcessData()));

		return request;
	}

	public static void getNameList(StateRequestForGeneralRegister request, StateNaming[] namingArray) {
		List<StateNamingRequest> stateNamingRequestList = new ArrayList<StateNamingRequest>();

		for (int i = 0; i < namingArray.length; i++) {
			stateNamingRequestList.add(createStateNamingRequestFromStateNaming(namingArray[i]));
		}

		request.setNaming(stateNamingRequestList);
	}

	public static StateNamingRequest createStateNamingRequestFromStateNaming(StateNaming naming) {
		StateNamingRequest stateNamingRequest = new StateNamingRequest();

		stateNamingRequest.setCode(naming.getCode());
		stateNamingRequest.setName(naming.getName());
		stateNamingRequest.setEffectiveFrom(naming.getEffectiveFrom());
		stateNamingRequest.setEffectiveTo(naming.getEffectiveTo());

		return stateNamingRequest;
	}

	public static void getCurrentVersionName(StateRequest request, StateNaming[] namingArray) {
		Long version = 0L;

		for (int i = 0; i < namingArray.length; i++) {
			if (namingArray[i] != null && namingArray[i].getVersion() > version) {
				version = namingArray[i].getVersion();
				request.setCode(namingArray[i].getCode());
				request.setName(namingArray[i].getName());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static List<LogMoreDetails> createMoreDetails(StateLog stateLog) {
		Gson gson = new Gson();

		List<LogMoreDetails> list = new ArrayList<LogMoreDetails>();
		list.addAll((Collection<? extends LogMoreDetails>) stateLog.getError());
		list.add(new LogMoreDetails("DATA", gson.toJson(stateLog.getData())));

		return list;
	}
}