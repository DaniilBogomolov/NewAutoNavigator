package repository.implementations;

import helpers.Database;
import models.*;
import repository.interfaces.CarsRepository;
import repository.interfaces.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarsRepositoryImpl implements CarsRepository {

    private RowMapper<Car> rowMapper = (row) -> {
        Long id = row.getLong("id");
        String imagePath = row.getString("img_src");
        return new CarBuilder()
                .setId(id)
                .setImagePath(imagePath)
                .createCar();
    };


    @Override
    public void save(Car model) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("insert into car(maker_id, year, type, avg_price, engine, transmission, capacity, img_src) values (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setLong(1, model.getMaker().getId());
            statement.setInt(2, model.getYear());
            statement.setString(3, model.getType().toString());
            statement.setInt(4, model.getAvgPrice());
            statement.setLong(5, model.getEngine().getId());
            statement.setLong(6, model.getTransmission().getId());
            statement.setInt(7, model.getCapacity());
            statement.setString(8, model.getMaker().getMakerName() + "_" + model.getModel().getName() + ".png");
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Car> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Optional<Car>> getAll() {
        List<Optional<Car>> cars = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from car");
            ResultSet results = statement.executeQuery();
            while (results.next()) {
//                cars.add(rowMapper.mapRow(results));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return cars;
    }

    @Override
    public void update(Car model, String[] params) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Car> getCarsWithCustomScript(String sqlScript) {
        List<Car> cars = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sqlScript);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Car car = rowMapper.mapRow(result);
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return cars;
    }

    @Override
    public List<CarType> getAllTypes() {
        return null;
    }
}
