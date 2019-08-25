package com.kotrkv.mentor.jsp.dao.impls;

import com.kotrkv.mentor.jsp.dao.UserDao;
import com.kotrkv.mentor.jsp.model.User;
import com.kotrkv.mentor.jsp.service.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbc implements UserDao {

    //language=sql
    private final String SQL_INSERT =
            "INSERT INTO jsp_project.users (login, password, email) VALUES(?, ?, ?)";

    private final String SQL_SELECT_ALL =
            "SELECT * FROM jsp_project.users";

    private final String SQL_FIND_BY_ID =
            "SELECT * FROM jsp_project.users WHERE id = ?";

    private final String SQL_FIND_BY_LOGIN_AND_PASSWORD =
            "SELECT * FROM jsp_project.users WHERE login = ? AND password = ?";

    private final String SQL_UPDATE =
            "UPDATE jsp_project.users SET login = ?, password = ?, email = ? WHERE id = ?";

    private final String SQL_DELETE =
            "DELETE FROM jsp_project.users WHERE id = ?";

    private static final UserDaoJdbc INSTANCE = new UserDaoJdbc();

    private UserDaoJdbc() {
    }

    public static UserDaoJdbc getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(User user) {
        try (PreparedStatement preparedStatement = DBHelper.getInstance().createConnection().prepareStatement(SQL_INSERT)) {
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
        try (PreparedStatement preparedStatement = DBHelper.getInstance().createConnection().prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");

                User user = new User(id, login, password, email, role);

                Optional<User> optionalUser = Optional.of(user);
                return optionalUser;
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getByLoginAndPassword(String login, String password) {
        try (PreparedStatement preparedStatement = DBHelper.getInstance().createConnection().prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(1, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5));

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
        try (Statement statement = DBHelper.getInstance().createConnection().createStatement()) {

            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");

                User user = new User(id, login, password, email, role);

                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = DBHelper.getInstance().createConnection().prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement preparedStatement = DBHelper.getInstance().createConnection().prepareStatement(SQL_UPDATE)) {
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
