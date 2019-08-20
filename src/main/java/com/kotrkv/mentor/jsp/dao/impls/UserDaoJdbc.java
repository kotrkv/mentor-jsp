package com.kotrkv.mentor.jsp.dao.impls;

import com.kotrkv.mentor.jsp.dao.UserDao;
import com.kotrkv.mentor.jsp.service.DBServiceJDBC;
import com.kotrkv.mentor.jsp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbc implements UserDao {

    private Connection connection;

    //language=sql
    private final String SQL_INSERT =
            "INSERT INTO jsp_project.users (login, password, email) VALUES(?, ?, ?)";

    private final String SQL_SELECT_ALL =
            "SELECT * FROM jsp_project.users";

    private final String SQL_FIND_BY_ID =
            "SELECT * FROM jsp_project.users WHERE id = ?";

    private final String SQL_UPDATE =
            "UPDATE jsp_project.users SET login = ?, password = ?, email = ? WHERE id = ?";

    private final String SQL_DELETE =
            "DELETE FROM jsp_project.users WHERE id = ?";

    private static final UserDaoJdbc INSTANCE = new UserDaoJdbc();

    private UserDaoJdbc() {
//        try {
//            String driver = "jdbc:postgresql://localhost:5432/mentor";
//            String user = "admin";
//            String password = "postgres";
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(driver, user, password);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
//    private UserDao() {
//        Properties properties = new Properties();
//        try {
//            properties.load(new FileInputStream("resources/db.properties"));
//
//            String driver = properties.getProperty("db.url");
//            String user = properties.getProperty("db.username");
//            String password = properties.getProperty("db.password");
//            Class.forName(properties.getProperty("db.driver"));
//            connection = DriverManager.getConnection(driver, user, password);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
    public static UserDaoJdbc getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(User user) {
        try (PreparedStatement preparedStatement = DBServiceJDBC.createConnection().prepareStatement(SQL_INSERT)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getById(Integer id) {
        try (PreparedStatement preparedStatement = DBServiceJDBC.createConnection().prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");

                User user = new User(id, login, password, email);

                Optional<User> optionalUser = Optional.of(user);
                return optionalUser;
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        try (Statement statement = DBServiceJDBC.createConnection().createStatement()){

            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");

                User user = new User(id, login, password, email);

                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = DBServiceJDBC.createConnection().prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement preparedStatement = DBServiceJDBC.createConnection().prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
