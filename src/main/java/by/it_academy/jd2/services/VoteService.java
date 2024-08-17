package by.it_academy.jd2.services;

import by.it_academy.jd2.dto.SavedVoteDTO;
import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.services.api.ISingerService;
import by.it_academy.jd2.services.api.IStyleService;
import by.it_academy.jd2.services.api.IVoteService;
import by.it_academy.jd2.storage.api.IVoteStorage;

import java.util.*;

public class VoteService implements IVoteService {
    public static final String SINGER_DO_NOT_EXIST = "Singer doesn't exist";
    public static final String STYLE_DO_NOT_EXIST = "Style doesn't exist";
    public static final String STYLES_LIST_IS_EMPTY = "Style list is empty";
    private final String SINGER_IS_EMPTY = "SingerDTO is empty";
    private final String STYLE_IS_EMPTY = "StyleDTO is empty";
    private final String MESSAGE_IS_EMPTY = "Message is empty";
    private final String WRONG_NUMBER_OF_STYLES = "Wrong number of styles";
    private final int MIN_STYLE_CHOICES = 3;
    private final int MAX_STYLE_CHOICES = 5;

    private final IVoteStorage<SavedVoteDTO> voteStorage;
    private final ISingerService singerService;
    private final IStyleService styleService;


    public VoteService(IVoteStorage<SavedVoteDTO> voteStorage, ISingerService singerService, IStyleService styleService) {
        this.voteStorage = voteStorage;
        this.singerService = singerService;
        this.styleService = styleService;
    }

    public void addVote(VoteDTO voteDTO) {
        validate(voteDTO);
        SavedVoteDTO savedVoteDTO = new SavedVoteDTO(voteDTO);
        voteStorage.create(savedVoteDTO);
    }

    private void validate (VoteDTO voteDTO) {
        List<String> errors = new ArrayList<>();
        int votesForStyleListSize = voteDTO.getVoicesForStyles().size();
        Long voiceForSinger = voteDTO.getVoiceForSinger();
        List<Long> voicesForStyles = voteDTO.getVoicesForStyles();
        String message = voteDTO.getMessage();

        if (voiceForSinger == null) {
            errors.add(SINGER_IS_EMPTY);
        }
        if (voicesForStyles.isEmpty()) {
            errors.add(STYLES_LIST_IS_EMPTY);
        }
        if (message.isEmpty()) {
            errors.add(MESSAGE_IS_EMPTY);
        }
        if (votesForStyleListSize < MIN_STYLE_CHOICES || votesForStyleListSize > MAX_STYLE_CHOICES) {
            errors.add(WRONG_NUMBER_OF_STYLES);
        }

        if(!this.singerService.exist(voiceForSinger)){
            errors.add(SINGER_DO_NOT_EXIST);
        }

        for (Long style : voicesForStyles) {
            if(style == null){
                errors.add(STYLE_IS_EMPTY);
            }
            if(!this.styleService.exist(style)){
                errors.add(STYLE_DO_NOT_EXIST);
            }
        }
    }

}
