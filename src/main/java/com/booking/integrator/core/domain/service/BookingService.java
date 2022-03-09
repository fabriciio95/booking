package com.booking.integrator.core.domain.service;

import org.springframework.stereotype.Service;

import com.booking.integrator.core.domain.model.Booking;
import com.booking.integrator.core.domain.repository.BookingRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingService {

	private BookingRepository bookingRepository;

	public void save(Booking booking) {

		bookingRepository.save(booking);

	}

}
