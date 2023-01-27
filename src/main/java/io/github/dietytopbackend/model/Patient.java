package io.github.dietytopbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
