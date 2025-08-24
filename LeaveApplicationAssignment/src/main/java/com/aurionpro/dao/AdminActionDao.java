package com.aurionpro.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.EmployeeLeave;

public class AdminActionDao {

    private final String jdbcURL = "jdbc:mysql://localhost:3306/your_db";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "your_password";

    private static final String SELECT_ALL_LEAVES = "SELECT * FROM employee_leave";
    private static final String SELECT_PENDING_LEAVES = "SELECT * FROM employee_leave WHERE status = 'Pending'";
    private static final String UPDATE_LEAVE_STATUS = "UPDATE employee_leave SET status = ? WHERE leave_id = ?";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // Fetch all leaves
    public List<EmployeeLeave> getAllLeaves() {
        List<EmployeeLeave> leaves = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_LEAVES);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EmployeeLeave leave = mapResultSetToLeave(rs);
                leaves.add(leave);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaves;
    }

    // Fetch pending leaves
    public List<EmployeeLeave> getPendingLeaves() {
        List<EmployeeLeave> leaves = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_PENDING_LEAVES);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EmployeeLeave leave = mapResultSetToLeave(rs);
                leaves.add(leave);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaves;
    }

    // Update leave status by leave_id
    public boolean updateLeaveStatus(int leaveId, String status) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_LEAVE_STATUS)) {
            ps.setString(1, status);
            ps.setInt(2, leaveId);
            int count = ps.executeUpdate();
            rowUpdated = count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    private EmployeeLeave mapResultSetToLeave(ResultSet rs) throws SQLException {
//        int leaveId = rs.getInt("leave_id");
        String empId = rs.getString("emp_id");
        String empName = rs.getString("emp_name");
        int totalLeaves = rs.getInt("total_leaves");
        String leaveFrom = rs.getString("leave_from");
        String tillLeave = rs.getString("till_leave");
        String reason = rs.getString("reason");
        String status = rs.getString("status");

        return new EmployeeLeave(empId, empName, totalLeaves, leaveFrom, tillLeave, reason, status);
    }
}
