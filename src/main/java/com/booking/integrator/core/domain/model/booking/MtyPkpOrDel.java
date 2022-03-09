package com.booking.integrator.core.domain.model.booking;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MtyPkpOrDel implements Serializable {
	private static final long serialVersionUID = -1815250383404830899L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMtyPkpOrDel;

	private String pkpOrDel;
	private String dateTime;
	private Boolean isMTYReleaseNotRequired;

	@OneToOne(cascade = CascadeType.ALL)
	private Facility facility;

	@ManyToOne
	private LineItems lineItems;
}