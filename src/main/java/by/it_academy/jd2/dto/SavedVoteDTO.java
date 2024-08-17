package by.it_academy.jd2.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class SavedVoteDTO {

    private LocalDateTime time;
    private final VoteDTO vote;


    public SavedVoteDTO(VoteDTO vote) {
        this.time = LocalDateTime.now();
        this.vote = vote;
    }

    public LocalDateTime getCreateDateTime() {
        return time;
    }

    public VoteDTO getVote() {
        return vote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedVoteDTO that = (SavedVoteDTO) o;
        return Objects.equals(time, that.time) && Objects.equals(vote, that.vote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, vote);
    }

    @Override
    public String toString() {
        return "SavedVoteDTO{" +
                "createDateTime=" + time +
                ", vote=" + vote +
                '}';
    }
}
