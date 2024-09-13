package by.it_academy.jd2.dto.dbresult;

public class GenreVoteCount {
    private final String genreName;
    private final int voteCount;

    public GenreVoteCount(String genreName, int voteCount) {
        this.genreName = genreName;
        this.voteCount = voteCount;
    }

    public String getGenreName() {
        return genreName;
    }

    public int getVoteCount() {
        return voteCount;
    }
}
