package by.it_academy.jd2.controllers;

import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.services.VoteService;
import by.it_academy.jd2.services.singletone.VoteServiceSingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static by.it_academy.jd2.util.GetSingerId.getId;
import static by.it_academy.jd2.util.GetStyleID.getStyleIDlist;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    private final VoteService voteService = VoteServiceSingleton.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> dataSingers = new ArrayList<>();
        dataSingers.add("Eminem");
        dataSingers.add("2Pac");
        dataSingers.add("Sabaton");
        dataSingers.add("Metallica");

        List<String> dataStyles = new ArrayList<>();
        dataStyles.add("HipHop");
        dataStyles.add("Rock");
        dataStyles.add("Jazz");
        dataStyles.add("Funk");
        dataStyles.add("Metal");
        dataStyles.add("Disko");
        dataStyles.add("Pop");
        dataStyles.add("Rap");
        dataStyles.add("Punk");
        dataStyles.add("DnB");

req.setAttribute("singers", dataSingers);
req.setAttribute("styles", dataStyles);

        req.getRequestDispatcher("template/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");


            String singer = req.getParameter("singer");
            String[] styles = req.getParameterValues("style");
            String message =req.getParameter("message");
            Long singerId = getId(singer);
            VoteDTO voteDTO = new VoteDTO(singerId, getStyleIDlist(styles), message);
            voteService.addVote(voteDTO);
            resp.sendRedirect(req.getContextPath() + "/vote_result");

    }
}