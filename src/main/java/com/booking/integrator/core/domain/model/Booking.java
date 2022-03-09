package com.booking.integrator.core.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.booking.integrator.core.domain.model.booking.BookingCharges;
import com.booking.integrator.core.domain.model.booking.BookingCommitments;
import com.booking.integrator.core.domain.model.booking.BookingProducts;
import com.booking.integrator.core.domain.model.booking.BookingRad;
import com.booking.integrator.core.domain.model.booking.BookingReference;
import com.booking.integrator.core.domain.model.booking.BookingTransportPlan;
import com.booking.integrator.core.domain.model.booking.BusinessPartners;
import com.booking.integrator.core.domain.model.booking.DocLocation;
import com.booking.integrator.core.domain.model.booking.Pld;
import com.booking.integrator.core.domain.model.booking.Pod;
import com.booking.integrator.core.domain.model.booking.Pol;
import com.booking.integrator.core.domain.model.booking.Por;
import com.booking.integrator.core.domain.model.booking.Remarks;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Booking implements Serializable {
	private static final long serialVersionUID = -7704609464780314510L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBooking;

	private String bookingNumber;
	private String bookingCreationDate;
	private String bookingUpdateDate;
	private String bookingConfirmationDate;
	private String status;
	private String brand;
	private Boolean isSplitAllowed;
	private Boolean isVesselRenominated;
	private Boolean isCodPerformed;
	private String stationCode;
	private String bookingLbu;
	private String responsibleLbu;
	private String contractNumber;
	private Boolean isBookingSplit;
	private Boolean isInstantBooking;

	@OneToOne(cascade = CascadeType.ALL)
	private DocLocation docLocation;

	private String serviceTerm;

	private transient List<String> blNumbers;

	private String originServiceType;
	private String destinationServiceType;
	private String trade;

	@OneToOne(cascade = CascadeType.ALL)
	private Por por;

	@OneToOne(cascade = CascadeType.ALL)
	private Pol pol;

	@OneToOne(cascade = CascadeType.ALL)
	private Pod pod;

	@OneToOne(cascade = CascadeType.ALL)
	private Pld pld;

	private String docCutOff;
	private Boolean isPorInbound;
	private Boolean isPldInbound;
	private Boolean isPorCat;
	private Boolean isPendingForAllocation;
	private String contributionMarginTotal;
	private String contributionMarginCurrency;
	private String bookingType;
	private Boolean isSlotBooking;
	private Boolean hasDgRequestAttachment;
	private Boolean imApprovedForDg;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
	private List<BookingCharges> bookingCharges;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
	private List<BusinessPartners> businessPartners;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
	private List<BookingProducts> bookingProducts;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
	private List<Remarks> remarks;

	@OneToOne(cascade = CascadeType.ALL)
	private BookingTransportPlan bookingTransportPlan;

	@OneToOne(cascade = CascadeType.ALL)
	private BookingRad bookingRad;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
	private List<BookingReference> bookingReference;

	private Boolean isCommitment;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
	private List<BookingCommitments> bookingCommitments;
}
