package com.example.rentACar.business.abstracts;

import com.example.rentACar.business.requests.CreateModelRequest;
import com.example.rentACar.business.responses.GetAllModelsResponse;
import com.example.rentACar.business.responses.GetByIdResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    void add(CreateModelRequest createModelRequest);
    GetByIdResponse getById(int id);
    void delete(int id);
}
