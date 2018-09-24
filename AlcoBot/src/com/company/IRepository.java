package com.company;

import java.util.List;

public interface IRepository<T> {
    public List<T> getAll();
    public T getById(long id);
    public void add(T item);
    public void remove(T item);
    public void update(T item);
}
