package repository.interfaces;

import models.Transmission;
import models.TransmissionType;

public interface TransmissionsRepository extends CrudRepository<Transmission> {
    Transmission getTransmissionByType(TransmissionType type);
}
