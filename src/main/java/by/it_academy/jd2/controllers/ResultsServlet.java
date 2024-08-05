package by.it_academy.jd2.controllers;

import by.it_academy.jd2.Storage.Storage;
import by.it_academy.jd2.dto.ResultVoteDTO;
import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.services.VoteService;
import by.it_academy.jd2.services.api.IVoteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/results")
public class ResultsServlet extends HttpServlet {
    private final static VoteService voteService = VoteService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        ResultVoteDTO resultVoteDTO = voteService.result();
        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        writer.println("<h1>Results</h1>");
        writer.println("<h2>Singers</h2>");
        writer.println("<p>Singer: " + resultVoteDTO.getSingerMaxKey() + ". Votes: " + resultVoteDTO.getSingerkey() + "</p>");
        writer.println("<h2>Style</h2>");
        writer.println("<p>Style: " + resultVoteDTO.getStyleMaxKey() + ". Votes: " + resultVoteDTO.getStylekey() + "</p>");
        writer.println("<h2>Message</h2>");
        writer.println("<p>Message: " + resultVoteDTO.getMessage());
        writer.println("</body></html>");
    }
}

