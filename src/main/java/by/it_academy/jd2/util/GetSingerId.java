package by.it_academy.jd2.util;

public class GetSingerId {
    public static Long getId(String singer) {
        if (singer.equals("Eminem")){
            return 1L;
        }
        if (singer.equals("2Pac")){
            return 2L;
        }
        if (singer.equals("Sabaton")){
            return 3L;
        }
        if (singer.equals("Metallica")){
            return 4L;
        } else {
            return null;
        }
    }
}
