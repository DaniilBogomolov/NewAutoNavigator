package repository.implementations;

import helpers.Database;
import models.Transmission;
import models.TransmissionType;
import repository.interfaces.RowMapper;
import repository.interfaces.TransmissionsRepository;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransmissionsRepositoryImpl implements TransmissionsRepository {

    private RowMapper<Transmission> rowMapper = (row) -> {
        Long id = row.getLong("id");
        TransmissionType type = TransmissionType.valueOf(row.getString("type"));
        return new Transmission(id, type);
    };

    @Override
    public void save(Transmission model) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("insert into car_transmission(type) select ? where not exists (select type from car_transmission where type = ?)");
            statement.setString(1, model.getTransmissionType().toString());
            statement.setString(2, model.getTransmissionType().toString());
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Transmission> get(Long id) {
        Transmission transmission = null;
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from car_transmission where id = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                transmission = rowMapper.mapRow(result);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.ofNullable(transmission);
    }

    @Override
    public List<Optional<Transmission>> getAll() {
        List<Optional<Transmission>> transmissions = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from car_transmission");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                transmissions.add(Optional.ofNullable(rowMapper.mapRow(result)));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return transmissions;
    }

    @Override
    public void update(Transmission model, String[] params) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Transmission getTransmissionByType(TransmissionType type) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from car_transmission where type = ?");
            statement.setString(1, type.toString());
            ResultSet engine = statement.executeQuery();
            if (engine.next()) {
                return rowMapper.mapRow(engine);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }
}
