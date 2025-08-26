package com.aurionpro.dao;

import com.aurionpro.model.EmployeeLeave;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeLeaveDao {

    private Connection connection;

    public EmployeeLeaveDao(Connection connection) {
        this.connection = connection;
    }

 /
    public boolean applyLeave(int userId, int leaveTypeId, Date startDate, Date endDate) {
        String sql =
            "INSERT INTO leaves (user_id, leave_type_id, start_date, end_date, status) " +
            "SELECT ?, ?, ?, ?, 'PENDING' " +
            "WHERE NOT EXISTS ( " +
            "    SELECT 1 FROM leaves " +
            "    WHERE user_id = ? " +
            "      AND YEAR(start_date) = YEAR(CURRENT_DATE) " +
            "    GROUP BY user_id, YEAR(start_date) " +
            "    HAVING SUM(DATEDIFF(end_date, start_date) + 1) >= 25 " +  // Constraint 1: Max 25 days/year
            ") " +
            "AND DATE_FORMAT(?, '%Y-%m-%d') >= DATE_FORMAT(CURRENT_DATE, '%Y-%m-%d') " + // Constraint 2: start_date not in past
            "AND DATE_FORMAT(?, '%Y-%m-%d') >= DATE_FORMAT(?, '%Y-%m-%d')";              // Constraint 3: end_date >= start_date

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, leaveTypeId);
            stmt.setDate(3, startDate);
            stmt.setDate(4, endDate);

            stmt.setInt(5, userId);
            stmt.setDate(6, startDate);
            stmt.setDate(7, endDate);
            stmt.setDate(8, startDate);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // Fetch leaves by user
    public List<EmployeeLeave> getLeavesByEmployeeId(int userId) {
        List<EmployeeLeave> leaves = new ArrayList<>();
        String sql = "SELECT l.leave_id, l.user_id, l.leave_type_id, " +
                     "lt.type_name, l.start_date, l.end_date, l.status, l.applied_on " +
                     "FROM leaves l " +
                     "JOIN leave_types lt ON l.leave_type_id = lt.leave_type_id " +
                     "WHERE l.user_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EmployeeLeave leave = new EmployeeLeave(
                        rs.getInt("leave_id"),
                        rs.getInt("user_id"),
                        rs.getInt("leave_type_id"),
                        rs.getString("type_name"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("status"),
                        rs.getTimestamp("applied_on")
                );
                leaves.add(leave);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaves;
    }

 // Get remaining leaves for each type
    public Map<String, Integer> getRemainingLeaves(int userId) {
        Map<String, Integer> remainingLeaves = new HashMap<>();

        String sql = "SELECT lt.type_name, " +
                     "       lt.total_allowed - COUNT(l.leave_id) AS remaining " +
                     "FROM leave_types lt " +
                     "LEFT JOIN leaves l ON lt.leave_type_id = l.leave_type_id " +
                     "     AND l.user_id = ? AND l.status = 'APPROVED' " +
                     "GROUP BY lt.type_name, lt.total_allowed";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String typeName = rs.getString("type_name");
                int remaining = rs.getInt("remaining");
                remainingLeaves.put(typeName, remaining);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return remainingLeaves;
    }

}

