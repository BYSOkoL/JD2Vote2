package by.it_academy.jd2.services.api;

import by.it_academy.jd2.dto.StyleDTO;

import java.util.List;

public interface IStyleService {

    List<StyleDTO> getContent();
    boolean exist(Long id);
}
