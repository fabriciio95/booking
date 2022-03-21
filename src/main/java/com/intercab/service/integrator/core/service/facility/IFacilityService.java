package com.intercab.service.integrator.core.service.facility;

import org.springframework.stereotype.Component;

import com.intercab.service.integrator.core.domain.facility.modal.FacilityEventHub;
import com.intercab.service.integrator.core.domain.log.modal.FacilityNewLog;
import com.intercab.service.integrator.core.service.common.ICommonService;

@Component
public interface IFacilityService extends ICommonService<FacilityEventHub, FacilityNewLog> {
}