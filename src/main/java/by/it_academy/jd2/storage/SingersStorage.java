package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.SingerDTO;
import by.it_academy.jd2.storage.api.ISingersStorage;

import java.util.List;

public class SingersStorage implements ISingersStorage<SingerDTO> {
    private static List<SingerDTO> SINGERS = List.of(
            new SingerDTO(1L,"Eminem"),
            new SingerDTO(2L,"2Pac"),
            new SingerDTO(3L,"Sabaton"),
            new SingerDTO(4L,"S2"));

    @Override
    public void create(SingerDTO singerDTO) {
        SINGERS.add(singerDTO);
    }

    @Override
    public List<SingerDTO> readAll() {
        return SINGERS;
    }

    @Override
    public void delete(SingerDTO singerDTO) {
        SINGERS.remove(singerDTO);
    }

    @Override
    public boolean exist(Long id) {
        for (SingerDTO singerDTO : SINGERS) {
            if(id.equals(singerDTO.getId())){
                return true;
            }
        }
        return false;
    }
}