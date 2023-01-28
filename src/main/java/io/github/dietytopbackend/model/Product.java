package io.github.dietytopbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
