package com.company;

import java.util.ArrayList;

public interface IRepository<T> {
    public void GetAll();

    public T GetById(int id);

    public void Add(T item);
}
