package by.it_academy.jd2.services.api;


import java.util.List;
import java.util.Map;

public interface IStyleService {
    Long create(String name);
    String get(Long id);
    Map<Long, String> getAll();
    boolean delete(Long id);
}
