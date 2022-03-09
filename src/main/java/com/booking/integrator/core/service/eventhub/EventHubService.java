package com.booking.integrator.core.service.eventhub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.booking.integrator.core.domain.model.MessageEventHub;
import com.booking.integrator.core.service.businesspartner.IBusinessPartnerService;

@Service
public class EventHubService implements IEventHubService {
//
//	@Value("${eventhub.globe.businespartnes.name}")
//	private String businessPartnerNeme;
	
	@Autowired
	IBusinessPartnerService businessPartnerService;

	@Override
	public void processMessage(MessageEventHub messageEventHub) {
		String globeBusinessPartner = "eventhub.globe.businespartnes.name";

		if (globeBusinessPartner.equals(messageEventHub.getEventName())) {
			this.businessPartnerService.processData(messageEventHub.getMessage());
		}
	}
}