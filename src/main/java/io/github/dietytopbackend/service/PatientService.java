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

    public boolean validate(Patient patient) {
        return (
            !patient.getName().isEmpty() &&
            !patient.getSurname().isEmpty() &&
            (patient.getSex().equals("m") || patient.getSex().equals("f")) &&
            patient.getWeight() > 0 &&
            patient.getHeight() > 0 &&
            patient.getGlucose() > 0 &&
            patient.getMagnesium() > 0 &&
            patient.getCalcium() > 0 &&
            patient.getIron() > 0 &&
            (patient.getActivity().equals("low") || patient.getActivity().equals("medium") || patient.getActivity().equals("high"))
        );
    }
}
