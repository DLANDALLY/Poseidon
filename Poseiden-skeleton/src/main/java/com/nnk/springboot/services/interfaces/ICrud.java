package com.nnk.springboot.services.interfaces;

import java.util.List;

public interface ICrud<T, ID> {
    List<T> getAll();

    T saving(T obj);

    void update(ID id, T obj);

    T getById(ID id);

    void deleteById(ID id);
}
