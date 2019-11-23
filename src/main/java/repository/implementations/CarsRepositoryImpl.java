package repository.implementations;

import helpers.Database;
import models.*;
import repository.interfaces.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarsRepositoryImpl implements CarsRepository {

    private ModelsRepository modelsRepository;
    private MakersRepository makersRepository;
    private EnginesRepository enginesRepository;
    private TransmissionsRepository transmissionsRepository;

    public CarsRepositoryImpl() {
        makersRepository = new MakersRepositoryImpl();
        modelsRepository = new ModelsRepositoryImpl();
        enginesRepository = new EnginesRepositoryImpl();
        transmissionsRepository = new TransmissionsRepositoryImpl();
    }

    private RowMapper<Car> rowMapper = (row) -> {
        Long id = row.getLong("id");
        Maker maker = makersRepository.get(row.getLong("maker_id")).get();
        Model model = modelsRepository.get(row.getLong("model_id")).get();
        Integer year = row.getInt("year");
        Integer averagePrice = row.getInt("avg_price");
        CarType type = CarType.valueOf(row.getString("type"));
        Engine engine = enginesRepository.get(row.getLong("engine")).get();
        Transmission transmission = transmissionsRepository.get(row.getLong("transmission")).get();
        Integer capacity = row.getInt("capacity");
        String imagePath = row.getString("img_src");
        return new CarBuilder()
                .setId(id)
                .setMaker(maker)
                .setModel(model)
                .setYear(year)
                .setImagePath(imagePath)
                .setAvgPrice(averagePrice)
                .setCapacity(capacity)
                .setTransmission(transmission)
                .setEngine(engine)
                .setType(type)
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
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from car where id = ?");
            statement.setLong(1, id);
            ResultSet car = statement.executeQuery();
            if (car.next()) {
                return Optional.ofNullable(rowMapper.mapRow(car));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
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
                cars.add(Optional.ofNullable(rowMapper.mapRow(results)));
            }
            statement.close();
            connection.close();
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
            statement.close();
            connection.close();
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
