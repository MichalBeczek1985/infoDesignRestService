package pl.michal.motorest.service;

import org.springframework.stereotype.Service;
import pl.michal.motorest.domain.Cars;

import java.util.HashMap;
@Service
public class CarsServiceImpl implements CarsService {

DataService dataService;

    public CarsServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public HashMap<Long,Cars> getAll() {
        return dataService.getAll();
    }

    @Override
    public Cars getById(Long id) {
        return dataService.getById(id);
    }

    @Override
    public void add(Cars car) {
         dataService.add(car);
    }

    @Override
    public void removeById(Long id) {
        dataService.removeById(id);
    }
}
