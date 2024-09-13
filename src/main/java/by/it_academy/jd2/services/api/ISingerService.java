package by.it_academy.jd2.services.api;

import java.util.List;
import java.util.Map;

public interface ISingerService <T, t> {
    Long create(String name);
    String get(Long id);
    Map<T, t> getAll();
    boolean delete(Long id);
}
