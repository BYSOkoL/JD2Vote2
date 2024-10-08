package by.it_academy.jd2.storage.database.factory;

import by.it_academy.jd2.entity.Genre;
import by.it_academy.jd2.storage.api.IStorage;
import by.it_academy.jd2.storage.database.GenreStorageDB;

public class GenreStorageDBFactory {
    private static final IStorage<Genre> instance = new GenreStorageDB();
    private GenreStorageDBFactory() {}

    public static IStorage<Genre> getInstance() {
        return instance;
    }
}
