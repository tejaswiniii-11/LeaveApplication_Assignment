package com.aurionpro.model;

import java.time.LocalDate;
import java.sql.Timestamp;

public class AdminLeaveDTO {

	private int leaveId;
	private int userId;
	private String username; // Employee name
	private int leaveTypeId;
	private String leaveTypeName; // Leave type name
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
	private Timestamp appliedOn;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
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
