CREATE DATABASE leave_management;
USE leave_management;


CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('ADMIN', 'EMPLOYEE') NOT NULL
);


CREATE TABLE leave_types (
    leave_type_id INT AUTO_INCREMENT PRIMARY KEY,
    type_name ENUM('CASUAL', 'PRIVILEGED', 'OCCASIONAL') NOT NULL UNIQUE,
    total_allowed INT NOT NULL
);


CREATE TABLE leaves (
    leave_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    leave_type_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status ENUM('PENDING','APPROVED','REJECTED') DEFAULT 'PENDING',
    applied_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (leave_type_id) REFERENCES leave_types(leave_type_id),
    CHECK (end_date >= start_date)
);


INSERT INTO leave_types (type_name, total_allowed) VALUES
('CASUAL', 8),
('PRIVILEGED', 15),
('OCCASIONAL', 2);


INSERT INTO users (username, password, role) VALUES 
('admin1', 'admin123', 'ADMIN');


INSERT INTO users (username, password, role) VALUES 
('Tejaswini', 'Teja123', 'EMPLOYEE'),
('Tanik', 'Tanik123','EMPLOYEE');

select * from leaves;
delete from leaves where leave_id=3;

INSERT INTO leaves (user_id, leave_type_id, start_date, end_date, status) 
VALUES (
    (SELECT user_id FROM users WHERE username='Tanik'),
    (SELECT leave_type_id FROM leave_types WHERE type_name='PRIVILEGED'),
    '2025-08-29', '2025-08-31', 'PENDING'
);
