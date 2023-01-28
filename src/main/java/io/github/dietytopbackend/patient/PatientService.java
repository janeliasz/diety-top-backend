package io.github.dietytopbackend.patient;

import io.github.dietytopbackend.product.Product;
import io.github.dietytopbackend.product.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class PatientService {

    PatientRepository patientRepository;

    ProductService productService;

    public PatientService(PatientRepository patientRepository, ProductService productService) {
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

        this.productService = productService;
    }

    public Patient getById(int id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.isEmpty() ? null : patient.get();
    }

    public void edit(Patient newPatient) {
        Patient patient = getById(newPatient.getId());
        newPatient.setExcludedProducts(patient.getExcludedProducts());

        patientRepository.save(newPatient);
    }

    public List<Product> getPatientExclusions(int id) {
        return getById(id).getExcludedProducts();
    }

    public boolean excludeProduct(int patientId, int productId) {
        Patient patient = getById(patientId);
        Product product = productService.getById(productId);

        if (patient == null || product == null) return false;

        patient.getExcludedProducts().add(product);
        patientRepository.save(patient);
        return true;
    }

    public boolean includeProduct(int patientId, int productId) {
        Patient patient = getById(patientId);
        Product product = productService.getById(productId);

        if (patient == null || product == null) return false;

        patient.getExcludedProducts().remove(product);
        patientRepository.save(patient);
        return true;
    }

    public boolean validate(Patient patient) {
        Pattern regexPattern = Pattern.compile("^[a-z ,.'-]+$", Pattern.CASE_INSENSITIVE);
        LocalDate birthday = LocalDate.parse(patient.getBirthday());

        return (
            !patient.getName().isEmpty() && regexPattern.matcher(patient.getName()).matches() &&
            !patient.getSurname().isEmpty() && regexPattern.matcher(patient.getSurname()).matches() &&
            !birthday.isBefore(LocalDate.parse("1900-01-01")) && !birthday.isAfter(LocalDate.now()) &&
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
