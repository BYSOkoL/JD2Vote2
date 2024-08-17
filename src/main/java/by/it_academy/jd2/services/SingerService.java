package by.it_academy.jd2.services;

import by.it_academy.jd2.dto.SingerDTO;
import by.it_academy.jd2.services.api.ISingerService;
import by.it_academy.jd2.storage.api.ISingersStorage;

import java.util.List;

public class SingerService implements ISingerService {


    private final ISingersStorage<SingerDTO> singerStorage;

    public SingerService(ISingersStorage<SingerDTO> singerStorage) {
        this.singerStorage = singerStorage;
    }

    @Override
    public List<SingerDTO> getContent() {
        return singerStorage.readAll();
    }

    public boolean exist(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Singer is null");
        }
        return singerStorage.exist(id);
    }
}
