# Getting Started with Library Management System

## Prerequisites

Before you begin, ensure you have the following installed:
- Java Development Kit (JDK) 8 or higher
- MySQL Server 5.7 or higher
- MySQL Workbench or command-line client (optional but recommended)

## Installation Steps

### 1. Database Setup

1. Start your MySQL server
2. Connect to MySQL as a user with administrative privileges (typically root)
3. Execute the database schema:
   ```sql
   CREATE DATABASE IF NOT EXISTS library_management;
   USE library_management;
   SOURCE database/schema.sql;
   ```

Alternatively, you can run:
```bash
mysql -u root -p < database/schema.sql
```

### 2. Configure Database Connection

Update the database connection details in `src/main/java/com/library/management/util/DatabaseConnection.java` if needed:
- URL: jdbc:mysql://localhost:3306/library_management
- USERNAME: root (change if needed)
- PASSWORD: password (change to your MySQL root password)

### 3. Download Dependencies

Download the MySQL Connector/J JDBC driver:
1. Visit the [MySQL Connector/J download page](https://dev.mysql.com/downloads/connector/j/)
2. Download the platform independent ZIP file
3. Extract the `mysql-connector-java-{version}.jar` file
4. Place it in the `lib` directory

### 4. Compilation

#### Option 1: Using Maven (Recommended)
```bash
mvn compile
```

#### Option 2: Manual Compilation
```bash
mkdir classes
javac -d classes -cp "lib/*:src/main/java" src/main/java/com/library/management/*.java src/main/java/com/library/management/*/*.java src/main/java/com/library/management/*/*/*.java
```

On Windows, use semicolons instead of colons:
```cmd
javac -d classes -cp "lib/*;src/main/java" src/main/java/com/library/management/*.java src/main/java/com/library/management/*/*.java src/main/java/com/library/management/*/*/*.java
```

### 5. Running the Application

#### Option 1: Using Maven
```bash
mvn exec:java -Dexec.mainClass="com.library.management.LibraryManagementSystem"
```

#### Option 2: Manual Execution
```bash
java -cp "classes:lib/*" com.library.management.LibraryManagementSystem
```

On Windows:
```cmd
java -cp "classes;lib/*" com.library.management.LibraryManagementSystem
```

## Using the System

### Main Menu Options
1. **User Management**: Register, view, update, and deactivate users
2. **Book Management**: Add, search, update, and delete books
3. **Transaction Management**: Borrow and return books, view transaction history
4. **Exit**: Close the application

### Sample Operations

#### Register a New User
1. Select "User Management" from the main menu
2. Select "Register User"
3. Enter user details when prompted

#### Add a New Book
1. Select "Book Management" from the main menu
2. Select "Add Book"
3. Enter book details when prompted

#### Borrow a Book
1. Select "Transaction Management" from the main menu
2. Select "Borrow Book"
3. Enter user ID and book ID when prompted

## Troubleshooting

### Common Issues

1. **ClassNotFoundException: com.mysql.cj.jdbc.Driver**
   - Ensure the MySQL JDBC driver is in the lib directory
   - Verify the classpath includes the lib directory

2. **SQLException: Access denied for user**
   - Check database credentials in DatabaseConnection.java
   - Ensure MySQL server is running
   - Verify the user has access to the library_management database

3. **Compilation Errors**
   - Ensure all Java files are compiled together
   - Check that the JDK is properly installed and in PATH

### Database Connection Issues

If you encounter connection issues:
1. Verify MySQL server is running
2. Check that the database `library_management` exists
3. Confirm username and password are correct
4. Ensure the MySQL port (default 3306) is correct

## System Maintenance

### Adding More Sample Data
You can add more sample data by modifying the `database/schema.sql` file or by using the application's add features.

### Backing Up Data
Use mysqldump to create backups:
```bash
mysqldump -u root -p library_management > backup.sql
```

### Restoring Data
```bash
mysql -u root -p library_management < backup.sql
```

## Extending the System

### Adding New Features
1. Create new model classes in the `model` package
2. Add DAO interfaces in the `dao` package
3. Implement DAOs in the `dao.impl` package
4. Create service classes in the `service` package
5. Update the main application UI to include new functionality

### Customizing Business Rules
Modify the service classes to change business logic such as:
- Borrowing period duration
- Overdue calculation rules
- User registration requirements

## Support

For issues not covered in this guide:
1. Check the console output for error messages
2. Verify all prerequisites are properly installed
3. Ensure database connectivity
4. Confirm all files are in their correct locations

## Next Steps

After successfully running the system:
1. Explore all menu options to understand the functionality
2. Add your own books and users
3. Test borrowing and returning workflows
4. Consider extending the system with additional features