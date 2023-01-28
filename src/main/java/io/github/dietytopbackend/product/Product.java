package io.github.dietytopbackend.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.dietytopbackend.patient.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    private int id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "excludedProducts")
    private List<Patient> excludingPatients;
}
