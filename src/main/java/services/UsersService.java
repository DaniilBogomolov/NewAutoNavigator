package services;

import repository.implementations.UsersRepositoryImpl;
import repository.interfaces.UsersRepository;

public class UsersService {

    private UsersRepository usersRepository;

    public UsersService() {
        usersRepository = new UsersRepositoryImpl();
    }


    public void removeFavourite(Integer carId, String username) {
        usersRepository.changeFavouriteCars("remove", carId, username);
    }

    public void addFavourite(Integer carId, String username) {
        usersRepository.changeFavouriteCars("add", carId, username);
    }
}
