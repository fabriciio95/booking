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
public class BlAndSiInformation implements Serializable {
	private static final long serialVersionUID = -245874787868592465L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBlAndSiInformation;
	
	private String blNumber;
	private String siReceivedDateTime;
	private String siMode;
	private String esiRefNumber;
	
	
	@ManyToOne
	private LineItems lineItems;

}
