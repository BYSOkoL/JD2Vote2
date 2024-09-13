package by.it_academy.jd2.util.dbresult;

public class ArtistVoteCount {
    private final String artistName;
    private final int voteCount;

    public ArtistVoteCount(String artistName, int voteCount) {
        this.artistName = artistName;
        this.voteCount = voteCount;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getVoteCount() {
        return voteCount;
    }
}

