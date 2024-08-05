package by.it_academy.jd2.dto;

import java.util.Arrays;
import java.util.Objects;

//DTO - Data Transfer Object
public class VoteDTO {
    private String singer;
    private String[] style;
    private String message;

    public VoteDTO() {
    }

    public VoteDTO(String singer, String[] style, String message) {
        this.singer = singer;
        this.style = style;
        this.message = message;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String artist) {
        this.singer = singer;
    }

    public String[] getStyle() {
        return style;
    }

    public void setStyle(String[] genre) {
        this.style = style;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteDTO voteDTO = (VoteDTO) o;
        return Objects.equals(singer, voteDTO.singer) && Arrays.equals(style, voteDTO.style) && Objects.equals(message, voteDTO.message);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(singer, message);
        result = 31 * result + Arrays.hashCode(style);
        return result;
    }

    @Override
    public String toString() {
        return "VoteDTO{" +
                "artist='" + singer + '\'' +
                ", genre=" + Arrays.toString(style) +
                ", about='" + message + '\'' +
                '}';
    }
}
