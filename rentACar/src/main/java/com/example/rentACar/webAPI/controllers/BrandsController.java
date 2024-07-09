package com.example.rentACar.webAPI.controllers;

import com.example.rentACar.business.abstracts.BrandService;
import com.example.rentACar.business.requests.CreateBrandRequest;
import com.example.rentACar.business.requests.UpdateBrandRequest;
import com.example.rentACar.business.responses.GetAllBrandsResponse;
import com.example.rentACar.business.responses.GetByIdResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController     // Restcontroller annotation
@RequestMapping("/api/brands")    // Adresleme
public class BrandsController {
    private BrandService brandService;

    @Autowired  // Anlamı bu constructor'ın parametrelerine bak. (brandService) uygulamayı tara ve kim bu parametreyi implement ediyosa onun new'lenmiş halini ver.
    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/getAll")      // Anlamı /api/brands/getAll diye bir request gelirse bu metot çalışacak.
    public List<GetAllBrandsResponse> getAll() {    // Burada List'in içindeki Brand'i GetAllBrandsResponse ile değiştirdik çünkü sadece response kısmını kullanıcıya vermek istiyoruz.
        return brandService.getAll();
    }

    @GetMapping("/{id}")    //süslü parantez içi variabledır. Yani pathden al ve ordaki id değerini parametre olarak kullan demektir.
    public GetByIdResponse getById(@PathVariable int id) {
        return brandService.getById(id);
    }

    // postmapping eklemeler, getmapping data çekmeler için kullanılır.
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid CreateBrandRequest createBrandRequest) {
        this.brandService.add(createBrandRequest);
    }


    @PutMapping
    public void update(@RequestBody() UpdateBrandRequest updateBrandRequest) {
        this.brandService.update(updateBrandRequest);
    }

    // PathVariable keywordu id pathindeki variable' göre hareket et anlamındadır.
    @DeleteMapping("/{id}")
    public void  delete(@PathVariable int id){
        this.brandService.delete(id);
    }


}
