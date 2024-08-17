package by.it_academy.jd2.storage.singletone;

import by.it_academy.jd2.dto.StyleDTO;
import by.it_academy.jd2.storage.StylesStorage;
import by.it_academy.jd2.storage.api.IStylesStorage;

public class StylesStorageSingleton {
    private volatile static StylesStorage instance;

    private StylesStorageSingleton() {
    }

    public static IStylesStorage<StyleDTO> getInstance() {

        if (instance == null) {
            instance = new StylesStorage();
        }

        return instance;
    }
}
