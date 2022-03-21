package com.intercab.service.integrator.core.service.location;

import org.springframework.stereotype.Component;

import com.intercab.service.integrator.core.domain.location.model.LocationEventHub;
import com.intercab.service.integrator.core.domain.log.modal.LocationLog;
import com.intercab.service.integrator.core.service.common.ICommonService;

@Component
public interface ILocationService extends ICommonService<LocationEventHub, LocationLog> {
}