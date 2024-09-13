package by.it_academy.jd2.services;

import by.it_academy.jd2.util.DBUtils;
import by.it_academy.jd2.dto.dbresult.ArtistVoteCount;
import by.it_academy.jd2.dto.dbresult.GenreVoteCount;
import by.it_academy.jd2.dto.dbresult.VoteMessage;
import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class VoteDAO {

    public List<GenreVoteCount> getGenreVoteCounts() throws SQLException {
        List<GenreVoteCount> genreVoteCounts = new ArrayList<>();
        String sql = "SELECT g.name AS genre_name, COUNT(cv.vote_id) AS vote_count " +
                "FROM app.genre g " +
                "JOIN app.cross_vote_genre cv ON g.id = cv.genre_id " +
                "GROUP BY g.name " +
                "ORDER BY vote_count DESC";
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String genreName = resultSet.getString("genre_name");
                int voteCount = resultSet.getInt("vote_count");
                genreVoteCounts.add(new GenreVoteCount(genreName, voteCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genreVoteCounts;
    }

    public List<ArtistVoteCount> getArtistVoteCounts() {
        List<ArtistVoteCount> artistVoteCounts = new ArrayList<>();
        String sql = "SELECT a.name AS artist_name, COUNT(v.id) AS vote_count " +
                "FROM app.artist a " +
                "JOIN app.vote v ON a.id = v.artist_id " +
                "GROUP BY a.name " +
                "ORDER BY vote_count DESC";
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String artistName = resultSet.getString("artist_name");
                int voteCount = resultSet.getInt("vote_count");
                artistVoteCounts.add(new ArtistVoteCount(artistName, voteCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artistVoteCounts;
    }

    public List<VoteMessage> getVoteMessages() {
        List<VoteMessage> voteMessages = new ArrayList<>();
        String sql = "SELECT about AS message, create_at AS timestamp FROM app.vote ORDER BY create_at DESC";
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String message = resultSet.getString("message");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                ZonedDateTime zonedDateTime = timestamp.toInstant().atZone(ZoneId.systemDefault());
                voteMessages.add(new VoteMessage(message, zonedDateTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voteMessages;
    }
}


