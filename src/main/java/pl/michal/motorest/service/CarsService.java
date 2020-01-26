package pl.michal.motorest.service;

import pl.michal.motorest.domain.Cars;

import java.util.HashMap;
import java.util.List;

public interface CarsService {
    HashMap<Long, Cars> getAll();
    Cars getById(Long id);
    void add(Cars car);
    void removeById(Long id);
}
