package com.reactivewithmapper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest extends PatientResponse {
    private String firstName;
    private String lastName;
}
