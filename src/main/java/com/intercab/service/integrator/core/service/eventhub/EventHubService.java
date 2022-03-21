package com.intercab.service.integrator.core.service.eventhub;

import com.intercab.keyvault.KeyVaultUtils;
import com.intercab.service.integrator.core.domain.MessageEventHub;
import com.intercab.service.integrator.core.service.businesspartner.IBusinessPartnerService;
import com.intercab.service.integrator.core.service.city.ICityService;
import com.intercab.service.integrator.core.service.country.ICountryService;
import com.intercab.service.integrator.core.service.facility.IFacilityService;
import com.intercab.service.integrator.core.service.location.ILocationService;
import com.intercab.service.integrator.core.service.state.IStateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class EventHubService implements IEventHubService {
	@Autowired
	private Environment env;

	@Autowired
	IBusinessPartnerService businessPartnerService;
	@Autowired
	ICountryService countryService;
	@Autowired
	IStateService stateService;
	@Autowired
	ICityService cityService;
	@Autowired
	IFacilityService facilityService;
	@Autowired
	ILocationService locactionService;

	@Override
	public void processMessage(MessageEventHub messageEventHub) {
		String globeBusinessPartner = KeyVaultUtils.getSecretValue(this.env, "eventhub.globe.businespartnes.name");
		String globeCountry = KeyVaultUtils.getSecretValue(this.env, "eventhub.globe.country.name");
		String globeStaty = KeyVaultUtils.getSecretValue(this.env, "eventhub.globe.state.name");
		String globeCity = KeyVaultUtils.getSecretValue(this.env, "eventhub.globe.city.name");
		String globeFacility = KeyVaultUtils.getSecretValue(this.env, "eventhub.globe.facilitynew.name");
		String globeLocation = KeyVaultUtils.getSecretValue(this.env, "eventhub.globe.location.name");

		if (globeBusinessPartner.equals(messageEventHub.getEventName())) {
			this.businessPartnerService.processData(messageEventHub.getMessage());
		} else if (globeFacility.equals(messageEventHub.getEventName())) {
			this.facilityService.processData(messageEventHub.getMessage());
		} else if (globeCountry.equals(messageEventHub.getEventName())) {
			this.countryService.processData(messageEventHub.getMessage());
		} else if (globeStaty.equals(messageEventHub.getEventName())) {
			this.stateService.processData(messageEventHub.getMessage());
		} else if (globeCity.equals(messageEventHub.getEventName())) {
			this.cityService.processData(messageEventHub.getMessage());
		} else if (globeLocation.equals(messageEventHub.getEventName())) {
			this.locactionService.processData(messageEventHub.getMessage());
		}
	}
}