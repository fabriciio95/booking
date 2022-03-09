package com.booking.integrator.core.domain.service;

import org.springframework.stereotype.Service;

import com.booking.integrator.core.domain.model.booking.TariffCommodities;
import com.booking.integrator.core.domain.repository.TariffCommoditiesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TariffCommoditiesService {

	private TariffCommoditiesRepository commoditiesRepository;

	public void save(TariffCommodities commodities) {

		commoditiesRepository.save(commodities);
	}
}
