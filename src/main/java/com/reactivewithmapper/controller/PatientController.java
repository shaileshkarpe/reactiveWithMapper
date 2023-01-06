package com.reactivewithmapper.controller;

import com.reactivewithmapper.model.PatientRequest;
import com.reactivewithmapper.model.PatientResponse;
import com.reactivewithmapper.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class PatientController {
    @Autowired
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(path = "/patients", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PatientResponse> addPatient(@RequestBody Mono<PatientRequest> patientRequestMono) {
        return this.patientService.addPatient(patientRequestMono);
    }

    @GetMapping(path = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<PatientRequest> getAllPatient() {
        return this.patientService.getAllPatient();
    }

    @GetMapping(path = "patients/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PatientRequest> getById(@PathVariable int patientId) {
        return this.patientService.getById(patientId);
    }

    @PutMapping(path = "patients/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PatientRequest> updatePatient(@PathVariable int patientId, @RequestBody Mono<PatientRequest> patientRequestMono) {
        return this.patientService.updatePatient(patientRequestMono, patientId);
    }

    @DeleteMapping(path = "patients/{patientId}")
    public ResponseEntity<Mono<String>> deletePatient(@PathVariable int patientId) {
        patientService.deletePatient(patientId);
        return new ResponseEntity<>(Mono.just("deleted successfully "), HttpStatus.OK);
    }

}
