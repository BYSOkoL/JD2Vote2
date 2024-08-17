package by.it_academy.jd2.storage.singletone;

import by.it_academy.jd2.dto.SingerDTO;
import by.it_academy.jd2.storage.SingersStorage;
import by.it_academy.jd2.storage.api.ISingersStorage;

public class SingerStorageSingleton {
    private volatile static SingersStorage instance;

    private SingerStorageSingleton() {
    }

    public static ISingersStorage<SingerDTO> getInstance() {
        if (instance == null) {
            instance = new SingersStorage();
        }
        return instance;
    }
}
