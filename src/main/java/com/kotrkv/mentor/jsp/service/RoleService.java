package com.kotrkv.mentor.jsp.service;

import com.kotrkv.mentor.jsp.dao.RoleDao;
import com.kotrkv.mentor.jsp.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public RoleService() {

    }

    public List<Role> findAll() {
        return roleDao.getAll();
    }
}
