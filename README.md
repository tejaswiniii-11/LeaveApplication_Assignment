
# Leave Management Application

A **Java-based Leave Management System** built using **JSP, Servlets, JDBC, MySQL, and JSTL**. The application provides separate interfaces for **Admins** and **Employees**, enabling efficient leave tracking and management.  

---

## Table of Contents

- [Features](#features)  
- [Technologies Used](#technologies-used)  
- [Database Structure](#database-structure)  
- [Project Setup](#project-setup)  
- [Usage](#usage)  
- [Screenshots](#screenshots)  
- [Contributing](#contributing)  
- [License](#license)  

---

## Features

### Employee Interface

- Apply for **Casual, Privileged, or Occasional** leaves.  
- View all leaves applied along with **status**.  
- View remaining leaves by type.  
- Constraints:  
  - Max **25 leaves per year**.  
  - Cannot apply for past dates.  

### Admin Interface

- View **all leaves**, or filter by **Pending, Approved, Rejected**.  
- Approve or Reject pending leave requests.  
- Dashboard shows **color-coded status cards** for easy navigation.  
- Logout functionality.  

### Authentication

- User login with **role-based access** (Admin / Employee).  
- Session management using `HttpSession`.  
- Access restricted to logged-in users only.  

---

## Technologies Used

- **Backend:** Java, Servlets, JDBC  
- **Frontend:** JSP, JSTL, Bootstrap 5, HTML, CSS  
- **Database:** MySQL  
- **Build Tool:** Apache Tomcat  
- **IDE:** Eclipse / IntelliJ IDEA  

---

## Database Structure

**Database:** `leave_management`

### Tables

1. **users**
```sql
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('ADMIN', 'EMPLOYEE') NOT NULL
);
````

2. **leave\_types**

```sql
CREATE TABLE leave_types (
    leave_type_id INT AUTO_INCREMENT PRIMARY KEY,
    type_name ENUM('CASUAL', 'PRIVILEGED', 'OCCASIONAL') NOT NULL UNIQUE,
    total_allowed INT NOT NULL
);
```

3. **leaves**

```sql
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
```

---

## Project Setup

1. **Clone the repository**

```bash
git clone <your-repo-url>
```

2. **Database Setup**

   * Create MySQL database `leave_management`.
   * Run the SQL scripts in `database.sql` to create tables and sample data.

3. **Configure JDBC Connection**

   * Update `DBConnection.java` (or your connection class) with your **MySQL username and password**.

4. **Deploy on Tomcat**

   * Import project into **Eclipse / IntelliJ**.
   * Add the project to **Apache Tomcat server**.
   * Start server and access via browser:

```
http://localhost:8080/<project_name>/login.jsp
```

---

## Usage

### Employee

1. Login using **Employee credentials**.
2. Apply for leave by selecting leave type and dates.
3. View status of all leaves.
4. View remaining leaves.
5. Logout.

### Admin

1. Login using **Admin credentials**.
2. Click the leave status cards (All / Pending / Approved / Rejected).
3. Approve or Reject pending leaves using action buttons.
4. Logout.

---

## Screenshots

*(Add screenshots of your login page, employee dashboard, admin dashboard, and leave table here)*

---

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/feature-name`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/feature-name`).
5. Open a Pull Request.

---

## License

This project is **open-source** and available under the [MIT License](LICENSE).

---

```

---

If you want, I can also make a **shorter, GitHub-ready README** with **badges, live screenshots, and demo instructions** that looks more professional for your repo.  

Do you want me to create that version too?
```
