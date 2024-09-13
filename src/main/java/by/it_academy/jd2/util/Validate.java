package by.it_academy.jd2.util;

import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.services.api.ISingerService;
import by.it_academy.jd2.services.api.IStyleService;
import by.it_academy.jd2.services.factory.ServiceArtistFactory;
import by.it_academy.jd2.services.factory.ServiceGenreFactory;

public class Validate {
    private final ISingerService singerService;
    private final IStyleService styleService;

    private static final int MIN_COUNT_GENRES = 3;

    public Validate(ISingerService singerService, IStyleService styleService) {
        this.singerService = singerService;
        this.styleService = styleService;
    }


    public boolean valid(VoteDTO voteDTO) {
        if (voteDTO.getSinger() == null) {
            throw new IllegalArgumentException("Artist not selected.");
        } else if (singerService.get(Long.valueOf(voteDTO.getSinger())) == null) {
            throw new IllegalArgumentException("Artist does not exist.");
        }
        String[] genres = voteDTO.getStyles();
        if (genres == null || genres.length < MIN_COUNT_GENRES) {
            throw new IllegalArgumentException("At least " + MIN_COUNT_GENRES + " genres must be selected.");
        } else {
            for (String genr : genres) {
                if (styleService.get(Long.valueOf(genr)) == null) {
                    throw new IllegalArgumentException("Genre does not exist.");
                }
            }
        }
        if (voteDTO.getMessage().isBlank()) {
            throw new IllegalArgumentException("Message cannot be blank.");
        }
        return true;
    }

    private static final Validate instance = new Validate(
            ServiceArtistFactory.getInstance(), ServiceGenreFactory.getInstance()
    );

    public static Validate getInstance() {
        return instance;
    }

}
