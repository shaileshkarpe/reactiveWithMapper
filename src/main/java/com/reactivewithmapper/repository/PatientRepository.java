package com.reactivewithmapper.repository;

import com.reactivewithmapper.entity.PatientEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends R2dbcRepository<PatientEntity,Integer> {
}
