package com.company;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GenericRepository<T> implements IRepository<T> {
    private Class<T> cls;

    public GenericRepository(Class<T> cls){
        this.cls = cls;
    }

    @Override
    public List<T> GetAll() {
        Session session = HibernateUtil.getInstance().getSession();
        return (List<T>) session.createQuery("FROM "+cls.getSimpleName()).list();
     }

    @Override
    public T GetById(long id) {
        Session session = HibernateUtil.getInstance().getSession();
        return session.get(cls, id);
    }

    @Override
    public void Add(T item) {
        Session session = HibernateUtil.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.persist(item);
        tx.commit();
    }

    @Override
    public void Remove(T item) {
        Session session = HibernateUtil.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.remove(item);
        tx.commit();
     }

    @Override
    public void Update(T item) {
        Session session = HibernateUtil.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.update(item);
        tx.commit();
    }
}
