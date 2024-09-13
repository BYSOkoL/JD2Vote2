package by.it_academy.jd2.controllers.servlet;


import by.it_academy.jd2.services.VoteDAO;

import by.it_academy.jd2.util.dbresult.ArtistVoteCount;
import by.it_academy.jd2.util.dbresult.GenreVoteCount;
import by.it_academy.jd2.util.dbresult.VoteMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(urlPatterns = "/resultOfVote")
public class ResultsServlet extends HttpServlet {
    private VoteDAO voteDAO = new VoteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получение данных из базы данных
        List<GenreVoteCount> genreVoteCounts = null;
        try {
            genreVoteCounts = voteDAO.getGenreVoteCounts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<ArtistVoteCount> artistVoteCounts = voteDAO.getArtistVoteCounts();
        List<VoteMessage> voteMessages = voteDAO.getVoteMessages();

        // Установка атрибутов для передачи на JSP
        request.setAttribute("genreVoteCounts", genreVoteCounts);
        request.setAttribute("artistVoteCounts", artistVoteCounts);
        request.setAttribute("voteMessages", voteMessages);

        // Перенаправление на JSP страницу
        RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
        dispatcher.forward(request, response);
    }
}
