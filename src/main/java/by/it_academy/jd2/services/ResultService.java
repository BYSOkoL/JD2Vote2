package by.it_academy.jd2.services;

import by.it_academy.jd2.dto.*;
import by.it_academy.jd2.services.api.IResultService;
import by.it_academy.jd2.storage.api.ISingersStorage;
import by.it_academy.jd2.storage.api.IStylesStorage;
import by.it_academy.jd2.storage.api.IVoteStorage;
import by.it_academy.jd2.storage.singletone.SingerStorageSingleton;
import by.it_academy.jd2.storage.singletone.StylesStorageSingleton;
import by.it_academy.jd2.storage.singletone.VoteStorageSingleton;

import java.util.LinkedHashMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Collection;

public class ResultService implements IResultService {

    private final IVoteStorage<SavedVoteDTO> voteStorage = VoteStorageSingleton.getInstance();
    private final ISingersStorage<SingerDTO> singersStorage = SingerStorageSingleton.getInstance();
    private final IStylesStorage<StyleDTO> stylesStorage = StylesStorageSingleton.getInstance();

    public VoteResultDTO getVoteResult() {
        List<SavedVoteDTO> voteDTOList = voteStorage.readAll();
        Map<String, Long> sortedSingerVotes = getSortedSingerVotes(voteDTOList);
        Map<String, Long> sortedStyleVotes = getSortedStyleVotes(voteDTOList);
        List<SavedVoteDTO> sortedVote = getSortedVote(voteDTOList);
        return new VoteResultDTO(sortedSingerVotes, sortedStyleVotes, sortedVote);
    }


    private Map<String, Long> getSortedSingerVotes(List<SavedVoteDTO> voteDTOList) {
        List<SingerDTO> singerDTOS = singersStorage.readAll();
        Map<Long, String> singerNamesMap = singerDTOS.stream()
                .collect(Collectors.toMap(SingerDTO::getId, SingerDTO::getName));

        Map<Long, Long> idVotesForSingers = voteDTOList.stream()
                .map(SavedVoteDTO::getVote)
                .map(VoteDTO::getVoiceForSinger)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> nameVotesForSingers = idVotesForSingers.entrySet().stream()
                .collect(Collectors.toMap(entry -> singerNamesMap.get(entry.getKey()), Map.Entry::getValue));


        return nameVotesForSingers
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }


    private Map<String, Long> getSortedStyleVotes(List<SavedVoteDTO> voteDTOList) {
        List<StyleDTO> genreDTOS = stylesStorage.readAll();
        Map<Long, String> stylesTitleMap = genreDTOS.stream()
                .collect(Collectors.toMap(StyleDTO::getId, StyleDTO::getTitle));

        Map<Long, Long> idVotesForStyles = voteDTOList.stream()
                .map(SavedVoteDTO::getVote)
                .map(VoteDTO::getVoicesForStyles)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> nameVotesForSingers = idVotesForStyles.entrySet().stream()
                .collect(Collectors.toMap(entry -> stylesTitleMap.get(entry.getKey()), Map.Entry::getValue));

        return nameVotesForSingers
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }

    public static class SavedVoteComparatorByTime implements Comparator<SavedVoteDTO> {
        public int compare(SavedVoteDTO o1, SavedVoteDTO o2) {
            return o2.getCreateDateTime().compareTo(o1.getCreateDateTime());
        }
    }

    private List<SavedVoteDTO> getSortedVote(List<SavedVoteDTO> voteDTOList) {
        voteDTOList.sort(new SavedVoteComparatorByTime());
        return voteDTOList;
    }

}
