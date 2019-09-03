package com.kotrkv.mentor.jsp.dao;

import com.kotrkv.mentor.jsp.dao.impls.UserDaoHibernate;

import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {

    public UserDao createUserDao() {
        String type = loadTypeDao();
        return type.equalsIgnoreCase("hibernate") ? UserDaoHibernate.getInstance() : null;
    }

    private String loadTypeDao() {
        try {
            Properties properties = new Properties();
            InputStream in = this.getClass().getResourceAsStream("/db.properties");
            properties.load(in);
            return properties.getProperty("userdao");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
