package by.it_academy.jd2.services.singletone;

import by.it_academy.jd2.services.SingerService;
import by.it_academy.jd2.storage.singletone.SingerStorageSingleton;

public class SingerServiceSingleton {
    private volatile static SingerService instance;

    private SingerServiceSingleton() {
    }

    public static SingerService getInstance() {

        if (instance == null) {
            instance = new SingerService(SingerStorageSingleton.getInstance());
        }

        return instance;
    }
}
