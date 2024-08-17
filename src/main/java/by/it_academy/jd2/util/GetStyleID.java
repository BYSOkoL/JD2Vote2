package by.it_academy.jd2.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetStyleID {
    public static List<Long> getStyleIDlist (String[] strings){
       List<Long> styleIdList =  new ArrayList<>();
        List<String> s = Arrays.asList(strings);

        for (String string : s) {
            if (string.equals("HipHop")){
                styleIdList.add(1L);
            }
            if (string.equals("Rock")){
                styleIdList.add(2L);
            }
            if (string.equals("Jazz")){
                styleIdList.add(3L);
            }
            if (string.equals("Funk")){
                styleIdList.add(4L);
            }
            if (string.equals("Metal")){
                styleIdList.add(5L);
            }
            if (string.equals("Disko")){
                styleIdList.add(6L);
            }
            if (string.equals("Pop")){
                styleIdList.add(7L);
            }
            if (string.equals("Rap")){
                styleIdList.add(8L);
            }
            if (string.equals("Punk")){
                styleIdList.add(9L);
            }
            if (string.equals("DnB")){
                styleIdList.add(10L);
            }
        }
        return styleIdList;
    }
}
