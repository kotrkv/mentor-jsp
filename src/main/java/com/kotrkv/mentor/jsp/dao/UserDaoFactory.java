package com.kotrkv.mentor.jsp.dao;

import com.kotrkv.mentor.jsp.dao.impls.UserDaoHibernate;
import com.kotrkv.mentor.jsp.dao.impls.UserDaoJdbc;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

public class UserDaoFactory {

    public UserDao createUserDao() {
        try {
            String type = loadTypeDao();
            return type.equalsIgnoreCase("jdbc") ? UserDaoJdbc.getInstance() : UserDaoHibernate.getInstance();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String loadTypeDao() throws URISyntaxException {
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
