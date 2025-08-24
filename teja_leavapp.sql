create database LeaveApplication;

use LeaveApplication;

create table UserLogin(
username varchar(50),
password varchar(20),
role varchar(10)
);

create table EmployeeLeave(
employeeid integer primary key,
employeename varchar(50),
total_leave_days integer,
leave_from date,
leave_to date,
leave_reason varchar(100),
leave_status varchar(20)
);

insert into UserLogin values ("Tejaswini", "Teja123", "Employee");
insert into UserLogin values ("Admin", "Admin123", "Admin");

select * from UserLogin;

select * from EmployeeLeave;