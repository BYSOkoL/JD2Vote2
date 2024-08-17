package by.it_academy.jd2.services;


import by.it_academy.jd2.dto.StyleDTO;
import by.it_academy.jd2.services.api.IStyleService;
import by.it_academy.jd2.storage.api.IStylesStorage;

import java.util.List;

public class StyleService implements IStyleService {
    private final IStylesStorage<StyleDTO> stylesStorage;

    public StyleService(IStylesStorage<StyleDTO> stylesStorage) {
        this.stylesStorage = stylesStorage;
    }

    @Override
    public List<StyleDTO>  getContent() {
        return stylesStorage.readAll();
    }

    @Override
    public boolean exist(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Style title is null");
        }
        return stylesStorage.exist(id);
    }
}
