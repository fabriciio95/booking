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

import com.booking.integrator.core.domain.model.Booking;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookingCharges implements Serializable{
	private static final long serialVersionUID = -1865987148960557544L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBookingCharges;
	
	private Integer chargeItemNumber;
	private String bookingProductItemNumber;
	private String formationType;
	private String chargeRate;
	private String chargeCurrency;
	private String chargeAmount;
	private String chargeBasis;
	private String chargeUnit;
	private String tariffRate;
	private String tariffAmount;
	private String tariffCurrency;
	private String occuringAt;
	private String rcc;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingCharges")
	private List<BaseRccs> baseRCCs;

	private String prepaidCollect;
	private Boolean isBundled;
	private String chargeType;
	private Boolean isObsolete;

	@ManyToOne
	private Booking booking;

}
