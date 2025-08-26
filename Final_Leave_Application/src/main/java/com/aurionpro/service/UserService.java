package com.aurionpro.service;

import com.aurionpro.dao.UserDao;
import com.aurionpro.model.User;

public class UserService {

    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    // Validate login
    public User loginUser(String username, String password, String role) {
        if (username == null || password == null || role == null) {
            return null;
        }
        // add extra validations if needed
        return userDao.validateUser(username, password, role);
    }
}
