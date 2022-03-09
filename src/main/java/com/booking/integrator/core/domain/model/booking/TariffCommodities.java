package com.booking.integrator.core.domain.model.booking;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TariffCommodities implements Serializable {
	private static final long serialVersionUID = -4273303272002696099L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTariffCommodities;

	private String tariffCommodities;

	@ManyToOne
	private BookingProducts bookingProducts;

}
