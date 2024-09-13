package by.it_academy.jd2.services.factory.factory;


import by.it_academy.jd2.services.VoteService;
import by.it_academy.jd2.services.api.IVoteService;
import by.it_academy.jd2.storage.database.factory.VoteStorageDBFactory;
import by.it_academy.jd2.util.Validate;
import by.it_academy.jd2.util.VoteToEnt;


public class ServiceVoteFactory {
    private static final IVoteService instance = new VoteService(
            VoteStorageDBFactory.getInstance(),
            Validate validate,
            VoteToEnt voteToEntity);

    private ServiceVoteFactory() {
    }

    public static IVoteService getInstance() {
        return instance;
    }
}
