package by.it_academy.jd2.services;

import by.it_academy.jd2.Storage.Storage;
import by.it_academy.jd2.dto.ResultVoteDTO;
import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.services.api.IVoteService;

import java.util.*;

import static by.it_academy.jd2.Storage.Storage.*;

public class VoteService implements IVoteService {

    private final static VoteService instance = new VoteService();
    String singerMaxKey = null;
    Integer singerkey = null;
    Integer stylekey = null;
    String styleMaxKey = null;

    private VoteService() {
    }

    @Override
    public void create(VoteDTO vote) {
        Storage.getSingerMap().compute(vote.getSinger(), (k, v) -> (v == null) ? 1 : v + 1);
        for (String genreItem : vote.getStyle()) {
            Storage.getStyleMap().compute(genreItem, (k, v) -> (v == null) ? 1 : v + 1);
        }
        if (!Objects.equals(vote.getMessage(), (null))) {
            Storage.getTextCommentList().add(vote.getMessage());
        }
    }

    public ResultVoteDTO result() {
        for (String key : Storage.getSingerMap().keySet()) {
            if (singerMaxKey == null || Storage.getSingerMap().get(key) >= Storage.getSingerMap().get(singerMaxKey)) {
                singerMaxKey = String.valueOf(key);
                singerkey = Storage.getSingerMap().get(singerMaxKey);
            }
        }
        for (String key : Storage.getStyleMap().keySet()) {
            if (styleMaxKey == null || Storage.getStyleMap().get(key) >= Storage.getStyleMap().get(styleMaxKey)) {
                styleMaxKey = String.valueOf(key);
                stylekey = Storage.getStyleMap().get(styleMaxKey);
            }
        }
        String lastComment = Storage.getTextCommentList().getLast();

        return new ResultVoteDTO(singerMaxKey, singerkey, styleMaxKey, stylekey, lastComment);
    }

    public static VoteService getInstance() {
        return instance;
    }
}
