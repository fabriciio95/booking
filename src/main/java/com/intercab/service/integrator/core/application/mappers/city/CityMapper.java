package com.intercab.service.integrator.core.application.mappers.city;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.intercab.log.core.model.LogMoreDetails;
import com.intercab.service.integrator.core.application.mappers.DataMapper;
import com.intercab.service.integrator.core.domain.city.modal.CityEventHub;
import com.intercab.service.integrator.core.domain.city.modal.CityNaming;
import com.intercab.service.integrator.core.domain.city.modal.CityRequest;
import com.intercab.service.integrator.core.domain.city.modal.CityRequestService;
import com.intercab.service.integrator.core.domain.log.modal.CityLog;
import com.intercab.utils.DateUtils;

public class CityMapper {
	
	public static CityRequestService createRequestFromServiceBus(CityEventHub cityEventHub) {
		
		CityRequestService request = new CityRequestService();
		
		request.setBusuCityId(Long.valueOf(cityEventHub.getBusinessObject().getCode()));

		if(cityEventHub.getBusinessObject().getState() != null){
			request.setBusuStateId(Long.valueOf(cityEventHub.getBusinessObject().getState()));
		}
		
		request.setProcessData(DataMapper.createProcessDataFromRequest(cityEventHub.getProcessData()));
		getCurrentVersionName(request, cityEventHub.getBusinessObject().getNaming());

		return request;
	}

	public static CityRequest createRequestGRFromServiceBus(CityEventHub cityEventHub) {
		
		CityRequest request = new CityRequest();
		
		request.setBusuCityId(Long.valueOf(cityEventHub.getBusinessObject().getCode()));//

		if(cityEventHub.getBusinessObject().getState() != null){
			request.setBusuStateId(Long.valueOf(cityEventHub.getBusinessObject().getState()));//
		}

		if(cityEventHub.getBusinessObject().getCityCodeBR() != null){
			request.setBusuIbgeCode(Long.valueOf(cityEventHub.getBusinessObject().getCityCodeBR()));//
		}

		request.setProcessData(DataMapper.createProcessDataFromRequest(cityEventHub.getProcessData()));//

		request.setLocation(cityEventHub.getBusinessObject().getLocation());//

		request.setNaming(cityEventHub.getBusinessObject().getNaming());//

		request.setValidFrom(DateUtils.dateToString(cityEventHub.getBusinessObject().getValidFrom(), DateUtils.YYYY_MM_DD));//

		request.setValidTo(DateUtils.dateToString(cityEventHub.getBusinessObject().getValidTo(), DateUtils.YYYY_MM_DD));//

		return request;
	}

	public static void getCurrentVersionName(CityRequestService request, List<CityNaming> namingList) {
		
		Optional<CityNaming> city = namingList.stream().max(Comparator.comparing(CityNaming::getVersion));
		
		if(city.isPresent()) {
			request.setName(city.get().getName());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<LogMoreDetails> createMoreDetails(CityLog stateLog) {
		
		Gson gson = new Gson();

		List<LogMoreDetails> list = new ArrayList<LogMoreDetails>();
		list.addAll((Collection<? extends LogMoreDetails>) stateLog.getError());
		list.add(new LogMoreDetails("DATA", gson.toJson(stateLog.getData())));

		return list;
	}
}