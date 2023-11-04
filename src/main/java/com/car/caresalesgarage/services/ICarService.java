package com.car.caresalesgarage.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.car.caresalesgarage.models.Car;
import com.car.caresalesgarage.models.Enums.FuelType;

public interface ICarService {
    ResponseEntity<Car> addCar(Car car) throws Exception;
    ResponseEntity<List<Car>> listCarsByFuelTypeAndMaxPrice(FuelType fuelType, double maxPrice) throws Exception;    
    ResponseEntity<List<String>> listAllMakes() throws Exception;
    ResponseEntity<Car>  updateCar(Long id, String picture) throws Exception;

}
