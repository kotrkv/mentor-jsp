package com.kotrkv.mentor.jsp.dao.impls;

import com.kotrkv.mentor.jsp.dao.Dao;
import com.kotrkv.mentor.jsp.model.User;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UserDao implements Dao<User, Integer> {

    private Connection connection;

    private final String SQL_SELECT_ALL =
            "SELECT * FROM jsp_project.users";

    private final String SQL_INSERT =
            "INSERT INTO jsp_project.users (login, password, email, birthday) VALUES(?, ?, ?, ?)";

    private static final UserDao INSTANCE = new UserDao();

    private UserDao() {
        try {
            String driver = "jdbc:postgresql://localhost:5432/mentor";
            String user = "admin";
            String password = "postgres";
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(driver, user, password);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
//            preparedStatement.setDate(4, user.getBirthday());
            preparedStatement.setDate(4, Date.valueOf(user.getBirthday()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        try {
            List<User> users = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                LocalDate birthday = LocalDate.parse(resultSet.getString("birthday"));


                User user = new User(id, login, password, email, birthday);

                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User user) {

    }
}
