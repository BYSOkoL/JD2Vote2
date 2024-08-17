package by.it_academy.jd2.storage.api;

public interface IStylesStorage<T> extends IStorage<T> {
    boolean exist(Long id);
}
