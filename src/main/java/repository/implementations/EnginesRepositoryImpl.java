package repository.implementations;

import helpers.Database;
import models.Engine;
import models.EngineType;
import repository.interfaces.EnginesRepository;
import repository.interfaces.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnginesRepositoryImpl implements EnginesRepository {

    private RowMapper<Engine> rowMapper = (row) -> {
        Long id = row.getLong("id");
        EngineType engineType = EngineType.valueOf(row.getString("type"));
        return new Engine(id, engineType);
    };

    @Override
    public void save(Engine model) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("insert into car_engine(type) select ? where not exists (select type from car_engine where type = ?)");
            statement.setString(1, model.getEngineType().toString());
            statement.setString(2, model.getEngineType().toString());
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Engine> get(Long id) {
        Engine engine = null;
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from car_engine where id = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                engine = rowMapper.mapRow(result);
            }
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.ofNullable(engine);
    }

    @Override
    public List<Optional<Engine>> getAll() {
        List<Optional<Engine>> engines = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from car_engine");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                engines.add(Optional.ofNullable(rowMapper.mapRow(result)));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return engines;
    }

    @Override
    public void update(Engine model, String[] params) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Engine getEngineByType(EngineType type) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from car_engine where type = ?");
            statement.setString(1, type.toString());
            ResultSet engine = statement.executeQuery();
            if (engine.next()) {
                return rowMapper.mapRow(engine);
            }
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }
}
