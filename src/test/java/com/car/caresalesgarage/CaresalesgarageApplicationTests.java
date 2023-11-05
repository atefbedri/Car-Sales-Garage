package com.car.caresalesgarage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.car.caresalesgarage.dao.CarRepository;
import com.car.caresalesgarage.models.Car;
import com.car.caresalesgarage.models.Enums.FuelType;
import com.car.caresalesgarage.services.implementations.CarService;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CaresalesgarageApplicationTests {


    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;


	/**
	 * Test for add car
	 * @throws Exception
	 */
    @Test
    public void testAddCar() throws Exception {
        Car car = new Car();
        car.setRegistrationDate(LocalDate.of(2016, 1, 1)); // After 2015

        when(carRepository.save(car)).thenReturn(car);
        ResponseEntity<Car> response = carService.addCar(car);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

	/**
	 * Test for returning list cars by fuel type and max price
	 * @throws Exception
	 */
    @Test
    public void testListCarsByFuelTypeAndMaxPrice() throws Exception {
        FuelType fuelType = FuelType.DIESEL;
        double maxPrice = 10000.0;
        List<Car> cars = new ArrayList<>();
        // Configure the behavior of carRepository to return the expected list of cars
        when(carRepository.findByFuelTypeAndPriceLessThanEqual(fuelType, maxPrice)).thenReturn(cars);

        ResponseEntity<List<Car>> response = carService.listCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

	/**
	 * Test for returning list makes
	 * @throws Exception
	 */
    @Test
    public void testListAllMakes() throws Exception {
        List<String> makes = new ArrayList<>();

        // Configure the behavior of carRepository to return the expected list of makes
        when(carRepository.findAllMakes()).thenReturn(makes);

        ResponseEntity<List<String>> response = carService.listAllMakes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

	/**
	 * Test for updating cars picture
	 * @throws Exception
	 */
	@Test
    public void testUpdateCar() throws Exception {
        // Define an existing car with an ID and the old picture
        Long carId = 1L;
        String oldPicture = "old_picture.jpg";
        Car existingCar = new Car();
        existingCar.setId(carId);
        existingCar.setPicture(oldPicture);

        // Define the new picture for the update
        String newPicture = "new_picture.jpg";

        // Configure the behavior of the repository to return the existing car
        when(carRepository.findById(carId)).thenReturn(Optional.of(existingCar));

        // Call the updateCar method to simulate an update
        ResponseEntity<Car> response = carService.updateCar(carId, newPicture);

        // Verify that the update occurred
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newPicture, existingCar.getPicture()); // Check that the car's picture has been updated
    }
}
