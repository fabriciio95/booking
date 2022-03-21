package com.intercab.service.integrator.core.infrastructure.repository.log;

import com.intercab.service.integrator.core.domain.log.modal.IntercabLog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<IntercabLog, String> {
}