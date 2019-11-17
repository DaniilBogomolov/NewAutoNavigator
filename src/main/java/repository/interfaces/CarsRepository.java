package repository.interfaces;

import models.Car;
import models.CarType;

import java.util.List;

public interface CarsRepository extends CrudRepository<Car> {
    List<CarType> getAllTypes();
    List<Car> getCarsWithCustomScript(String sqlScript);
}
