package by.it_academy.jd2.controllers;

import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.services.VoteService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    private static final String SINGER = "singer";
    private static final String STYLE = "style";
    private static final String MESSAGE = "message";
    private final VoteService voteService = VoteService.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");


        String singer = req.getParameter(SINGER);
        String[] style = req.getParameterValues(STYLE);
        String message = req.getParameter(MESSAGE);

        VoteDTO vote = new VoteDTO(singer, style, message);
        voteService.create(vote);
    }
}