package com.kotrkv.mentor.jsp.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, U> {

    void create(T t);
    Optional<T> getById(U u);
    List<T> getAll();
    void delete(U u);
    void update(T t);
}
