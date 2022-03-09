package com.booking.integrator.core.domain.model.booking;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class BookingProducts implements Serializable{
	private static final long serialVersionUID = 5963699656398643904L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBookingProducts;

	private String bookingProductNumber;
	private String bookingProductItemNumber;
	private Boolean isActive;
	private String socCoc;
	private String cargoType;
	private String shipmentType;

	private transient List<String> tariffCommodities;

	private String contractNumber;
	private String tradeCommodityGroup;
	private String ebkgCommodityDescription;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingProducts")
	private List<BookingVas> bookingVas;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingProducts")
	private List<SpecialHandling> specialHandling;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingProducts")
	private List<LineItems> lineItems;

	@ManyToOne
	private Booking booking;
}
