package com.example.rentACar.Data.dataAccess.entities.concretes;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name="brands")
// Lombok ile constructor getter setter oluşturma
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // autoincrement olmasını sağlar.
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    // one to many ilişki (Brand ile Model arasında)
    @OneToMany(mappedBy = "brand")  //mappedBy hangi alan ile ilişkilendireleciğini belirttiğimiz yer
    private List<Model> models;
}

