package com.example.rentACar.Data.dataAccess.entities.concretes;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "models")
// Lombok ile constructor getter setter oluşturma
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // autoincrement olmasını sağlar.
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="brand_id")       // Tablomuz için veritabanına bir brand_id koyacak ve aradaki ilişkiyi tanımlayacak.
    private Brand brand;

    @OneToMany(mappedBy = "model")
    private List<Car> cars;
}
