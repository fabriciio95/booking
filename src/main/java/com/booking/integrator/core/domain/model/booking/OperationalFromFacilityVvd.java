package com.booking.integrator.core.domain.model.booking;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OperationalFromFacilityVvd implements Serializable {
	private static final long serialVersionUID = -265955699454634539L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOperationalFromFacilityVvd;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Vessel vessel;
	
	private String voyageNumber;
	private String direction;
}
