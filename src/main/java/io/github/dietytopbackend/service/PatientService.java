package io.github.dietytopbackend.service;

import io.github.dietytopbackend.model.Patient;
import io.github.dietytopbackend.model.Product;
import io.github.dietytopbackend.repository.PatientRepository;
import io.github.dietytopbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    PatientRepository patientRepository;

    @Autowired
    ProductService productService;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
        this.patientRepository.save(Patient.builder()
            .id(1)
            .name("Jan")
            .surname("Kowalski")
            .birthday("2001-04-22")
            .sex("m")
            .weight(75)
            .height(185)
            .glucose(96.27)
            .magnesium(2.4)
            .calcium(8.9)
            .iron(188.88)
            .activity("medium")
            .build()
        );
    }

    public Patient getById(int id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.isEmpty() ? null : patient.get();
    }

    public Patient edit(Patient newPatient) {
        patientRepository.save(newPatient);
        return getById(newPatient.getId());
    }

    public void excludeProduct(int patientId, int productId) {
        Patient patient = getById(patientId);
        Product product = productService.getById(productId);

        if (patient == null || product == null) return;

        patient.getExcludedProducts().add(product);
        patientRepository.save(patient);
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

    public List<Product> getPatientExclusions(int id) {
        return getById(id).getExcludedProducts();
    }
}
