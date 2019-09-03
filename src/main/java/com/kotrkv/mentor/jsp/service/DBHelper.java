package com.kotrkv.mentor.jsp.service;

import org.hibernate.cfg.Configuration;

public class DBHelper {

    private static final DBHelper INSTANCE = new DBHelper();

    public DBHelper() {
    }

    public static DBHelper getInstance() {
        return INSTANCE;
    }

    public Configuration createConfiguration() {
        return DBServiceHibernate.getConfiguration();
    }
}
