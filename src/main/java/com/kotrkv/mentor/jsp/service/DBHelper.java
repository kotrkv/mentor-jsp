package com.kotrkv.mentor.jsp.service;

import org.hibernate.SessionFactory;

import java.sql.Connection;

public class DBHelper {

    private static final DBHelper INSTANCE = new DBHelper();

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        return INSTANCE;
    }

    public Connection createConnection() {
        return DBServiceJDBC.createConnection();
    }

    public SessionFactory createSessionFactory() {
        return DBServiceHibernate.getSessionFactory();
    }
}
