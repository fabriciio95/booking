package com.booking.integrator.core.domain.model.booking;

import java.io.Serializable;
import java.util.Date;

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
public class Remarks implements Serializable {
	private static final long serialVersionUID = -2966568254553087002L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRemarks;
	
	private String remark;
	private String creationDate;
	private String reasonCode;
	private String reasonCodeCategory;
	private Boolean ebkgAdditionalShipmentDetails;

	@ManyToOne
	private Booking booking;
}
