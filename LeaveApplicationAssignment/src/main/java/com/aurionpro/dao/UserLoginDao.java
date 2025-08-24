package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.aurionpro.database.Database;
import com.aurionpro.model.UserLogin;

public class UserLoginDao {
	
	 public boolean validateUser(UserLogin user) {
	        String sql = "SELECT 1 FROM UserLogin WHERE username = ? AND password = ? AND role = ?";

	        try (Connection conn = Database.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setString(1, user.getUsername());
	            ps.setString(2, user.getPassword());
	            ps.setString(3, user.getRole());
	            

	            try (ResultSet rs = ps.executeQuery()) {
	                return rs.next(); 
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

}
