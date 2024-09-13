package by.it_academy.jd2.controllers.servlet;


import by.it_academy.jd2.services.VoteDAO;

import by.it_academy.jd2.services.factory.DAOVoteFactory;
import by.it_academy.jd2.dto.dbresult.ArtistVoteCount;
import by.it_academy.jd2.dto.dbresult.GenreVoteCount;
import by.it_academy.jd2.dto.dbresult.VoteMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/results")
public class ResultsServlet extends HttpServlet {
    private VoteDAO voteDAO = DAOVoteFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<GenreVoteCount> genreVoteCounts = null;
        try {
            genreVoteCounts = voteDAO.getGenreVoteCounts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<ArtistVoteCount> artistVoteCounts = voteDAO.getArtistVoteCounts();
        List<VoteMessage> voteMessages = voteDAO.getVoteMessages();


        request.setAttribute("genreVoteCounts", genreVoteCounts);
        request.setAttribute("artistVoteCounts", artistVoteCounts);
        request.setAttribute("voteMessages", voteMessages);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/results.jsp");
        dispatcher.forward(request, response);
    }
}
