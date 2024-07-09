package com.example.rentACar.business.concretes;

import com.example.rentACar.business.abstracts.CarService;
import com.example.rentACar.business.requests.CreateCarRequest;
import com.example.rentACar.business.responses.GetAllCarsResponse;
import com.example.rentACar.business.responses.GetByIdResponse;
import com.example.rentACar.business.rules.CarBusinessRules;
import com.example.rentACar.common.utilities.mappers.ModelMapperService;
import com.example.rentACar.Data.dataAccess.abstracts.CarRepository;
import com.example.rentACar.Data.dataAccess.entities.concretes.Car;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class CarManager implements CarService {
    CarRepository carRepository;
    ModelMapperService modelMapperService;
    CarBusinessRules carBusinessRules;


    @Autowired
    public CarManager(CarRepository carRepository, ModelMapperService modelMapperService, CarBusinessRules carBusinessRules) {
        this.carRepository = carRepository;
        this.modelMapperService = modelMapperService;
        this.carBusinessRules = carBusinessRules;
    }

    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = carRepository.findAll();

        List<GetAllCarsResponse> carsResponse = cars.stream()
                .map(car -> this.modelMapperService
                        .forResponse()
                        .map(car, GetAllCarsResponse.class)).collect(Collectors.toList());
        return carsResponse;
    }

    @Override
    public void add(CreateCarRequest createCarRequest) {
        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);    // Buradaki olay şu: eldeki CreateBrandRequest tipindeki nesneyi Car tipine çevirerek map ediyoruz.
        this.carRepository.save(car);

    }

    @Override
    public GetByIdResponse getById(int id) {
        Car car = this.carRepository.findById(id).orElseThrow();
        GetByIdResponse response = this.modelMapperService.forResponse().map(car, GetByIdResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        this.carRepository.deleteById(id);
    }

    @Override
    public void letBeRented(int id) {
        this.carBusinessRules.checkIfCarIsAvailable(id);     //iş kuralına göre state = 1 olmayan araba kiralanamaz. hata fırlatıp metodu sonlandırır.

        Car car = carRepository.getById(id);
        car.setState(2);
        carRepository.save(car);
    }

    @Override
    public void updateState(int id, int newState) {
        Car car = this.carRepository.getById(id);
        this.carBusinessRules.checkIfStateIsValid(newState);

        car.setState(newState);
        carRepository.save(car);
    }

    @Override
    public void updateDailyPrice(int id, double dailyPrice) {
        Car car = this.carRepository.getById(id);
        this.carBusinessRules.checkIfDailyPriceInRange(dailyPrice);

        car.setDailyPrice(dailyPrice);
        this.carRepository.save(car);

    }

    @Override
    public void updateModelYear(int id, int year) {
        this.carBusinessRules.checkIfModelYearInRange(year);

        Car car = this.carRepository.getById(id);
        car.setModelYear(year);
        this.carRepository.save(car);

    }


}
