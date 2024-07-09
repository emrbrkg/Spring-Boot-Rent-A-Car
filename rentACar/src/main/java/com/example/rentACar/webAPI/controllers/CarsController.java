package com.example.rentACar.webAPI.controllers;

import com.example.rentACar.business.abstracts.CarService;
import com.example.rentACar.business.requests.CreateCarRequest;
import com.example.rentACar.business.responses.GetAllCarsResponse;
import com.example.rentACar.business.responses.GetByIdResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//ToDo: Car business rulesa göz at. Neden car eklenemiyor ve üzerinde işlem yapılamıyor, diğer sınıflarla karşılaştırıp bak.


@RestController     // Restcontroller annotation
@RequestMapping("/api/cars")    // Adresleme
public class CarsController {
    private CarService carService;

    @Autowired
    public CarsController (CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/getAll")      // Anlamı /api/brands/getAll diye bir request gelirse bu metot çalışacak.
    public List<GetAllCarsResponse> getAll() {    // Burada List'in içindeki Brand'i GetAllBrandsResponse ile değiştirdik çünkü sadece response kısmını kullanıcıya vermek istiyoruz.
        return carService.getAll();
    }

    @GetMapping("/getById")
    public GetByIdResponse getById(int id) {
        return carService.getById(id);
    }


    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid CreateCarRequest createCarRequest) {
        this.carService.add(createCarRequest);
    }

    @DeleteMapping("/{id}")
    public void  delete(@PathVariable int id){
        this.carService.delete(id);
    }

    @PutMapping("/let be rented")  // Put mapping ??
    @ResponseStatus(code = HttpStatus.OK)
    public void letBeRented(int id) {
        this.carService.letBeRented(id);
    }

    // Yeni ekledim.
    @PutMapping("/update state")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateState(int id, int newState) {
        this.carService.updateState(id, newState);
    }


    @PutMapping("/update daily price")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateDailyPrice(int id, double dailyPrice) {
        this.carService.updateDailyPrice(id, dailyPrice);
    }


    @PutMapping("/update model year")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateModelYear(int id, int year) {
        this.carService.updateModelYear(id, year);
    }


}
