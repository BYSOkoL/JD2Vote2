package by.it_academy.jd2.services.factory;


import by.it_academy.jd2.services.SingerService;
import by.it_academy.jd2.services.api.ISingerService;
import by.it_academy.jd2.storage.database.factory.ArtistStorageDBFactory;


public class ServiceArtistFactory {
    private static final ISingerService instance = new SingerService(ArtistStorageDBFactory.getInstance());


    private ServiceArtistFactory(){}

    public static ISingerService getInstance() {
        return instance;
    }
}
