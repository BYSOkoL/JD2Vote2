package by.it_academy.jd2.controllers;

import by.it_academy.jd2.dto.StyleDTO;
import by.it_academy.jd2.services.api.IStyleService;
import by.it_academy.jd2.services.singletone.StyleServiceSingleton;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StyleServlet", urlPatterns = "/styles")
public class StyleServlet extends HttpServlet {

    private static final String BR = "<br>";
    private static final String HEADER = "<p><b>Select min 3 max 5 styles:</b></p>";
    private static final String FOOTER = "<p><b>Message: </b></p>";

    private final IStyleService styleService = StyleServiceSingleton.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<StyleDTO> styleDTOList = styleService.getContent();
        StringBuilder str = new StringBuilder();
        for(StyleDTO styleDTO :styleDTOList){
            str.append(styleDTO.getId()).append(" - ").append(styleDTO.getTitle()).append(BR);
        }
        String htmlResult = HEADER + str + FOOTER;
        writer.write(htmlResult);
    }


}
