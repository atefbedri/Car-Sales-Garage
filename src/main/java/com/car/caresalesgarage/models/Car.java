package com.car.caresalesgarage.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.car.caresalesgarage.models.Enums.FuelType;
import com.car.caresalesgarage.models.Enums.TransmissionType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private String model;
    private LocalDate registrationDate;
    private double price;
    private FuelType fuelType;
    private int mileage;
    private TransmissionType transmissionType;
    private String picture;
}