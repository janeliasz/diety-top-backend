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

    @JsonIgnore
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
