package io.github.dietytopbackend.service;

import io.github.dietytopbackend.model.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    Patient patient = new Patient(
            1,
            "Jan",
            "Kowalski",
            "2001-04-22",
            "m",
            75,
            185,
            96.27,
            2.4,
            8.9,
            188.88,
            "medium"
    );

    public Patient getById(int id) {
        return patient;
    }

    public Patient edit(Patient newPatient) {
        return patient = newPatient;
    }
}
