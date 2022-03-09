package com.booking.integrator.core.domain.model.booking;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.booking.integrator.core.domain.model.Booking;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookingTransportPlan implements Serializable {
	private static final long serialVersionUID = -7631628284640881628L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBookingTransportPlan;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingTransportPlan")
	private List<Legs> legs;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Amendment amendment;

	@ManyToOne
	private Booking booking;
}