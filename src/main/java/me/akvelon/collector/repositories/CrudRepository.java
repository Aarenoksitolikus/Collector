package me.akvelon.collector.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);

    T save(T entity);
    void update(T entity);
    void delete(T entity);
    void deleteById(ID id);
}
