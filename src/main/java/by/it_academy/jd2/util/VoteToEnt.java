package by.it_academy.jd2.util;

import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.entity.Vote;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.stream.Collectors;

public class VoteToEnt {

    private static final VoteToEnt instance = new VoteToEnt();

    private VoteToEnt() {
    }

    public static VoteToEnt getInstance() {
        return instance;
    }

    public Vote toEntity(VoteDTO voteDTO) {
        return Vote.builder()
                .setArtist(Long.valueOf(voteDTO.getSinger()))
                .setGenres(Arrays.stream(voteDTO.getStyles()).map(Long::valueOf).collect(Collectors.toList()))
                .setInfo(voteDTO.getMessage())
                .createdAt(OffsetDateTime.of(voteDTO.getDateTime(), ZoneOffset.of("+03:00")))
                .build();
    }
}