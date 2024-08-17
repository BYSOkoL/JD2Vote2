package by.it_academy.jd2.storage.singletone;

import by.it_academy.jd2.dto.SavedVoteDTO;
import by.it_academy.jd2.storage.VoteStorage;
import by.it_academy.jd2.storage.api.IVoteStorage;

public class VoteStorageSingleton {
    private volatile static VoteStorage instance;

    private VoteStorageSingleton() {
    }

    public static IVoteStorage<SavedVoteDTO> getInstance() {

        if (instance == null) {
            instance = new VoteStorage();
        }

        return instance;
    }
}
