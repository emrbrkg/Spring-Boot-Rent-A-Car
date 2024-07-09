package com.example.rentACar.Data.dataAccess.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "cars")
// Lombok ile constructor getter setter oluşturma
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // autoincrement olmasını sağlar.
    @Column(name="id")
    private int id;

    @Column(name = "plate", unique = true)
    private String plate;

    @Column(name = "dailyPrice")
    private double dailyPrice;

    @Column(name = "modelYear")
    private int modelYear;

    @Column(name = "state")
    private int state;   // 1-available    2-Rented    3-maintenance

    @ManyToOne
    @JoinColumn(name = "model_id")
    private  Model model;

}
