package com.booking.integrator.core.domain.service;

import org.springframework.stereotype.Service;

import com.booking.integrator.core.domain.model.booking.BlNumbers;
import com.booking.integrator.core.domain.repository.BlNumbersRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlNumbersService {

	private BlNumbersRepository blNumbersRepository;

	public void save(BlNumbers blNumbers) {

		blNumbersRepository.save(blNumbers);
	}

}
