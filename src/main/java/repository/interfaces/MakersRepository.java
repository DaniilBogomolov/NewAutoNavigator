package repository.interfaces;

import models.Maker;

public interface MakersRepository extends CrudRepository<Maker> {
    Maker getMakerByName(String name);
}
