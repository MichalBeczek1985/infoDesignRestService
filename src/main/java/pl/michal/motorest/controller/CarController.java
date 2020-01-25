package pl.michal.motorest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michal.motorest.domain.Cars;
import pl.michal.motorest.service.CarsService;

import java.io.*;
import java.util.*;

@RestController("/cars/")
public class CarController {

     private CarsService carsService;

    public CarController(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping
    public ResponseEntity<HashMap<Long,Cars>> getAll() throws IOException {
         return new ResponseEntity<>(carsService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cars> getCarById(@PathVariable Long id) throws IOException {
        Cars c = carsService.getById(id);
        return new ResponseEntity<>(carsService.getById(id),
                carsService.getById(id)==null?HttpStatus.NOT_FOUND:
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cars> addCar(@RequestBody Cars car){
        return new ResponseEntity<>(carsService.add(car),
                        HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable Long id){
        carsService.removeById(id);
    }

}
