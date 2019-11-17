package services;

import repository.implementations.CarsRepositoryImpl;
import repository.interfaces.CarsRepository;

public class CarsService {

    private CarsRepository carsRepository;

    public CarsService() {
        carsRepository = new CarsRepositoryImpl();
    }


}
