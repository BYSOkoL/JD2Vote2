package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.SavedVoteDTO;
import by.it_academy.jd2.storage.api.IVoteStorage;

import java.util.ArrayList;
import java.util.List;

public class VoteStorage implements IVoteStorage<SavedVoteDTO> {

    private final List<SavedVoteDTO> savedVoteDTOS = new ArrayList<>();

    @Override
    public void create(SavedVoteDTO savedVoteDTO) {
        savedVoteDTOS.add(savedVoteDTO);
    }
    @Override
    public List<SavedVoteDTO> readAll() {
        return savedVoteDTOS;
    }

    @Override
    public void delete(SavedVoteDTO savedVoteDTO) {
        savedVoteDTOS.remove(savedVoteDTO);
    }


}
