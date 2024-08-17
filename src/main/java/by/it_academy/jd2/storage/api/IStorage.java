package by.it_academy.jd2.storage.api;

import java.util.List;

public interface IStorage<T> {
    void create(T t);

    List<T> readAll();

    void delete(T t);

}
