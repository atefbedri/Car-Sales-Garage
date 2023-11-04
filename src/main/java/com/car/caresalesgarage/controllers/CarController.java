package com.car.caresalesgarage.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.caresalesgarage.models.Car;
import com.car.caresalesgarage.models.Enums.FuelType;
import com.car.caresalesgarage.services.ICarService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/car")
public class CarController {

    @Autowired
    private ICarService carService;

    @PostMapping("/add")
    @ApiOperation("Add a new car")
    public ResponseEntity<Car> addCar(@RequestBody Car car) throws Exception{
        return carService.addCar(car);
    }

    @GetMapping("/allByFuelTypeAndMaxPrice")
    @ApiOperation("Get all cars by fuel type and max price")
    public ResponseEntity<List<Car>> getCarsByFuelAndPrice(@RequestParam FuelType fuelType, @RequestParam double maxPrice)  throws Exception{
        return carService.listCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);
    }

    @GetMapping("/makes")
    @ApiOperation("Get all makes")
    public ResponseEntity<List<String>> getAllMakes()  throws Exception{
       return carService.listAllMakes();
    }

    @PutMapping("/{id}/picture")
    @ApiOperation("Update a car picture")
    public ResponseEntity<Car> updateCarPicture(@PathVariable Long id, @RequestParam String picture)  throws Exception{
        return carService.updateCar(id, picture);
    }
}