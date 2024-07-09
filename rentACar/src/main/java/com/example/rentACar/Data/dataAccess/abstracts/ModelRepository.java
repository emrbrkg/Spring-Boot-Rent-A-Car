package com.example.rentACar.Data.dataAccess.abstracts;

import com.example.rentACar.Data.dataAccess.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    boolean existsByName(String brandName);

}
