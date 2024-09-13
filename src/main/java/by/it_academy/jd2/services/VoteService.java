package by.it_academy.jd2.services;

import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.entity.Vote;
import by.it_academy.jd2.services.api.IVoteService;
import by.it_academy.jd2.storage.api.IStorage;
import by.it_academy.jd2.util.Validate;
import by.it_academy.jd2.util.VoteToEnt;


public class VoteService implements IVoteService {
    private final Validate validate;
    private final VoteToEnt voteToEntity;
    private final IStorage<Vote> voteStorage;

    public VoteService(IStorage<Vote> voteStorage,
                       Validate validate,
                       VoteToEnt voteToEntity
    ){
        this.voteStorage = voteStorage;
        this.validate = validate;
        this.voteToEntity = voteToEntity;
    }
    @Override
    public void addVote(VoteDTO voteDTO) {
        Boolean valid = validate.valid(voteDTO);
        if(!valid) {
            throw new IllegalArgumentException("Не прошли валидацию");
        }
        Vote vote = voteToEntity.toEntity(voteDTO);
        voteStorage.create(vote);
    }
}
