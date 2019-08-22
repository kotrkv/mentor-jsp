package com.kotrkv.mentor.jsp.service;

import com.kotrkv.mentor.jsp.model.User;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public Configuration createConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/mentor");
        configuration.setProperty("hibernate.connection.username", "admin");
        configuration.setProperty("hibernate.connection.password", "postgres");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        return configuration;
    }
}
