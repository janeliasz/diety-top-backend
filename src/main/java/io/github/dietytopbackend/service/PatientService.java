package io.github.dietytopbackend.service;

import io.github.dietytopbackend.model.Patient;
import io.github.dietytopbackend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
        this.patientRepository.save(new Patient(
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
        ));
    }

    public Patient getById(int id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.isEmpty() ? null : patient.get();
    }

    public Patient edit(Patient newPatient) {
        patientRepository.save(newPatient);
        return getById(newPatient.getId());
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
