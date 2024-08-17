package by.it_academy.jd2.services.singletone;

import by.it_academy.jd2.controllers.ResultServlet;
import by.it_academy.jd2.services.ResultService;

public class ResultServiceSingleton {
    private volatile static ResultService instance;

    private ResultServiceSingleton() {
    }

    public static ResultService getInstance() {


        if (instance == null) {
            instance = new ResultService();


        }
        return instance;
    }
}
