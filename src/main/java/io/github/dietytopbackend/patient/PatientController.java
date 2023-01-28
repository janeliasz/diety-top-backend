package io.github.dietytopbackend.patient;

import io.github.dietytopbackend.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/{id}")
    public Patient getById(@PathVariable("id") int id) {
        return patientService.getById(id);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> edit(@RequestBody Patient newPatient) {
        if (!patientService.validate(newPatient)) {
            return ResponseEntity.badRequest().body("Validation failed.");
        }

        patientService.edit(newPatient);
        return ResponseEntity.ok().body("Successfully edited patient.");
    }

    @GetMapping("/{id}/exclusions")
    public List<Product> getExclusions(@PathVariable("id") int id) {
        return patientService.getPatientExclusions(id);
    }

    @PostMapping("/{patientId}/exclusions/{productId}")
    public void excludeProduct(@PathVariable("patientId") int patientId, @PathVariable("productId") int productId) {
        patientService.excludeProduct(patientId, productId);
    }

    @DeleteMapping("/{patientId}/exclusions/{productId}")
    public void includeProduct(@PathVariable("patientId") int patientId, @PathVariable("productId") int productId) {
        patientService.includeProduct(patientId, productId);
    }
}
