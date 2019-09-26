package com.kotrkv.mentor.jsp.dao;

import com.kotrkv.mentor.jsp.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    List<Role> getAll();

    Optional<Role> getByName(String name);

    Optional<Role> getById(Integer id);
}
