package by.it_academy.jd2.storage.database;

import by.it_academy.jd2.entity.Artist;
import by.it_academy.jd2.storage.api.IStorage;
import by.it_academy.jd2.util.DBUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ArtistStorageDB implements IStorage<Artist> {

    private static final String SQL_INSERT_ARTIST = "INSERT INTO app.artist (name) VALUES (?) returning id";
    private static final String SQL_GET_ARTIST = "SELECT name FROM app.artist WHERE id = ?";
    private static final String SQL_GET_ALL_ARTIST = "SELECT id, name FROM app.artist";
    private static final String SQL_DELETE_ARTIST = "DELETE FROM app.artist WHERE id = ?";
    private static final String SQL_UPDATE_ARTIST_IN_VOTE = "UPDATE app.vote SET artist_id = null WHERE artist_id = ?";


    public ArtistStorageDB() {
    }
    private long counter;

    @Override
    public Long create(Artist artist) {
        Long id = null;
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(SQL_INSERT_ARTIST);
        ) {
            statement.setString(1, artist.getName());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Artist get(Long id) {
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(SQL_GET_ARTIST);
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Artist artist = null;
            if (resultSet.next()) {
                artist = new Artist(
                        id,
                        resultSet.getString("name"));
            }
            return artist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Long, Artist> getAll() {
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(SQL_GET_ALL_ARTIST);
        ) {
            Map<Long, Artist> result = new HashMap<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                result.put(id, new Artist(id, name));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(SQL_UPDATE_ARTIST_IN_VOTE);
             PreparedStatement statementVote = connect.prepareStatement(SQL_DELETE_ARTIST);
        ) {

            connect.setAutoCommit(false);
            try {
                statementVote.setLong(1, id);
                statementVote.executeUpdate();
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
