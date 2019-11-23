package repository.implementations;

import helpers.Database;
import models.Maker;
import repository.interfaces.MakersRepository;
import repository.interfaces.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MakersRepositoryImpl implements MakersRepository {

    private RowMapper<Maker> rowMapper = (row) -> {
        Long id = row.getLong("id");
        String name = row.getString("maker_name");
      return new Maker(id, name);
    };

    @Override
    public void save(Maker model) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("insert into car_maker(maker_name) select ? where not exists (select maker_name from car_maker where maker_name = ?)");
            statement.setString(1, model.getMakerName());
            statement.setString(2, model.getMakerName());
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Maker> get(Long id) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from car_maker where id = ?");
            statement.setLong(1, id);
            ResultSet maker = statement.executeQuery();
            if (maker.next()) {
                return Optional.ofNullable(rowMapper.mapRow(maker));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Optional<Maker>> getAll() {
        try {
            List<Optional<Maker>> makers = new ArrayList<>();
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from car_maker");
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                makers.add(Optional.ofNullable(rowMapper.mapRow(results)));
            }
            statement.close();
            connection.close();
            return makers;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(Maker model, String[] params) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Maker getMakerByName(String name) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from car_maker where maker_name = ?");
            statement.setString(1, name);
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
