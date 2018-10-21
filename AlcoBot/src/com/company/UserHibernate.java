package com.company;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class UserHibernate {
    private Configuration configuration;
    private static volatile UserHibernate instance;
    private SessionFactory factory;

    private UserHibernate() {
        this.configuration = new Configuration();
        this.configuration.configure("META-INF/userHibernate.cfg.xml");
        this.configuration.addAnnotatedClass(User.class);
        this.factory = configuration.buildSessionFactory();
    }

    public static UserHibernate getInstance() {
        if (instance == null) {
            synchronized (UserHibernate.class) {
                instance = new UserHibernate();
            }
        }
        return instance;
    }

    public Session getSession() {
        return factory.openSession();
    }
}


