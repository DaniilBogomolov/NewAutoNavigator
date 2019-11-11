package repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T model);
    Optional<T> get(Long id);
    List<Optional<T>> getAll();
    void update(T model, String[] params);
    void delete(Long id);
}
