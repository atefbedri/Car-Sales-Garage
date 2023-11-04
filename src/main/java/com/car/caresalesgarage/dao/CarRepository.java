package com.car.caresalesgarage.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.car.caresalesgarage.models.Car;
import com.car.caresalesgarage.models.Enums.FuelType;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
    List<Car> findByFuelTypeAndPriceLessThanEqual(FuelType fuelType, double maxPrice);
    List<Car> findByRegistrationDateAfter(LocalDate date);

    @Query("SELECT DISTINCT car.make FROM Car car")
    List<String> findAllMakes();
}