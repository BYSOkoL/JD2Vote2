package by.it_academy.jd2.services.singletone;


import by.it_academy.jd2.services.StyleService;
import by.it_academy.jd2.storage.singletone.StylesStorageSingleton;

public class StyleServiceSingleton {
    private volatile static StyleService instance;

    private StyleServiceSingleton() {
    }

    public static StyleService getInstance() {

        if (instance == null) {
            instance = new StyleService(StylesStorageSingleton.getInstance());
        }

        return instance;
    }
}

