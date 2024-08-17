package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.StyleDTO;
import by.it_academy.jd2.storage.api.IStylesStorage;

import java.util.List;

public class StylesStorage implements IStylesStorage<StyleDTO> {
    private List<StyleDTO> STYLES = List.of(
            new StyleDTO(1L,"Hop"),
            new StyleDTO(2L,"Hip"),
            new StyleDTO(3L,"Rock"),
            new StyleDTO(4L,"Metal"),
            new StyleDTO(5L,"Jazz"),
            new StyleDTO(6L,"Folk"),
            new StyleDTO(7L,"DnB"),
            new StyleDTO(8L,"Pop"),
            new StyleDTO(9L,"Shanson"),
            new StyleDTO(10L,"HZ"));


    @Override
    public void create (StyleDTO styleDTO) {
        STYLES.add(styleDTO);
    }

    @Override
    public List<StyleDTO> readAll() {
        return STYLES;
    }

    @Override
    public void delete(StyleDTO styleDTO) {
        STYLES.remove(styleDTO);
    }

    @Override
    public boolean exist(Long id) {
        for (StyleDTO styleDTO : STYLES) {
            if(id.equals(styleDTO.getId())){
                return true;
            }
        }
        return false;
    }
}
