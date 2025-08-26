package com.aurionpro.model;

import java.util.Date;

public class EmployeeLeave {
    private int leaveId;
    private int userId;
    private int leaveTypeId;
    private String leaveTypeName;  
    private Date startDate;
    private Date endDate;
    private String status;
    private Date appliedOn;

    // Getters and Setters
    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(int leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getLeaveTypeName() {
        return leaveTypeName;
    }

    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(Date appliedOn) {
        this.appliedOn = appliedOn;
    }

	public EmployeeLeave(int leaveId, int userId, int leaveTypeId, String leaveTypeName, Date startDate, Date endDate,
			String status, Date appliedOn) {
		super();
		this.leaveId = leaveId;
		this.userId = userId;
		this.leaveTypeId = leaveTypeId;
		this.leaveTypeName = leaveTypeName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.appliedOn = appliedOn;
	}
    
    
}

