package repository.implementations;

import helpers.Database;
import models.Car;
import models.Role;
import models.User;
import repository.interfaces.CarsRepository;
import repository.interfaces.RowMapper;
import repository.interfaces.UsersRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsersRepositoryImpl implements UsersRepository {

    private CarsRepository carsRepository;

    private RowMapper<User> rowMapper = (row) -> {
      Long id = row.getLong("id");
      String username = row.getString("username");
      String password = row.getString("password");
      Role role = Enum.valueOf(Role.class, row.getString("role"));
      List<Integer> carsID = new ArrayList<>(Arrays.asList((Integer[])row.getArray("favourite_cars").getArray()));
      List<Car> cars = carsID.stream().map(carID -> carsRepository.get(carID.longValue())).map(Optional::get).collect(Collectors.toList());
      return new User(username, password, id, role, cars);
    };

    public UsersRepositoryImpl() {
        carsRepository = new CarsRepositoryImpl();
    }

    @Override
    public void save(User model) {
        try {
            Connection connection = helpers.Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("insert into client(username, password, role) values (?, ?, ?)");
            statement.setString(1, model.getUsername());
            statement.setString(2, model.getPassword());
            statement.setString(3, model.getRole().name());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        User user = null;
        try {
            Connection connection = helpers.Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from client where id = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user = rowMapper.mapRow(result);
            }
            statement.close();
            connection.close();
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Optional<User>> getAll() {
        List<Optional<User>> users = new ArrayList<>();
        try {
            Connection connection = helpers.Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from client");
            ResultSet usersResultSet = statement.executeQuery();
            while (usersResultSet.next()) {
                users.add(Optional.ofNullable(rowMapper.mapRow(usersResultSet)));
            }
            statement.close();
            connection.close();
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(User model, String[] params) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        try {
            Connection connection = helpers.Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from client where username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = rowMapper.mapRow(resultSet);
            }
            statement.close();
            connection.close();
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void changeFavouriteCars(String action, Integer cardID, String username) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement;
            if (action.equals("add")) {
                statement = connection.prepareStatement("update client set favourite_cars = (select favourite_cars || ? from client where username = ?) where username = ?;");
            } else {
                statement = connection.
                        prepareStatement("update client set favourite_cars = (select array_remove(favourite_cars, ?) from client where username = ?) where username = ?;");
            }
            statement.setInt(1, cardID);
            statement.setString(2, username);
            statement.setString(3, username);
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
