package com.library.management.service;

import com.library.management.dao.BookDAO;
import com.library.management.dao.TransactionDAO;
import com.library.management.dao.UserDAO;
import com.library.management.dao.impl.BookDAOImpl;
import com.library.management.dao.impl.TransactionDAOImpl;
import com.library.management.dao.impl.UserDAOImpl;
import com.library.management.model.Book;
import com.library.management.model.Transaction;
import com.library.management.model.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TransactionService {
    private TransactionDAO transactionDAO;
    private UserDAO userDAO;
    private BookDAO bookDAO;
    
    public TransactionService() {
        this.transactionDAO = new TransactionDAOImpl();
        this.userDAO = new UserDAOImpl();
        this.bookDAO = new BookDAOImpl();
    }
    
    public String borrowBook(int userId, int bookId) {
        // Check if user exists
        User user = userDAO.getUserById(userId);
        if (user == null) {
            return "User not found";
        }
        
        // Check if book exists
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            return "Book not found";
        }
        
        // Check if user has an active transaction for this book
        Transaction existingTransaction = transactionDAO.getActiveTransaction(userId, bookId);
        if (existingTransaction != null) {
            return "User already has this book borrowed";
        }
        
        // Check if book is available
        if (!bookDAO.isBookAvailable(bookId)) {
            return "Book is not available";
        }
        
        // Create transaction
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(14); // 2 weeks borrowing period
        
        Transaction transaction = new Transaction(userId, bookId, borrowDate, dueDate);
        transactionDAO.addTransaction(transaction);
        
        // Update book availability
        int newAvailableCopies = book.getAvailableCopies() - 1;
        bookDAO.updateBookAvailability(bookId, newAvailableCopies);
        
        return "Book borrowed successfully";
    }
    
    public String returnBook(int userId, int bookId) {
        // Check if user exists
        User user = userDAO.getUserById(userId);
        if (user == null) {
            return "User not found";
        }
        
        // Check if book exists
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            return "Book not found";
        }
        
        // Get active transaction
        Transaction transaction = transactionDAO.getActiveTransaction(userId, bookId);
        if (transaction == null) {
            return "No active borrowing record found for this user and book";
        }
        
        // Update transaction
        transaction.setReturnDate(LocalDate.now());
        transaction.setStatus("RETURNED");
        transactionDAO.updateTransaction(transaction);
        
        // Update book availability
        int newAvailableCopies = book.getAvailableCopies() + 1;
        bookDAO.updateBookAvailability(bookId, newAvailableCopies);
        
        // Check if book is returned late
        if (LocalDate.now().isAfter(transaction.getDueDate())) {
            long daysOverdue = ChronoUnit.DAYS.between(transaction.getDueDate(), LocalDate.now());
            return "Book returned successfully. Book was " + daysOverdue + " days overdue.";
        }
        
        return "Book returned successfully";
    }
    
    public List<Transaction> getUserTransactions(int userId) {
        return transactionDAO.getTransactionsByUserId(userId);
    }
    
    public List<Transaction> getBookTransactions(int bookId) {
        return transactionDAO.getTransactionsByBookId(bookId);
    }
    
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }
    
    public List<Transaction> getOverdueTransactions() {
        return transactionDAO.getOverdueTransactions();
    }
}