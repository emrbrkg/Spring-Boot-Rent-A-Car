package com.example.rentACar.business.rules;

import com.example.rentACar.common.utilities.exceptions.BusinessException;
import com.example.rentACar.Data.dataAccess.abstracts.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// İŞ KURALLARINI BURAYA YAZIYORUZ!!!!!!!
@Service
public class BrandBusinessRules {
    private BrandRepository brandRepository;

    @Autowired
    public BrandBusinessRules (BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    // örneğin marka tekrar etmeyecek.
    public void checkIfBrandNameExists(String brandName) {
        if (this.brandRepository.existsByName(brandName)) {
            throw new BusinessException("Brand already exists");  //Java exception  tiplerini araştır.
        }

    }

}
