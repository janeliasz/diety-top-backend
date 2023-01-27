package io.github.dietytopbackend.controller;

import io.github.dietytopbackend.model.Patient;
import io.github.dietytopbackend.model.Product;
import io.github.dietytopbackend.service.PatientService;
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

    @GetMapping()
    public Patient getById(@RequestParam("id") int id) {
        return patientService.getById(id);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> edit(@RequestBody Patient newPatient) {
        if (!patientService.validate(newPatient)) {
            return ResponseEntity.badRequest().body("Validation failed.");
        }

        return ResponseEntity.ok().body(patientService.edit(newPatient).toString());
    }

    @PostMapping("/{patientId}/exclusions/{productId}")
    public void excludeProduct(@PathVariable("patientId") int patientId, @PathVariable("productId") int productId) {
        patientService.excludeProduct(patientId, productId);
    }

    @GetMapping("/{id}/exclusions")
    public List<Product> getExclusions(@PathVariable("id") int id) {
        return patientService.getPatientExclusions(id);
    }
}
