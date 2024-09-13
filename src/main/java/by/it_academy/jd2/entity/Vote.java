package by.it_academy.jd2.entity;

import java.time.OffsetDateTime;
import java.util.List;

public class Vote {
    private Long id;
    private List<Long> genresId;
    private final Long artistId;
    private final String info;
    private final OffsetDateTime createdAt;

    private Vote(List<Long> genresId, Long artistId, String info, OffsetDateTime createdAt, Long id ) {
        this.genresId = genresId;
        this.artistId = artistId;
        this.info = info;
        this.createdAt = createdAt;
        this.id = id;
    }

    public static VoteEntityBuilder builder() {
        return new VoteEntityBuilder();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGenresId(List<Long> genresId) {
        this.genresId = genresId;
    }

    public List<Long> getGenresId() {
        return genresId;
    }

    public Long getArtistId() {
        return artistId;
    }

    public String getInfo() {
        return info;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }

    public static class VoteEntityBuilder {
        private Long id;
        private List<Long> genresId;
        private Long artistId;
        private String info;
        private OffsetDateTime createdAt;

        private VoteEntityBuilder() {
        }

        public VoteEntityBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public VoteEntityBuilder setGenres(List<Long> genresId) {
            this.genresId = genresId;
            return this;
        }

        public VoteEntityBuilder setArtist(Long artistId) {
            this.artistId = artistId;
            return this;
        }

        public VoteEntityBuilder setInfo(String info) {
            this.info = info;
            return this;
        }

        public VoteEntityBuilder createdAt(OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Vote build() {
            return new Vote(genresId, artistId, info, createdAt, id);
        }
    }
}
