package com.reactivewithmapper.service;

import com.reactivewithmapper.mapper.PatientMapper;
import com.reactivewithmapper.model.PatientRequest;
import com.reactivewithmapper.model.PatientResponse;
import com.reactivewithmapper.repository.PatientRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class PatientService {
    @Autowired
    private final PatientRepository patientRepository;
    @Autowired
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public Mono<PatientResponse> addPatient(Mono<PatientRequest> patientRequestMono) {
        log.info(" patient Added!!!!");
        return patientRequestMono.map(patientMapper::modeltoentity)
                .flatMap(this.patientRepository::save)
                .map(patientMapper::entitytomodel2);
    }

    public Flux<PatientRequest> getAllPatient() {
        log.info("get All Successfully");

        return this.patientRepository.findAll()
                .map(patientMapper::entitytomodel);
    }

    public Mono<PatientRequest> getById(int patientId) {
        log.info("id get successfully");
        return this.patientRepository.findById(patientId)
                .map(patientMapper::entitytomodel);

    }

    public Mono<PatientRequest> updatePatient(Mono<PatientRequest> patientRequestMono, int patientId) {
        log.info("updated Successfully");
        return this.patientRepository.findById(patientId)
                .flatMap(u -> patientRequestMono.map(patientMapper::modeltoentity)
                        .doOnNext(e -> e.setPatientId(patientId))).flatMap(patientRepository::save)
                .map(patientMapper::entitytomodel);
    }

    public Mono<Void> deletePatient(int patientId) {
        log.info("Deleted Successfully");
        return this.patientRepository.deleteById(patientId);
    }
}
