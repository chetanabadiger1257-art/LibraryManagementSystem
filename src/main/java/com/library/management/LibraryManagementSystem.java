package com.library.management;

import com.library.management.service.BookService;
import com.library.management.service.TransactionService;
import com.library.management.service.UserService;

import java.util.Scanner;

public class LibraryManagementSystem {
    private static UserService userService = new UserService();
    private static BookService bookService = new BookService();
    private static TransactionService transactionService = new TransactionService();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Welcome to the Library Management System!");
        
        while (true) {
            showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    handleUserManagement();
                    break;
                case 2:
                    handleBookManagement();
                    break;
                case 3:
                    handleTransactionManagement();
                    break;
                case 4:
                    System.out.println("Thank you for using the Library Management System!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void showMainMenu() {
        System.out.println("\n=== Library Management System ===");
        System.out.println("1. User Management");
        System.out.println("2. Book Management");
        System.out.println("3. Transaction Management");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void handleUserManagement() {
        while (true) {
            System.out.println("\n=== User Management ===");
            System.out.println("1. Register User");
            System.out.println("2. View All Users");
            System.out.println("3. View User Details");
            System.out.println("4. Update User");
            System.out.println("5. Deactivate User");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    viewAllUsers();
                    break;
                case 3:
                    viewUserDetails();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    deactivateUser();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void handleBookManagement() {
        while (true) {
            System.out.println("\n=== Book Management ===");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Books by Title");
            System.out.println("4. Search Books by Author");
            System.out.println("5. View Book Details");
            System.out.println("6. Update Book");
            System.out.println("7. Delete Book");
            System.out.println("8. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    searchBooksByTitle();
                    break;
                case 4:
                    searchBooksByAuthor();
                    break;
                case 5:
                    viewBookDetails();
                    break;
                case 6:
                    updateBook();
                    break;
                case 7:
                    deleteBook();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void handleTransactionManagement() {
        while (true) {
            System.out.println("\n=== Transaction Management ===");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. View User Transactions");
            System.out.println("4. View Book Transactions");
            System.out.println("5. View All Transactions");
            System.out.println("6. View Overdue Books");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    borrowBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    viewUserTransactions();
                    break;
                case 4:
                    viewBookTransactions();
                    break;
                case 5:
                    viewAllTransactions();
                    break;
                case 6:
                    viewOverdueBooks();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    // User Management Methods
    private static void registerUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter user email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter user phone: ");
        String phone = scanner.nextLine();
        
        boolean success = userService.registerUser(name, email, phone);
        if (success) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Failed to register user. Email might already exist.");
        }
    }
    
    private static void viewAllUsers() {
        System.out.println("\n=== All Users ===");
        userService.getAllUsers().forEach(System.out::println);
    }
    
    private static void viewUserDetails() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        var user = userService.getUserById(userId);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("User not found.");
        }
    }
    
    private static void updateUser() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter new phone: ");
        String phone = scanner.nextLine();
        
        boolean success = userService.updateUser(userId, name, email, phone);
        if (success) {
            System.out.println("User updated successfully!");
        } else {
            System.out.println("Failed to update user. User might not exist.");
        }
    }
    
    private static void deactivateUser() {
        System.out.print("Enter user ID to deactivate: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        boolean success = userService.deactivateUser(userId);
        if (success) {
            System.out.println("User deactivated successfully!");
        } else {
            System.out.println("Failed to deactivate user. User might not exist.");
        }
    }
    
    // Book Management Methods
    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();
        
        System.out.print("Enter publication year: ");
        int publicationYear = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter book genre: ");
        String genre = scanner.nextLine();
        
        System.out.print("Enter number of copies: ");
        int copies = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        boolean success = bookService.addBook(title, author, isbn, publicationYear, genre, copies);
        if (success) {
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Failed to add book. ISBN might already exist.");
        }
    }
    
    private static void viewAllBooks() {
        System.out.println("\n=== All Books ===");
        bookService.getAllBooks().forEach(System.out::println);
    }
    
    private static void searchBooksByTitle() {
        System.out.print("Enter title to search: ");
        String title = scanner.nextLine();
        
        var books = bookService.searchBooksByTitle(title);
        if (books.isEmpty()) {
            System.out.println("No books found with that title.");
        } else {
            System.out.println("\n=== Search Results ===");
            books.forEach(System.out::println);
        }
    }
    
    private static void searchBooksByAuthor() {
        System.out.print("Enter author to search: ");
        String author = scanner.nextLine();
        
        var books = bookService.searchBooksByAuthor(author);
        if (books.isEmpty()) {
            System.out.println("No books found by that author.");
        } else {
            System.out.println("\n=== Search Results ===");
            books.forEach(System.out::println);
        }
    }
    
    private static void viewBookDetails() {
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        var book = bookService.getBookById(bookId);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Book not found.");
        }
    }
    
    private static void updateBook() {
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter new title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter new author: ");
        String author = scanner.nextLine();
        
        System.out.print("Enter new ISBN: ");
        String isbn = scanner.nextLine();
        
        System.out.print("Enter new publication year: ");
        int publicationYear = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter new genre: ");
        String genre = scanner.nextLine();
        
        System.out.print("Enter new total copies: ");
        int totalCopies = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        boolean success = bookService.updateBook(bookId, title, author, isbn, publicationYear, genre, totalCopies);
        if (success) {
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Failed to update book. Book might not exist.");
        }
    }
    
    private static void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        boolean success = bookService.deleteBook(bookId);
        if (success) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Failed to delete book. Book might not exist.");
        }
    }
    
    // Transaction Management Methods
    private static void borrowBook() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        String result = transactionService.borrowBook(userId, bookId);
        System.out.println(result);
    }
    
    private static void returnBook() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        String result = transactionService.returnBook(userId, bookId);
        System.out.println(result);
    }
    
    private static void viewUserTransactions() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        var transactions = transactionService.getUserTransactions(userId);
        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this user.");
        } else {
            System.out.println("\n=== User Transactions ===");
            transactions.forEach(System.out::println);
        }
    }
    
    private static void viewBookTransactions() {
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        var transactions = transactionService.getBookTransactions(bookId);
        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this book.");
        } else {
            System.out.println("\n=== Book Transactions ===");
            transactions.forEach(System.out::println);
        }
    }
    
    private static void viewAllTransactions() {
        System.out.println("\n=== All Transactions ===");
        transactionService.getAllTransactions().forEach(System.out::println);
    }
    
    private static void viewOverdueBooks() {
        var overdueTransactions = transactionService.getOverdueTransactions();
        if (overdueTransactions.isEmpty()) {
            System.out.println("No overdue books found.");
        } else {
            System.out.println("\n=== Overdue Books ===");
            overdueTransactions.forEach(System.out::println);
        }
    }
}