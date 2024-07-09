package com.example.rentACar.Data.dataAccess.abstracts;

import com.example.rentACar.Data.dataAccess.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

}
