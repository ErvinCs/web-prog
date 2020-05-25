package ubb.web.lab8.repository;


import ubb.web.lab8.model.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface IRepository<T extends BaseEntity> {
    void add(T entity);
    void delete(Long id);
    void update(T entity);
    Optional<T> getById(Long id);
    List<T> getAll();
}
