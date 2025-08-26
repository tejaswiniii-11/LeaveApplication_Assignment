package com.aurionpro.service;

import com.aurionpro.dao.EmployeeLeaveDao;
import com.aurionpro.model.EmployeeLeave;

import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaveService {
    private EmployeeLeaveDao leaveDao;

    // Inject DAO with DB connection
    public LeaveService(Connection connection) {
        this.leaveDao = new EmployeeLeaveDao(connection);
    }

    // Apply leave with validation
    public boolean applyLeave(int empId, int leaveTypeId, Date startDate, Date endDate) {
        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
        return leaveDao.applyLeave(empId, leaveTypeId, startDate, endDate);
    }

    // View all leaves of a user
    public List<EmployeeLeave> getLeavesByEmployeeId(int employeeId) {
        return leaveDao.getLeavesByEmployeeId(employeeId);
    }

    public Map<String, Integer> getRemainingLeaves(int userId) {
        Map<String, Integer> remaining = new HashMap<>();
//        // Example logic (you will replace with DB query):
//        remaining.put("Casual Leave", 5);
//        remaining.put("Sick Leave", 7);
//        remaining.put("Earned Leave", 10);

        return remaining;
    }

}
