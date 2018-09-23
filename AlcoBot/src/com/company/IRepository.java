package com.company;

import java.util.List;

public interface IRepository<T> {
    public List<T> GetAll();
    public T GetById(long id);
    public void Add(T item);
    public void Remove(T item);
    public void Update(T item);
}
