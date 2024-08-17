package by.it_academy.jd2.dto;

import java.util.Objects;

public class StyleDTO {

    private final Long id;
    private final String style;

    public StyleDTO(Long id, String title) {
        this.id = id;
        this.style = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StyleDTO styleDTO = (StyleDTO) o;
        return Objects.equals(style, styleDTO.style);
    }

    @Override
    public int hashCode() {
        return Objects.hash(style);
    }

    @Override
    public String toString() {
        return "StyleDTO{" +
                "style='" + style + '\'' +
                '}';
    }
}
