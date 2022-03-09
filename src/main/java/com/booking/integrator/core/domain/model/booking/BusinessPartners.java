package com.booking.integrator.core.domain.model.booking;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.booking.integrator.core.domain.model.Booking;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BusinessPartners implements Serializable {
	private static final long serialVersionUID = 916806373983350527L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPusinessPartners;

	private String role;
	private Boolean partnerIsNac;
	private String partnerNumber;
	private String partnerFiAccount;
	private String partnerFiAccountTerritory;

	@ManyToOne
	private Booking booking;

}