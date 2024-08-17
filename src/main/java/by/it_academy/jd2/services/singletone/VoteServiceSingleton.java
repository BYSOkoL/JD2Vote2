package by.it_academy.jd2.services.singletone;

import by.it_academy.jd2.services.VoteService;
import by.it_academy.jd2.storage.singletone.VoteStorageSingleton;

public class VoteServiceSingleton {
    private volatile static VoteService instance;

    private VoteServiceSingleton() {
    }

    public static VoteService getInstance() {
        if (instance == null) {

            instance = new VoteService(
                    VoteStorageSingleton.getInstance(),
                    SingerServiceSingleton.getInstance(),
                    StyleServiceSingleton.getInstance());
        }

        return instance;
    }
}
