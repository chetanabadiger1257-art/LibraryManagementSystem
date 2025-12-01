# Library Management System

A Java-based Library Management System with a clear separation of concerns using JDBC and MySQL.

## Features

- User Management (Registration, Update, Deactivation)
- Book Management (Add, Update, Search, Delete)
- Transaction Management (Borrow, Return, Overdue Tracking)
- Clean MVC architecture with DAO pattern

## Project Structure

```
LibraryManagementSystem/
├── database/
│   └── schema.sql          # Database schema and sample data
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── library/
│                   └── management/
│                       ├── model/      # Entity classes
│                       ├── dao/        # Data Access Object interfaces
│                       ├── dao/impl/   # DAO implementations
│                       ├── service/    # Business logic
│                       └── util/       # Utility classes
└── README.md
```

## Prerequisites

- Java 8 or higher
- MySQL Server
- MySQL Connector/J (JDBC Driver)

## Installation

1. Download the MySQL Connector/J JDBC driver and place it in the `lib` directory
2. Run the setup script `setup.bat` (Windows) or execute the commands manually

## Database Setup

1. Create a MySQL database named `library_management`
2. Execute the SQL script in `database/schema.sql` to create tables and sample data
3. Update the database credentials in `util/DatabaseConnection.java` if needed

## How to Run

1. Compile the Java source files
2. Add the MySQL JDBC driver to the classpath
3. Run the main class: `com.library.management.LibraryManagementSystem`

## Architecture

### Model Layer
- `User` - Represents library users
- `Book` - Represents library books
- `Transaction` - Represents borrowing/returning transactions

### DAO Layer
- `UserDAO` - Interface for user data operations
- `BookDAO` - Interface for book data operations
- `TransactionDAO` - Interface for transaction data operations
- `*Impl` - Implementation classes for the DAO interfaces

### Service Layer
- `UserService` - Business logic for user management
- `BookService` - Business logic for book management
- `TransactionService` - Business logic for transaction management

### UI Layer
- `LibraryManagementSystem` - Console-based user interface

## Design Patterns Used

1. **DAO Pattern** - Separates data access logic from business logic
2. **Singleton Pattern** - Database connection management
3. **MVC Pattern** - Separation of Model, View, and Controller logic

## Security Features

- Input validation
- SQL injection prevention through prepared statements
- Proper error handling

## Future Enhancements

- Web-based UI using Java Servlets/JSP
- Admin panel with special privileges
- Email notifications for overdue books
- Fine system for late returns
- Reporting and analytics