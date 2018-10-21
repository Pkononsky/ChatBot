package com.company;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GenericRepository<T> implements IRepository<T> {
    private Class<T> cls;

    public GenericRepository(Class<T> cls) {
        this.cls = cls;
    }

    @Override
    public List<T> getAll() {
        Session session = HibernateUtil.getInstance().getSession();
        return (List<T>) session.createQuery("FROM " + cls.getSimpleName()).list();
    }

    public long getCount(){
        Session session = HibernateUtil.getInstance().getSession();
        return (long) session.createQuery("SELECT COUNT(*) FROM " + cls.getSimpleName()).list().get(0);
    }

    public T getRandom(){
        Session session = HibernateUtil.getInstance().getSession();
        return (T) session.createQuery("FROM " +cls.getSimpleName() +" ORDER BY RANDOM()").list().get(0);
    }

    @Override
    public T getById(long id) {
        Session session = HibernateUtil.getInstance().getSession();
        return session.get(cls, id);
    }

    @Override
    public void add(T item) {
        Session session = HibernateUtil.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.persist(item);
        tx.commit();
    }

    @Override
    public void remove(T item) {
        Session session = HibernateUtil.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.remove(item);
        tx.commit();
    }

    @Override
    public void update(T item) {
        Session session = HibernateUtil.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.update(item);
        tx.commit();
    }
}