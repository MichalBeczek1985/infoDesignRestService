package pl.michal.motorest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michal.motorest.domain.Cars;
import pl.michal.motorest.service.CarsService;

import java.io.*;
import java.util.*;

@RestController()
@RequestMapping("/cars/")
public class CarController {

     private CarsService carsService;

    public CarController(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public HashMap<Long,Cars> getAll() throws IOException {
         return carsService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cars getCarById(@PathVariable Long id) throws IOException {
        return carsService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addCar(@RequestBody Cars car){
        carsService.add(car);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCarById(@PathVariable Long id){
        carsService.removeById(id);
    }

}
