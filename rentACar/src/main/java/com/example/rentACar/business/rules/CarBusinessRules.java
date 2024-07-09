package com.example.rentACar.business.rules;

import com.example.rentACar.common.utilities.exceptions.BusinessException;
import com.example.rentACar.Data.dataAccess.abstracts.CarRepository;
import com.example.rentACar.Data.dataAccess.entities.concretes.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class CarBusinessRules {
    private CarRepository carRepository;

    @Autowired
    public CarBusinessRules (CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void checkIfCarIsAvailable (int id) {
        Car car = carRepository.getById(id);
        if (car.getState() != 1)
            throw new BusinessException("Car is not available to be rented!");
    }

    public void checkIfStateIsValid(int state) {
        if (state != 1 && state != 2 && state != 3)
            throw new BusinessException("Invalid state! Must be in the range of 1-3");
    }

    public void checkIfDailyPriceInRange(double dailyPrice) {
        if (dailyPrice < 700 || dailyPrice > 10000)
            throw new BusinessException("Daily price must be among 700-10000!");
    }

    public void checkIfModelYearInRange(int year) {
        if (Year.of(year).isBefore(Year.of(2000)))
            throw new BusinessException("Model year must be after 2000!");
    }



}
