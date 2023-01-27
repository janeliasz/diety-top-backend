package io.github.dietytopbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {
    @Id private int id;
    private String name;
    private String surname;
    private String birthday;
    private String sex;
    private double weight;
    private double height;
    private double glucose;
    private double magnesium;
    private double calcium;
    private double iron;
    private String activity;

    @ManyToMany
    @JoinTable(
        name = "Exclusions",
        joinColumns = @JoinColumn(
            name = "patientId"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "productId"
        )
    )
    private List<Product> excludedProducts;
}
