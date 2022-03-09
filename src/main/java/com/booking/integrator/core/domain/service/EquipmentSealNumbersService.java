package com.booking.integrator.core.domain.service;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.booking.integrator.core.domain.model.booking.EquipmentSealNumbers;
import com.booking.integrator.core.domain.repository.EquipmentSealNumbersRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EquipmentSealNumbersService implements Serializable {
	private static final long serialVersionUID = -5798594138507304010L;
	
	EquipmentSealNumbersRepository equipmentSealNumbersRepository;

	public void save(EquipmentSealNumbers equipmentSealNumbers) {
		
		equipmentSealNumbersRepository.save(equipmentSealNumbers);

	}

}
