package io.github.dietytopbackend.service;

import io.github.dietytopbackend.model.Patient;
import io.github.dietytopbackend.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = Patient.builder()
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
            .build();
    }

    @Test
    public void validPatient_validationSuccess() {
        boolean validationResult = patientService.validate(patient);

        assertTrue(validationResult);
    }

    @Test
    public void emptyName_validationError() {
        patient.setName("");

        boolean validationResult = patientService.validate(patient);

        assertFalse(validationResult);
    }

    @Test
    public void specialCharactersName_validationError() {
        patient.setName(patient.getName() + "!@#");

        boolean validationResult = patientService.validate(patient);

        assertFalse(validationResult);
    }

    @Test
    public void birthdayTooEarly_validationError() {
        patient.setBirthday("1899-12-12");

        boolean validationResult = patientService.validate(patient);

        assertFalse(validationResult);
    }

    @Test
    public void birthdayTooLate_validationError() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        patient.setBirthday(tomorrow.toString());

        boolean validationResult = patientService.validate(patient);

        assertFalse(validationResult);
    }

    @Test
    public void invalidSex_validationError() {
        patient.setSex("a");

        boolean validationResult = patientService.validate(patient);

        assertFalse(validationResult);
    }

    @Test
    public void negativeOrZeroWeight_validationError() {
        patient.setWeight(0);

        boolean validationResult = patientService.validate(patient);

        assertFalse(validationResult);
    }

    @Test
    public void invalidActivity_validationError() {
        patient.setActivity("none");

        boolean validationResult = patientService.validate(patient);

        assertFalse(validationResult);
    }
}