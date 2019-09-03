package com.kotrkv.mentor.jsp.service;

import org.hibernate.cfg.Configuration;

import java.sql.Connection;

public class DBHelper {

    private static final DBHelper INSTANCE = new DBHelper();

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        return INSTANCE;
    }

    public Configuration createConfiguration() {
        return DBServiceHibernate.getConfiguration();
    }
}
