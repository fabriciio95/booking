package com.booking.integrator.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.integrator.core.domain.model.booking.TariffCommodities;

@Repository
public interface TariffCommoditiesRepository extends JpaRepository<TariffCommodities, Long> {

}
