package com.example.rentACar.Data.dataAccess.abstracts;

import com.example.rentACar.Data.dataAccess.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// BrandDao (Data Access Object) olarak da kulanılabilir.
// Spring initializerda kurduğumuz Jpa yapısını kullanıyoruz.
// JpaRepository CRUD işlemlerini gerçekleştirebildiğimiz bir interface. içindeki parametreler de Brand'e karşılık integer bir id geleceğini belirtiyor.

// Jpa generic bir yapıda çalışıyo ve bizim için bellekte implemente etmiş gibi bizim için bir class üretiyo.
// Yani spring bu classın concrete'ini bizim için bellekte hazırlamış oluyo.

// Business katmanında BrandRepository brandRepository şeklinde bir nesne ürettiğimizde
// IOC(inversion of cotrol) sebebiyle yeni bir nesne üretmek yerine bellekte üretilmiş olan bir taneyi getirir. Böylelikle bir karmaşa yaşanmaz.
@Repository
public interface BrandRepository extends JpaRepository <Brand, Integer> {
    boolean existsByName(String brandName);   // Spring JPA'nın bir özelliğidir. exists kelimesini görünce bizim için bir sorgu oluşturuyo

}
