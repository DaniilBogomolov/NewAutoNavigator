package repository.interfaces;

import models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> getUserByUsername(String input);
}
