package by.it_academy.jd2.storage.database;

import by.it_academy.jd2.entity.Genre;
import by.it_academy.jd2.storage.api.IStorage;

import by.it_academy.jd2.util.DBUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class GenreStorageDB implements IStorage<Genre> {
    private static final String SQL_INSERT_GENRE = "INSERT INTO app.genre (name) VALUES (?)";
    private static final String SQL_GET_GENRE = "SELECT name FROM app.genre WHERE id = ?";
    private static final String SQL_GET_ALL_GENRE = "SELECT id, name FROM app.genre";
    private static final String SQL_DELETE_GENRE = "DELETE FROM app.genre WHERE id = ?";
    private static final String SQL_DELETE_GENRE_IN_CROSS = "DELETE FROM app.cross_vote_genre WHERE genre_id = ?";

    public GenreStorageDB() {
    }

    @Override
    public Long create(Genre genre) {
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(SQL_INSERT_GENRE);
        ) {
            statement.setString(1, genre.getName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            Long id = null;
            if (generatedKeys.next()) {
                id = generatedKeys.getLong("id");
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Genre get(Long id) {
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(SQL_GET_GENRE);
        ) {
            Genre genre = null;
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                genre = new Genre(id,
                        resultSet.getString("name"));
            }
            return genre;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Long, Genre> getAll() {
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(SQL_GET_ALL_GENRE);
        ) {
            Map<Long, Genre> result = new HashMap<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                result.put(id, new Genre(id, name));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean delete(Long id) throws SQLException {
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(SQL_DELETE_GENRE);
             PreparedStatement statementCross = connect.prepareStatement(SQL_DELETE_GENRE_IN_CROSS);
        ) {
            connect.setAutoCommit(false);
            try {
                statementCross.setLong(1, id);
                statementCross.executeUpdate();
                statement.setLong(1, id);
                statement.executeUpdate();
                connect.commit();
                return true;
            } catch (SQLException e) {
                connect.rollback();
                return false;
            }
        } catch (SQLException e) {
            throw new SQLException("Ошибка при удалении из базы данных", e);
        }
    }

}
