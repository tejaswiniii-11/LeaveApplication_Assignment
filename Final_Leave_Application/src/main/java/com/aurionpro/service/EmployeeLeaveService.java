package com.aurionpro.service;

import com.aurionpro.dao.EmployeeLeaveDao;
import com.aurionpro.model.EmployeeLeave;
import com.aurionpro.model.Leave;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public class EmployeeLeaveService {
    private EmployeeLeaveDao leaveDao;

    public EmployeeLeaveService(Connection connection) {
        this.leaveDao = new EmployeeLeaveDao(connection);
    }

    public boolean applyLeave(int userId, int leaveTypeId, Date startDate, Date endDate) {
        // Constraint check: start date >= today
        if (startDate.before(new Date(System.currentTimeMillis()))) {
            return false;
        }
        if (endDate.before(startDate)) {
            return false;
        }
        return leaveDao.applyLeave(userId, leaveTypeId, startDate, endDate);
    }

    public List<EmployeeLeave> getLeavesByEmployee(int employeeId) {
        return leaveDao.getLeavesByEmployeeId(employeeId);
    }

    public Map<String, Integer> getRemainingLeaves(int userId) {
        return leaveDao.getRemainingLeaves(userId);
    }
}
