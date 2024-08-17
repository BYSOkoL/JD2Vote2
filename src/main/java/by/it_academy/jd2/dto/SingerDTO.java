package by.it_academy.jd2.dto;

import java.util.Objects;

public class SingerDTO {

    private final Long id;
    private final String name;

    public SingerDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingerDTO that = (SingerDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "SingerDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
