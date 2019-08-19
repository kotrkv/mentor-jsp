package com.kotrkv.mentor.jsp.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
//        configuration.addAnnotatedClass(Car.class);
//        configuration.addAnnotatedClass(DailyReport.class);
//        configuration.addAnnotatedClass(SaleCar.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example");
        configuration.setProperty("hibernate.connection.username", "admin");
        configuration.setProperty("hibernate.connection.password", "postgres");
        //configuration.setProperty("hibernate.show_sql", "true");
        //configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
