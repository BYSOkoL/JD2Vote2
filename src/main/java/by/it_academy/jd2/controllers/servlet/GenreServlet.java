package by.it_academy.jd2.controllers.servlet;


import by.it_academy.jd2.services.api.IStyleService;
import by.it_academy.jd2.services.factory.ServiceGenreFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet(urlPatterns = "/admin/genre")
public class GenreServlet extends HttpServlet {
    public static final String ERROR_GENRE_NAME = "Genre name must not be blank or consist of spaces.";
    public static final String GENRE_ADDED = "Genre added with ID: ";
    public static final String GENRE_NOT_ADDED = "Failed to add genre.";
    public static final String GENRE_DELETED = "Genre has been deleted.";
    public static final String GENRE_NOT_DELETED = "Failed to delete genre.";

    public static final String PARAM_DELETE_GENRE_ERROR = "deleteGenreErr";
    public static final String PARAM_DELETE_GENRE = "deleteGenre";
    public static final String ATTR_ERROR_GENRE = "genreErr";
    public static final String ATTR_ADD_GENRE = "genreAdd";

    IStyleService genreService = ServiceGenreFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("genres", genreService.getAll());
        req.getRequestDispatcher("/WEB-INF/jsp/genre.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String genre = req.getParameter("genre");
        String deleteGenre = req.getParameter(PARAM_DELETE_GENRE);

        if(genre != null) {
            addingGenre(req, genre);
        }

        if(deleteGenre != null) {
            deletionGenre(req, deleteGenre);
        }

        doGet(req, resp);
    }


    private void addingGenre(HttpServletRequest req, String genre) {
        try {
            if(genre.isBlank()) {
                throw new IllegalArgumentException(ERROR_GENRE_NAME);
            }

            Long id = genreService.create(genre);
            req.setAttribute(ATTR_ADD_GENRE, GENRE_ADDED + id);

        } catch (IllegalArgumentException e) {
            req.setAttribute(ATTR_ERROR_GENRE, GENRE_NOT_ADDED + e.getMessage());
        }
    }

    private void deletionGenre(HttpServletRequest req, String deleteGenre) {
        try {
            boolean delete = genreService.delete(Long.valueOf(deleteGenre));

            if (delete) {
                req.setAttribute(PARAM_DELETE_GENRE, GENRE_DELETED);
            } else {
                req.setAttribute(PARAM_DELETE_GENRE_ERROR, GENRE_NOT_DELETED);
            }

        } catch (RuntimeException e) {
            req.setAttribute(PARAM_DELETE_GENRE_ERROR, GENRE_NOT_DELETED + " " + e.getMessage());
        }
    }
}
