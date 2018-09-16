package com.company;

import java.util.ArrayList;

public interface IRepository<T> {
    public ArrayList<T> GetAll();

    public T GetById(long id);

    public void Add(T item);

    public void Remove(T item);
}
