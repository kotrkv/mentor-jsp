package com.kotrkv.mentor.jsp.db;

import com.kotrkv.mentor.jsp.model.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RuntimeDB {

    private static final RuntimeDB instance = new RuntimeDB();

    private List<User> users;

    private RuntimeDB() {
        this.users = new ArrayList<>();
        users.add(new User("One", "passone", "one@gmail.com"));
        users.add(new User("Two", "passtwo", "two@gmail.com"));
    }

    public static RuntimeDB getInstance() {
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }
}
