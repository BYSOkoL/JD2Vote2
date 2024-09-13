package by.it_academy.jd2.controllers.servlet;


import by.it_academy.jd2.services.api.ISingerService;
import by.it_academy.jd2.services.factory.ServiceArtistFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = "/admin/artist")
public class ArtistServlet extends HttpServlet {
    public static final String ERROR_ARTIST_NAME = "Artist name must not be blank or consist of spaces.";
    public static final String ARTIST_ADDED = "Artist added with ID: ";
    public static final String ARTIST_NOT_ADDED = "Failed to add artist.";
    public static final String ARTIST_DELETED = "Artist has been deleted.";
    public static final String ARTIST_NOT_DELETED = "Failed to delete artist.";
    public static final String ATTR_ERROR_MESSAGE = "errorMsg";
    public static final String ATTR_SUCCESS_MESSAGE = "successMsg";
    public static final String ATTR_ARTIST_LIST = "artistList";
    public static final String PARAM_ARTIST_NAME = "artist";
    public static final String PARAM_DELETE_ARTIST = "deleteArtist";
    public static final String PARAM_DELETE_ARTIST_ERROR = "deleteArtistErr";

    private static final ISingerService artistService = ServiceArtistFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ATTR_ARTIST_LIST, artistService.getAll());
        req.getRequestDispatcher("/WEB-INF/jsp/artist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artist = req.getParameter(PARAM_ARTIST_NAME);
        String deleteArtist = req.getParameter(PARAM_DELETE_ARTIST);

        if (!artist.isBlank()) {
            addingArtist(req, artist);
        }
        if(artist.isBlank()) {
            deletionArtist(req, deleteArtist);
         g   doGet(req, resp);
        }
    }

    private void addingArtist(HttpServletRequest req, String artist) {
        try {
            if (artist.isBlank()) {
                throw new IllegalArgumentException(ERROR_ARTIST_NAME);
            }
            Long id = artistService.create(artist);
            req.setAttribute(ATTR_SUCCESS_MESSAGE, ARTIST_ADDED + id);

        } catch (IllegalArgumentException e) {
            req.setAttribute(ATTR_ERROR_MESSAGE, ARTIST_NOT_ADDED + ". " + e.getMessage());
        }
    }

    private static void deletionArtist(HttpServletRequest req, String deleteArtist) {
        if (deleteArtist != null) {
            try {
                boolean delete = artistService.delete(Long.valueOf(deleteArtist));
                if (delete) {
                    req.setAttribute(PARAM_DELETE_ARTIST, ARTIST_DELETED);
                } else {
                    req.setAttribute(PARAM_DELETE_ARTIST_ERROR, ARTIST_NOT_DELETED);
                }
            } catch (RuntimeException e) {
                req.setAttribute(PARAM_DELETE_ARTIST_ERROR, ARTIST_NOT_DELETED + " " + e.getMessage());
            }
        }
    }
}
