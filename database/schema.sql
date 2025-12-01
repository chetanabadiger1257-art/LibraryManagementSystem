-- Create database
CREATE DATABASE IF NOT EXISTS library_management;
USE library_management;

-- Users table
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    membership_date DATE NOT NULL,
    membership_status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE'
);

-- Books table
CREATE TABLE books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100) NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    publication_year YEAR,
    genre VARCHAR(50),
    total_copies INT DEFAULT 1,
    available_copies INT DEFAULT 1,
    created_date DATE NOT NULL
);

-- Transactions table (borrowing/returning records)
CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    book_id INT,
    borrow_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE NULL,
    status ENUM('BORROWED', 'RETURNED', 'OVERDUE') DEFAULT 'BORROWED',
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);

-- Insert sample data
INSERT INTO users (name, email, phone, membership_date) VALUES
('John Doe', 'john.doe@email.com', '123-456-7890', '2023-01-15'),
('Jane Smith', 'jane.smith@email.com', '098-765-4321', '2023-02-20');

INSERT INTO books (title, author, isbn, publication_year, genre, total_copies, available_copies, created_date) VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', '978-0-7432-7356-5', 1925, 'Fiction', 3, 3, '2023-01-10'),
('To Kill a Mockingbird', 'Harper Lee', '978-0-06-112008-4', 1960, 'Fiction', 2, 2, '2023-01-10'),
('1984', 'George Orwell', '978-0-452-28423-4', 1949, 'Dystopian Fiction', 4, 4, '2023-01-10');