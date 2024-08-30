package com.connectCare.connectCareApi.services;


import java.util.List;

public interface GenericService<T> {

    T create(T object);
    T getById(Integer id);
    List<T> getAll();
    T update(T object);
    void delete(Integer id);

}
