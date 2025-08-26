package com.aurionpro.dao;

import com.aurionpro.model.AdminLeaveDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminLeaveDao {

	private Connection connection;

    public AdminLeaveDao(Connection connection) {
        this.connection = connection;
    }


    // 1️⃣ Fetch all leaves with username and leave type name
    public List<AdminLeaveDTO> getAllLeaves() {
        List<AdminLeaveDTO> leaves = new ArrayList<>();
        String query = "SELECT l.leave_id, l.user_id, u.username, l.leave_type_id, lt.type_name, " +
                       "l.start_date, l.end_date, l.status, l.applied_on " +
                       "FROM leaves l " +
                       "JOIN users u ON l.user_id = u.user_id " +
                       "JOIN leave_types lt ON l.leave_type_id = lt.leave_type_id";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AdminLeaveDTO leave = new AdminLeaveDTO();
                leave.setLeaveId(rs.getInt("leave_id"));
                leave.setUserId(rs.getInt("user_id"));
                leave.setUsername(rs.getString("username"));
                leave.setLeaveTypeId(rs.getInt("leave_type_id"));
                leave.setLeaveTypeName(rs.getString("type_name"));
                leave.setStartDate(rs.getDate("start_date").toLocalDate());
                leave.setEndDate(rs.getDate("end_date").toLocalDate());
                leave.setStatus(rs.getString("status"));
                leave.setAppliedOn(rs.getTimestamp("applied_on"));
                leaves.add(leave);
            }
            System.out.println(leaves.size()); // Should be > 0

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaves;
    }

    // 2️⃣ Fetch leaves by status (PENDING, APPROVED, REJECTED)
    public List<AdminLeaveDTO> getLeavesByStatus(String status) {
        List<AdminLeaveDTO> leaves = new ArrayList<>();
        String query = "SELECT l.leave_id, l.user_id, u.username, l.leave_type_id, lt.type_name, " +
                       "l.start_date, l.end_date, l.status, l.applied_on " +
                       "FROM leaves l " +
                       "JOIN users u ON l.user_id = u.user_id " +
                       "JOIN leave_types lt ON l.leave_type_id = lt.leave_type_id " +
                       "WHERE l.status = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AdminLeaveDTO leave = new AdminLeaveDTO();
                    leave.setLeaveId(rs.getInt("leave_id"));
                    leave.setUserId(rs.getInt("user_id"));
                    leave.setUsername(rs.getString("username"));
                    leave.setLeaveTypeId(rs.getInt("leave_type_id"));
                    leave.setLeaveTypeName(rs.getString("type_name"));
                    leave.setStartDate(rs.getDate("start_date").toLocalDate());
                    leave.setEndDate(rs.getDate("end_date").toLocalDate());
                    leave.setStatus(rs.getString("status"));
                    leave.setAppliedOn(rs.getTimestamp("applied_on"));
                    leaves.add(leave);
                }
                System.out.println(leaves.size());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaves;
    }

    // 3️⃣ Update leave status (approve/reject)
    public boolean updateLeaveStatus(int leaveId, String status) {
        String query = "UPDATE leaves SET status = ? WHERE leave_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, leaveId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
