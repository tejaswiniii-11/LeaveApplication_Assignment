package com.aurionpro.service;

import com.aurionpro.dao.AdminLeaveDao;
import com.aurionpro.dao.EmployeeLeaveDao;
import com.aurionpro.model.AdminLeaveDTO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AdminLeaveService {

    private AdminLeaveDao adminLeaveDao;

    public AdminLeaveService(Connection connection) {
        this.adminLeaveDao = new AdminLeaveDao(connection);
    }


    public List<AdminLeaveDTO> viewAllLeaves() {
        try {
            return adminLeaveDao.getAllLeaves();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

 
    public List<AdminLeaveDTO> viewLeavesByStatus(String status) {
        try {
            return adminLeaveDao.getLeavesByStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

  
    public boolean approveLeave(int leaveId) {
        try {
            return adminLeaveDao.updateLeaveStatus(leaveId, "APPROVED");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean rejectLeave(int leaveId) {
        try {
            return adminLeaveDao.updateLeaveStatus(leaveId, "REJECTED");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

