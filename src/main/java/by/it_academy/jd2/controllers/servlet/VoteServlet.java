package by.it_academy.jd2.controllers.servlet;


import by.it_academy.jd2.dto.VoteDTO;

import by.it_academy.jd2.services.api.ISingerService;
import by.it_academy.jd2.services.api.IStyleService;
import by.it_academy.jd2.services.api.IVoteService;
import by.it_academy.jd2.services.factory.ServiceArtistFactory;
import by.it_academy.jd2.services.factory.ServiceGenreFactory;
import by.it_academy.jd2.services.factory.ServiceVoteFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;


@WebServlet(urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    public static final String PARAM_GENRE = "genre";
    public static final String PARAM_SINGER = "singer";
    public static final String PARAM_INFO = "info";
    public static final String ATTR_REQUEST_GENRES = "genres";
    public static final String ATTR_REQUEST_SINGERS = "singers";

    private final IVoteService serviceVote = ServiceVoteFactory.getInstance();
    private final ISingerService singerService = ServiceArtistFactory.getInstance();
    private final IStyleService genreService = ServiceGenreFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(ATTR_REQUEST_SINGERS, singerService.getAll());
        req.setAttribute(ATTR_REQUEST_GENRES, genreService.getAll());

        req.getRequestDispatcher("/WEB-INF/jsp/vote.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        VoteDTO infoFromUserDto = VoteDTO.builder()
                .setSinger(req.getParameter(PARAM_SINGER))
                .setStyles(req.getParameterValues(PARAM_GENRE))
                .setDateTime(LocalDateTime.now())
                .setMessage(req.getParameter(PARAM_INFO))
                .build();

        try {
            serviceVote.addVote(infoFromUserDto);
            resp.sendRedirect(req.getContextPath() + "/results");
        } catch (IllegalArgumentException e) {
            try (PrintWriter writer = resp.getWriter()) {
                writer.write("<p>error: " + e.getMessage() + "</p>");
            }
        }

    }
}
