package com.aurionpro.model;

public class EmployeeLeave {
	
	
	    private String empId;
	    private String empName;
	    private int totalLeaves;
	    private String leaveFrom;
	    private String tillLeave;
	    private String reason;
	    private String status;

	    // Default constructor
	    public EmployeeLeave() {
	    	super();
	    }

	    // Parameterized constructor
	    public EmployeeLeave(String empId, String empName, int totalLeaves, String leaveFrom, String tillLeave, String reason, String status) {
	        this.empId = empId;
	        this.empName = empName;
	        this.totalLeaves = totalLeaves;
	        this.leaveFrom = leaveFrom;
	        this.tillLeave = tillLeave;
	        this.reason = reason;
	        this.status = status;
	    }

	    // Getters and Setters

	    public String getEmpId() {
	        return empId;
	    }

	    public void setEmpId(String empId) {
	        this.empId = empId;
	    }

	    public String getEmpName() {
	        return empName;
	    }

	    public void setEmpName(String empName) {
	        this.empName = empName;
	    }

	    public int getTotalLeaves() {
	        return totalLeaves;
	    }

	    public void setTotalLeaves(int totalLeaves) {
	        this.totalLeaves = totalLeaves;
	    }

	    public String getLeaveFrom() {
	        return leaveFrom;
	    }

	    public void setLeaveFrom(String leaveFrom) {
	        this.leaveFrom = leaveFrom;
	    }

	    public String getTillLeave() {
	        return tillLeave;
	    }

	    public void setTillLeave(String tillLeave) {
	        this.tillLeave = tillLeave;
	    }

	    public String getReason() {
	        return reason;
	    }

	    public void setReason(String reason) {
	        this.reason = reason;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }
	


}
