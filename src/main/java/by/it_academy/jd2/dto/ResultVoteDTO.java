package by.it_academy.jd2.dto;

import java.util.Objects;

public class ResultVoteDTO {
    private String singerMaxKey;
    private String styleMaxKey;
    private String message;
    private Integer singerkey;
    private Integer stylekey;

    public ResultVoteDTO() {
    }

    public ResultVoteDTO(String singerMaxKey, Integer singerkey, String styleMaxKey, Integer stylekey, String message) {
        this.singerMaxKey = singerMaxKey;
        this.styleMaxKey = styleMaxKey;
        this.message = message;
        this.singerkey = singerkey;
        this.stylekey = stylekey;
    }

    public String getSingerMaxKey() {
        return singerMaxKey;
    }

    public String getStyleMaxKey() {
        return styleMaxKey;
    }

    public String getMessage() {
        return message;
    }

    public Integer getSingerkey() {
        return singerkey;
    }

    public Integer getStylekey() {
        return stylekey;
    }

    @Override
    public String toString() {
        return "ResultVoteDTO{" +
                "singer='" + singerMaxKey + '\'' +
                ", style='" + styleMaxKey + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultVoteDTO that = (ResultVoteDTO) o;
        return Objects.equals(singerMaxKey, that.singerMaxKey) && Objects.equals(styleMaxKey, that.styleMaxKey) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(singerMaxKey, styleMaxKey, message);
    }
}
