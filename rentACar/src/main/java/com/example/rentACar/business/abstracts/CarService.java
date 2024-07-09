package com.example.rentACar.business.abstracts;

import com.example.rentACar.business.requests.CreateCarRequest;
import com.example.rentACar.business.responses.GetAllCarsResponse;
import com.example.rentACar.business.responses.GetByIdResponse;
import java.util.List;

public interface CarService {
    List<GetAllCarsResponse> getAll();
    void add(CreateCarRequest createCarRequest);
    GetByIdResponse getById(int id);
    void delete(int id);
    void letBeRented(int id);
    void updateState(int id, int newState);
    void updateDailyPrice(int id, double dailyPrice);
    void updateModelYear(int id, int year);

}
