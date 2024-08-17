package by.it_academy.jd2.dto;

import java.util.List;
import java.util.Objects;

public class VoteDTO {
    private final Long voiceForSinger;
    private final List<Long> voicesForStyles;
    private final String message;

    public VoteDTO(Long voiceForSinger, List<Long> voicesForStyles, String message) {
        this.voiceForSinger = voiceForSinger;
        this.voicesForStyles = voicesForStyles;
        this.message = message;
    }

    public Long getVoiceForSinger() {
        return voiceForSinger;
    }

    public List<Long> getVoicesForStyles() {
        return voicesForStyles;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteDTO voteDTO = (VoteDTO) o;
        return Objects.equals(voiceForSinger, voteDTO.voiceForSinger) && Objects.equals(voicesForStyles, voteDTO.voicesForStyles) && Objects.equals(message, voteDTO.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voiceForSinger, voicesForStyles, message);
    }

    @Override
    public String toString() {
        return "VoteDto{" +
                "voiceForSinger=" + voiceForSinger +
                ", voicesForStyles=" + voicesForStyles +
                ", message='" + message + '\'' +
                '}';
    }
}
