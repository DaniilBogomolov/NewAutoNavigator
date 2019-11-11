package repository.interfaces;

import models.Engine;
import models.EngineType;

public interface EnginesRepository extends CrudRepository<Engine> {
    Engine getEngineByType(EngineType type);
}
