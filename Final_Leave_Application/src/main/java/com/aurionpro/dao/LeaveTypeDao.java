package com.aurionpro.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveTypeDao {
    private Connection connection;

    public LeaveTypeDao(Connection connection) {
        this.connection = connection;
    }

    // Get all leave types
    public List<String> getLeaveTypes() {
        List<String> leaveTypes = new ArrayList<>();
        String sql = "SELECT type_name FROM leave_types";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                leaveTypes.add(rs.getString("type_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaveTypes;
    }

    // Get leaveTypeId by name
    public int getLeaveTypeIdByName(String typeName) {
        String sql = "SELECT leave_type_id FROM leave_types WHERE type_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, typeName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("leave_type_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
