package by.it_academy.jd2.controllers;

import by.it_academy.jd2.dto.SingerDTO;
import by.it_academy.jd2.services.api.ISingerService;
import by.it_academy.jd2.services.singletone.SingerServiceSingleton;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SingerServlet", urlPatterns = "/singers")
public class SingerServlet extends HttpServlet {

    private static final String BR = "<br>";
    private static final String HEADER = "<p><b>Singer:</b></p>";

    private final ISingerService singerService = SingerServiceSingleton.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<SingerDTO> singerDTOList = singerService.getContent();
        StringBuilder str = new StringBuilder();
        for(SingerDTO singerDTO : singerDTOList){
            str.append(singerDTO.getId()).append(" - ").append(singerDTO.getName()).append(BR);
        }
        String htmlResult = HEADER + str;

        writer.write(htmlResult);
    }
}
