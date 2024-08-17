package by.it_academy.jd2.storage.api;

public interface ISingersStorage<T> extends IStorage<T> {
    boolean exist (Long id);
}
