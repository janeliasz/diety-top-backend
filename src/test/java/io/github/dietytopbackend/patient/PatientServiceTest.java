package io.github.dietytopbackend.patient;

import io.github.dietytopbackend.product.Product;
import io.github.dietytopbackend.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ProductService productService;

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
            .excludedProducts(new ArrayList<Product>(Arrays.asList(Product.builder().id(1).name("test").build())))
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

    @Test
    public void patientAndProductNotNull_excluded() {
        when(patientRepository.findById(1)).thenReturn(Optional.ofNullable(patient));
        when(productService.getById(1)).thenReturn(Product.builder()
            .id(1)
            .name("test")
            .build()
        );

        boolean excluded = patientService.excludeProduct(1, 1);

        assertTrue(excluded);
        assertEquals(patient.getExcludedProducts().size(), 2);
    }

    @Test
    public void patientNull_didNotExclude() {
        when(patientRepository.findById(2)).thenReturn(Optional.ofNullable(null));

        boolean excluded = patientService.excludeProduct(2, 1);

        assertFalse(excluded);
        assertEquals(patient.getExcludedProducts().size(), 1);
    }

    @Test
    public void productNull_didNotExclude() {
        when(patientRepository.findById(1)).thenReturn(Optional.ofNullable(patient));
        when(productService.getById(2)).thenReturn(null);

        boolean excluded = patientService.excludeProduct(1, 2);

        assertFalse(excluded);
        assertEquals(patient.getExcludedProducts().size(), 1);
    }
}