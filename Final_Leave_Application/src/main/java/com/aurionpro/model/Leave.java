package com.aurionpro.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Leave {
    private int leaveId;
    private int userId;
    private String leaveType;
    private Date startDate;
    private Date endDate;
    private String status;
    private Timestamp appliedOn;

    public Leave(int leaveId, int userId, String leaveType, Date startDate, Date endDate, String status, Timestamp appliedOn) {
        this.leaveId = leaveId;
        this.userId = userId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.appliedOn = appliedOn;
    }

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

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
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

	public Timestamp getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(Timestamp appliedOn) {
		this.appliedOn = appliedOn;
	}

    
   
}
