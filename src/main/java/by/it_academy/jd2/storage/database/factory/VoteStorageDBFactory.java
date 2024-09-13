package by.it_academy.jd2.storage.database.factory;

import by.it_academy.jd2.entity.Vote;
import by.it_academy.jd2.storage.api.IStorage;
import by.it_academy.jd2.storage.database.VoteStorageDB;

public class VoteStorageDBFactory {
    private static final IStorage<Vote> instance = new VoteStorageDB();
    private VoteStorageDBFactory() {}

    public static IStorage<Vote> getInstance() {
        return instance;
    }
}
