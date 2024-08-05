/*
package by.it_academy.jd2;

import by.it_academy.jd2.Storage.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


@WebServlet(urlPatterns = "/vote")
public class VoteServlet extends HttpServlet{

    Map<String, Integer> singerMap = new HashMap<>();

    Map<String, Integer> styleMap = new HashMap<>();

    List<String> textCommentList = new ArrayList<>();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
// SINGER
        String singerVal = req.getParameter("singer");

        if (singerVal != null) {
            if (singerMap.containsKey(singerVal)) {
                int repeatNumber = singerMap.get(singerVal);
                repeatNumber += 1;
                singerMap.put(singerVal, repeatNumber);
            } else singerMap.put(singerVal, 1);
        }

        String singerMaxKey = null;
        for (String key : singerMap.keySet()) {
            if (singerMaxKey == null || singerMap.get(key) > singerMap.get(singerMaxKey)) {
                singerMaxKey = String.valueOf(key);
            }
        }
//SINGER
// STYLE
        String[] styleArr = req.getParameterValues("style");

        for (String styleRepeat: styleArr) {
            if (styleArr.length < 2 || styleArr.length > 4) {
            writer.write("Error, minimum 3 choice, maximum 5");
            break;
            }

            Integer repeatNumber = styleMap.get(styleRepeat);
//2
        if (repeatNumber == null) {
                repeatNumber = 0;
            }
            repeatNumber++;
            styleMap.put(styleRepeat, repeatNumber);


//1
        if (styleMap.containsKey(styleRepeat)) {
                    int repeatNumber = styleMap.get(styleRepeat); //
                    repeatNumber += 1;
                    styleMap.put(styleRepeat, repeatNumber);
                } else {
                    styleMap.put(styleRepeat, 1);
                }


            styleMap.compute(styleRepeat, (k,v) -> {
                if (v == null) {
                    return 1;
                }
                return v+1;
            });
        }
        String styleMaxKey = null;
        for(String key : styleMap.keySet()){
            if(styleMaxKey == null || styleMap.get(key) > styleMap.get(styleMaxKey)){
                styleMaxKey = String.valueOf(key);
            }
        }

//STYLE
// MESSAGE
        String textComment = req.getParameter("message");

        if (!Objects.equals(textComment,(null))) {
            textCommentList.add(textComment);
        }
//MESSAGE
    }
}
*/
