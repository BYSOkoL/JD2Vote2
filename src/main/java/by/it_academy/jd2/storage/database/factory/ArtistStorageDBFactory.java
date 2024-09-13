package by.it_academy.jd2.storage.database.factory;

import by.it_academy.jd2.entity.Artist;
import by.it_academy.jd2.storage.api.IStorage;
import by.it_academy.jd2.storage.database.ArtistStorageDB;

public class ArtistStorageDBFactory {
    private static final IStorage<Artist> instance = new ArtistStorageDB();
    private ArtistStorageDBFactory() {}

    public static IStorage<Artist> getInstance() {
        return instance;
    }
}
