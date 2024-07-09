package com.example.rentACar.business.rules;

import com.example.rentACar.common.utilities.exceptions.BusinessException;
import com.example.rentACar.Data.dataAccess.abstracts.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelBusinessRules {
    private ModelRepository modelRepository;

    @Autowired
    public ModelBusinessRules (ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public void checkIfModelExists(String modelName) {
        if (this.modelRepository.existsByName(modelName)) {
            throw new BusinessException("Model already exists.");
        }
    }

}
