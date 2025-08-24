package com.aurionpro.service;

import java.util.List;
import com.aurionpro.dao.EmployeeLeaveDao;
import com.aurionpro.model.EmployeeLeave;

public class EmployeeLeaveService {
    private final EmployeeLeaveDao leaveDao = new EmployeeLeaveDao();

    //Employee Part
    public boolean applyLeave(EmployeeLeave leave) {
        return leaveDao.applyLeave(leave);
    }


    public List<EmployeeLeave> getLeaveSummary(String empId) {
        return leaveDao.getLeavesByEmployeeId(empId);
    }
    
    //Admin Part
    public List<EmployeeLeave> getAllLeaves() {
        List<EmployeeLeave> leaves = leaveDao.getAllLeaves();
        System.out.println("EmployeeLeaveService.getAllLeaves() returned " + (leaves == null ? "null" : leaves.size()));
        return leaves;
    }


    // Update leave status
    public boolean updateLeaveStatus(String empId, String status) {
        return leaveDao.updateLeaveStatus(empId, status);
    }

}
