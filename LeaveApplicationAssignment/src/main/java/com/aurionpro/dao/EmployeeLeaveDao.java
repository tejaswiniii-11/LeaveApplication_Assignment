package com.aurionpro.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.EmployeeLeave;
import com.aurionpro.database.Database;  

public class EmployeeLeaveDao {

    //Employee Part
    public boolean applyLeave(EmployeeLeave leave) {
        String sql = "INSERT INTO EmployeeLeave (employeeid, employeename, total_leave_days, leave_from, leave_to, leave_reason, leave_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, leave.getEmpId());
            ps.setString(2, leave.getEmpName());
            ps.setInt(3, leave.getTotalLeaves());
            ps.setDate(4, Date.valueOf(leave.getLeaveFrom()));  
            ps.setDate(5, Date.valueOf(leave.getTillLeave()));
            ps.setString(6, leave.getReason());
            ps.setString(7, leave.getStatus());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

   
    public List<EmployeeLeave> getLeavesByEmployeeId(String employeeId) {
        String sql = "SELECT * FROM EmployeeLeave WHERE employeeid=?";
        List<EmployeeLeave> leaveList = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    EmployeeLeave leave = new EmployeeLeave();
                    leave.setEmpId(rs.getString("employeeid"));
                    leave.setEmpName(rs.getString("employeename"));
                    leave.setTotalLeaves(rs.getInt("total_leave_days"));
                    leave.setLeaveFrom(rs.getDate("leave_from").toString());
                    leave.setTillLeave(rs.getDate("leave_to").toString());
                    leave.setReason(rs.getString("leave_reason"));
                    leave.setStatus(rs.getString("leave_status"));
                    leaveList.add(leave);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaveList;
    }
    
    //Admin Part
 // Fetch all leave records
    public List<EmployeeLeave> getAllLeaves() {
        List<EmployeeLeave> leaveList = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM EmployeeLeave ORDER BY leave_from DESC");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EmployeeLeave leave = new EmployeeLeave();
                leave.setEmpId(rs.getString("employeeid"));
                leave.setEmpName(rs.getString("employeename"));
                leave.setTotalLeaves(rs.getInt("total_leave_days"));
                leave.setLeaveFrom(rs.getDate("leave_from").toString());
                leave.setTillLeave(rs.getDate("leave_to").toString());
                leave.setReason(rs.getString("leave_reason"));
                leave.setStatus(rs.getString("leave_status"));
                leaveList.add(leave);
            }
            System.out.println("EmployeeLeaveDao.getAllLeaves() fetched " + leaveList.size() + " rows");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaveList;
    }


    // Update status for a specific leave application
    public boolean updateLeaveStatus(String empId, String status) {
        String sql = "UPDATE EmployeeLeave SET leave_status=? WHERE employeeid=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, empId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
