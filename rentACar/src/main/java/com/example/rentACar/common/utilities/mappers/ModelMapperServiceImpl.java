package com.example.rentACar.common.utilities.mappers;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  // Bu yapı her seferinde yeni bir nesne üretmeden var olanı kullanmamızı sağlar. (Ioc)
@NoArgsConstructor
public class ModelMapperServiceImpl implements ModelMapperService{
    private ModelMapper modelMapper;  //@Service annotasyonu sayesinde bunu kullanacağız her çağırıldığında.

    @Autowired
    public ModelMapperServiceImpl (ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Belli bi filtreleme uygulanarak response oluşturulur.
    @Override
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);  // LOOSE gevşek mapleme yapar örneğin id, name, model, age içinden sadece model ve age maplenecekse kullanırız.

        return this.modelMapper;
    }


    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);  // STANDART ise her şeyi eksiksiz maple demektir.

        return this.modelMapper;

    }


}
