package com.reactivewithmapper.mapper;

import com.reactivewithmapper.entity.PatientEntity;
import com.reactivewithmapper.model.PatientRequest;
import com.reactivewithmapper.model.PatientResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface PatientMapper {
 PatientEntity modeltoentity (PatientRequest patientRequest);
 PatientRequest entitytomodel(PatientEntity patientEntity);
 PatientResponse entitytomodel2 (PatientEntity patientEntity);
}
