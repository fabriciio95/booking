package com.booking.integrator.core.domain.model.booking;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Legs implements Serializable {
	private static final long serialVersionUID = -6651848750709598089L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLegs;

	private String sequenceNumber;
	private String operationType;
	private String mode;
	private Boolean invalidTpLegFlag;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "legs")
	private List<InvalidityReasonCodes> invalidityReasonCodes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "legs")
	private List<LegApprovals> legApprovals;

	private String vvdFacilityIdentFigureFrom;
	private String vvdFacilityIdentFigureTo;
	private String etd;
	private String eta;
	private String cutOffDateTime;
	private String availabilityDateTime;
	private String totalTransitTime;
	private String transitTime;
	private String idleTime;
	private Boolean blLeg;

	@OneToOne(cascade = CascadeType.ALL)
	private LegFrom legFrom;

	@OneToOne(cascade = CascadeType.ALL)
	private LegTo legTo;

	@OneToOne(cascade = CascadeType.ALL)
	private CommercialVvd commercialVVD;

	@OneToOne(cascade = CascadeType.ALL)
	private OperationalFromFacilityVvd operationalFromFacilityVVD;

	@OneToOne(cascade = CascadeType.ALL)
	private OperationalToFacilityVvd operationalToFacilityVVD;

	@ManyToOne
	private BookingTransportPlan bookingTransportPlan;
}