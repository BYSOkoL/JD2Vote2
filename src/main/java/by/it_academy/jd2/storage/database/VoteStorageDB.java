package by.it_academy.jd2.storage.database;

import by.it_academy.jd2.entity.Vote;
import by.it_academy.jd2.storage.api.IStorage;

import by.it_academy.jd2.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteStorageDB implements IStorage<Vote> {

    private static final String INSERT_VOTE_SQL = "INSERT INTO app.vote (artist_id, about, create_at) VALUES (?, ?, ?) RETURNING id";
    private static final String INSERT_VOTE_GENRE_SQL = "INSERT INTO app.cross_vote_genre (vote_id, genre_id) VALUES (?, ?)";
    private static final String SELECT_VOTE_SQL = "SELECT vote.id, about, artist_id, create_at FROM app.vote";
    private static final String SELECT_VOTE_BY_ID_SQL = "SELECT id, about, artist_id, create_at FROM app.vote WHERE id = ?";
    private static final String SELECT_GENRE_BY_VOTE_ID = "SELECT genre_id FROM app.cross_vote_genre WHERE vote_id = ?";
    private static final String SQL_DELETE_VOTE = "DELETE FROM app.vote WHERE id = ?";
    private static final String SQL_DELETE_VOTE_IN_CROSS = "DELETE FROM app.cross_vote_genre WHERE vote_id = ?";

    public VoteStorageDB() {
    }

    @Override
    public Long create(Vote vote) {
        long vote_id = 0;
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(INSERT_VOTE_SQL);
             PreparedStatement statement2 = connect.prepareStatement(INSERT_VOTE_GENRE_SQL);
        ) {

            statement.setLong(1, vote.getArtistId());
            statement.setString(2, vote.getInfo());
            statement.setObject(3, vote.getCreatedAt());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                vote_id = resultSet.getLong("id");
            }

            List<Long> genresId = vote.getGenresId();
            for (Long genreId : genresId) {
                statement2.setLong(1, vote_id);
                statement2.setLong(2, genreId);
                statement2.executeUpdate();
            }

            return vote_id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Vote get(Long id) {
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_VOTE_BY_ID_SQL);
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Vote vote = null;
            if (resultSet.next()) {
                vote = buildVoteEntity(resultSet);
            }
            List<Long> genresByVoice = getGenresByVote(vote.getId(), connect);
            vote.setGenresId(genresByVoice);
            return vote;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Long> getGenresByVote(Long vote_id, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GENRE_BY_VOTE_ID)) {
            preparedStatement.setLong(1, vote_id);
            ResultSet resultSet2 = preparedStatement.executeQuery();
            return getListGenres(resultSet2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Long, Vote> getAll() {
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_VOTE_SQL);
        ) {
            ResultSet resultSet = statement.executeQuery();
            Map<Long, Vote> mapVotes = new HashMap<>();
            while (resultSet.next()) {
                Vote vote = buildVoteEntity(resultSet);
                Long id = vote.getId();
                List<Long> genresByVoice = getGenresByVote(id, connect);
                vote.setGenresId(genresByVoice);
                mapVotes.put(id, vote);
            }
            return mapVotes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Long> getListGenres(ResultSet resultSet) throws SQLException {
        List<Long> genreId = new ArrayList<>();
        while (resultSet.next()) {
            genreId.add(resultSet.getLong("genre_id"));
        }
        return genreId;
    }

    private Vote buildVoteEntity(ResultSet resultSet) throws SQLException {
        return Vote.builder()
                .setId(resultSet.getLong("id"))
                .setArtist(resultSet.getLong("artist_id"))
                .setInfo(resultSet.getString("about"))
                .createdAt(resultSet.getObject("create_at", OffsetDateTime.class))
                .build();
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(SQL_DELETE_VOTE);
             PreparedStatement statementCross = connect.prepareStatement(SQL_DELETE_VOTE_IN_CROSS);
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
