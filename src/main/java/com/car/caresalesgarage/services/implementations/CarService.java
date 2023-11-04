package com.car.caresalesgarage.services.implementations;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.car.caresalesgarage.dao.CarRepository;
import com.car.caresalesgarage.models.Car;
import com.car.caresalesgarage.models.Enums.FuelType;
import com.car.caresalesgarage.services.ICarService;

@Service
public class CarService implements ICarService{

    @Autowired
    CarRepository carRepository;

    @Override
    public ResponseEntity<Car>  addCar(Car car) throws Exception{
        try {
            if (car.getRegistrationDate().isAfter(LocalDate.of(2015, 1, 1))) {
                return new ResponseEntity<>(carRepository.save(car), HttpStatus.OK);
            }else{
                throw new Exception("Only car registered after 2015 are allowed to be add to the catalog");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<Car>> listCarsByFuelTypeAndMaxPrice(FuelType fuelType, double maxPrice)  throws Exception {
        try {
            return new ResponseEntity<>(carRepository.findByFuelTypeAndPriceLessThanEqual(fuelType, maxPrice), HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<String>> listAllMakes() throws Exception {
        try {
            return new ResponseEntity<>(carRepository.findAllMakes(), HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Car> updateCar(Long id, String picture) throws Exception {
       try {
            Car updateCar = carRepository.findById(id).get();
            if(updateCar != null && picture != null){
                updateCar.setPicture(picture);
                carRepository.save(updateCar);
            }
            return new ResponseEntity<>(updateCar, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
}
