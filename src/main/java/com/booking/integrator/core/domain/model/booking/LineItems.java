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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LineItems implements Serializable {
	private static final long serialVersionUID = -8962550104818627225L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLineItems;

	private String bookingLineItemNumber;
	private String bookingLineItemStatus;
	private String equipmentSize;
	private String equipmentType;
	private String equipmentNumber;
	private String isSwapHappened;
	private String isEquSizeTypeAutoSubstituted;
	private String isEquProfileAutoAssigned;
	private String grossWeight;
	private String vgm;
	private String gateInWeight;
	private String vgmProvidedByTerminal;

	private transient List<String> equipmentSealNumbers;
	
	private String tareWeight;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lineItems")
	private List<CargoPkpOrDel> cargoPkpOrDel;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lineItems")
	private List<MtyPkpOrDel> mtyPkpOrDel;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lineItems")
	private List<DgCommodities> dgCommodities;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lineItems")
	private List<BlAndSiInformation> blAndSiInformation;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lineItems")
	private List<MrnAndFdaDetails> mrnAndFdaDetails;
	
	private String isoSizeType;

	@ManyToOne
	private BookingProducts bookingProducts;
}
