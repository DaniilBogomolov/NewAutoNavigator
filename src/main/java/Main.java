import repository.implementations.UsersRepositoryImpl;
import repository.interfaces.UsersRepository;

import java.sql.Array;

public class Main {
    public static void main(String[] args) {
        UsersRepository usersRepository = new UsersRepositoryImpl();
        System.out.println(usersRepository.getUserByUsername("admin").get().getFavouriteCars().contains(5));
    }
}
