package repository.interfaces;

import models.Model;

public interface ModelsRepository extends CrudRepository<Model> {
    Model getModelByName(String name);
}
