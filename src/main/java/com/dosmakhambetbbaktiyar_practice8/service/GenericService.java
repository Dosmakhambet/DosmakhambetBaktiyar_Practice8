package com.dosmakhambetbbaktiyar_practice8.service;

import java.util.List;

public interface GenericService <T, ID>{
    T findById(ID id);

    List<T> findAll();

    T save(T t);

    void deleteById(ID id);
}
