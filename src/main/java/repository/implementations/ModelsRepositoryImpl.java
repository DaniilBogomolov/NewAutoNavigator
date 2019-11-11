package repository.implementations;

import helpers.Database;
import models.Model;
import repository.interfaces.ModelsRepository;
import repository.interfaces.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ModelsRepositoryImpl implements ModelsRepository {

    private RowMapper<Model> rowMapper = (row) -> {
        Long id = row.getLong("id");
        String modelName = row.getString("model_name");
        return new Model(id, modelName);
    };

    @Override
    public void save(Model model) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("insert into car_model(model_name) select ? where not exists (select model_name from car_model where model_name = ?)");
            statement.setString(1, model.getName());
            statement.setString(2, model.getName());
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Model> get(Long id) {
        Model model = null;
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from car_model where id = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                model = rowMapper.mapRow(result);
            }
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.ofNullable(model);
    }

    @Override
    public List<Optional<Model>> getAll() {
        return null;
    }

    @Override
    public void update(Model model, String[] params) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Model getModelByName(String name) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from car_model where model_name = ?");
            statement.setString(1, name);
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
