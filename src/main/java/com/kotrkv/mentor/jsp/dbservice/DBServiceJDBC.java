package com.kotrkv.mentor.jsp.dbservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBServiceJDBC {

    private DBServiceJDBC() {
    }

    public static Connection createConnection() {
        try {
            String driver = "jdbc:postgresql://localhost:5432/mentor";
            String user = "postgres";
            String password = "postgres";
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(driver, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            new RuntimeException(e);
        }
        return null;
    }
}
