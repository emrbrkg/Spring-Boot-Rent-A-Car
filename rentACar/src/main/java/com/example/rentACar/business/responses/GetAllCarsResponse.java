package com.example.rentACar.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCarsResponse {
    private int id;
    private double dailyPrice;
    private int modelYear;
    private int state;
    private String brandName;
    private String modelName;

}
