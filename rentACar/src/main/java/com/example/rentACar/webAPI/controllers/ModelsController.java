package com.example.rentACar.webAPI.controllers;

import com.example.rentACar.business.abstracts.ModelService;
import com.example.rentACar.business.requests.CreateModelRequest;
import com.example.rentACar.business.responses.GetAllModelsResponse;
import com.example.rentACar.business.responses.GetByIdResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController     // Restcontroller annotation
@RequestMapping("/api/models")
@NoArgsConstructor
public class ModelsController {
    private ModelService modelService;

    @Autowired
    public ModelsController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/getAll")      // Anlamı /api/brands/getAll diye bir request gelirse bu metot çalışacak.
    public List<GetAllModelsResponse> getAll() {    // Burada List'in içindeki Brand'i GetAllBrandsResponse ile değiştirdik çünkü sadece response kısmını kullanıcıya vermek istiyoruz.
        return modelService.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(CreateModelRequest createModelRequest) {
        this.modelService.add(createModelRequest);
    }

    @GetMapping("/{id}")    //süslü parantez içi variabledır. Yani pathden al ve ordaki id değerini parametre olarak kullan demektir.
    public GetByIdResponse getById(@PathVariable int id) {
        return modelService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void  delete(@PathVariable int id){
        this.modelService.delete(id);
    }

}
