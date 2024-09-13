package by.it_academy.jd2.services.factory;

import by.it_academy.jd2.services.VoteDAO;

public class DAOVoteFactory {
    private static final VoteDAO instance = new VoteDAO();

    private DAOVoteFactory() {}

    public static VoteDAO getInstance() {
        return instance;
    }
}