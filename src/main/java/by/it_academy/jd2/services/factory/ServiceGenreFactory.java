package by.it_academy.jd2.services.factory.factory;

import by.it_academy.jd2.services.StyleService;
import by.it_academy.jd2.services.api.IStyleService;
import by.it_academy.jd2.storage.database.factory.GenreStorageDBFactory;


public class ServiceGenreFactory {
    private static final IStyleService instance = new StyleService(GenreStorageDBFactory.getInstance());

    private ServiceGenreFactory() {
    }

    public static IStyleService getInstance() {
        return instance;
    }
}
