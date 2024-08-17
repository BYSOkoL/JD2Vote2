package by.it_academy.jd2.services.api;

import by.it_academy.jd2.dto.SingerDTO;

import java.util.List;

public interface ISingerService {
    List<SingerDTO> getContent();
    boolean exist(Long id);
}
