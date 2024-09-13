package by.it_academy.jd2.dto;

import java.time.LocalDateTime;
import java.util.Arrays;

public class VoteDTO {
    private String singer;
    private String[] styles;
    private String message;
    private LocalDateTime dateTime;

    public VoteDTO(String singer, String[] styles, String message, LocalDateTime dateTime) {
        this.singer = singer;
        this.styles = styles;
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getSinger() {
        return singer;
    }

    public String[] getStyles() {
        return styles;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public static BuilderVoteDTO builder() {
        return new BuilderVoteDTO();
    }

    public static class BuilderVoteDTO {
        private String singer;
        private String[] styles;
        private String message;
        private LocalDateTime dateTime;

        public BuilderVoteDTO setStyles(String[] janres) {
            this.styles = janres;
            return this;
        }

        public BuilderVoteDTO setSinger(String singer) {
            this.singer = singer;
            return this;
        }

        public BuilderVoteDTO setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public BuilderVoteDTO setMessage(String message) {
            this.message = message;
            return this;
        }

        public VoteDTO build() {
            return new VoteDTO(singer, styles,message, dateTime);
        }
    }

    @Override
    public String toString() {
        return "InfoFromUser{" +
                "janres=" + Arrays.toString(styles) +
                ", singer=" + singer +
                ", date='" + dateTime + '\'' +
                '}';
    }
}
