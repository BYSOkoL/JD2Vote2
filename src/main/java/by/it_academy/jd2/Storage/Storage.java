package by.it_academy.jd2.Storage;

import by.it_academy.jd2.Storage.api.IStorage;
import by.it_academy.jd2.dto.VoteDTO;

import java.util.*;

public class Storage implements IStorage {

    private static final Storage instance = new Storage();
    private static final Map<String, Integer> singerMap = new HashMap<>();
    private static final Map<String, Integer> styleMap = new HashMap<>();
    private static final List<String> textCommentList = new ArrayList<>();

    public Storage() {
    }

    public static Storage getInstance() {
        return instance;
    }

    public static Map<String, Integer> getSingerMap() {
        return singerMap;
    }

    public static Map<String, Integer> getStyleMap() {
        return styleMap;
    }

    public static List<String> getTextCommentList() {
        return textCommentList;
    }
}
