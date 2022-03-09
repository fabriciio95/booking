package com.booking.integrator.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.integrator.core.domain.model.booking.BlNumbers;

@Repository
public interface BlNumbersRepository extends JpaRepository<BlNumbers, Long> {

}
