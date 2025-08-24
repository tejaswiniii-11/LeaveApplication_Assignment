package com.aurionpro.service;

import com.aurionpro.dao.UserLoginDao;
import com.aurionpro.model.UserLogin;

public class UserLoginService {
	
	private UserLoginDao userDao;
	
	public UserLoginService() {
        this.userDao = new UserLoginDao();
    }
	
	public boolean loginUser(UserLogin user) {
		if (user.getUsername() == null || user.getPassword() == null) {
            System.out.println("Invalid user data");
           
            return false;
        }

        return userDao.validateUser(user);
	}

}
