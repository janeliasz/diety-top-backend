package io.github.dietytopbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private int id;
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
