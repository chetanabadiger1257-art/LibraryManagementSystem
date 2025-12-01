package com.library.management.dao.impl;

import com.library.management.dao.TransactionDAO;
import com.library.management.model.Transaction;
import com.library.management.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    
    @Override
    public void addTransaction(Transaction transaction) {
        String query = "INSERT INTO transactions (user_id, book_id, borrow_date, due_date, status) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setInt(1, transaction.getUserId());
            statement.setInt(2, transaction.getBookId());
            statement.setDate(3, Date.valueOf(transaction.getBorrowDate()));
            statement.setDate(4, Date.valueOf(transaction.getDueDate()));
            statement.setString(5, transaction.getStatus());
            
            int affectedRows = statement.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        transaction.setTransactionId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Transaction getTransactionById(int transactionId) {
        String query = "SELECT * FROM transactions WHERE transaction_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, transactionId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return extractTransactionFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    public List<Transaction> getTransactionsByUserId(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE user_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                transactions.add(extractTransactionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return transactions;
    }
    
    @Override
    public List<Transaction> getTransactionsByBookId(int bookId) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE book_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                transactions.add(extractTransactionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return transactions;
    }
    
    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                transactions.add(extractTransactionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return transactions;
    }
    
    @Override
    public List<Transaction> getOverdueTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE status = 'BORROWED' AND due_date < ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                transactions.add(extractTransactionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return transactions;
    }
    
    @Override
    public void updateTransaction(Transaction transaction) {
        String query = "UPDATE transactions SET user_id = ?, book_id = ?, borrow_date = ?, due_date = ?, return_date = ?, status = ? WHERE transaction_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, transaction.getUserId());
            statement.setInt(2, transaction.getBookId());
            statement.setDate(3, Date.valueOf(transaction.getBorrowDate()));
            statement.setDate(4, Date.valueOf(transaction.getDueDate()));
            statement.setDate(5, transaction.getReturnDate() != null ? Date.valueOf(transaction.getReturnDate()) : null);
            statement.setString(6, transaction.getStatus());
            statement.setInt(7, transaction.getTransactionId());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteTransaction(int transactionId) {
        String query = "DELETE FROM transactions WHERE transaction_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, transactionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Transaction getActiveTransaction(int userId, int bookId) {
        String query = "SELECT * FROM transactions WHERE user_id = ? AND book_id = ? AND status = 'BORROWED'";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, userId);
            statement.setInt(2, bookId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return extractTransactionFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    private Transaction extractTransactionFromResultSet(ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(resultSet.getInt("transaction_id"));
        transaction.setUserId(resultSet.getInt("user_id"));
        transaction.setBookId(resultSet.getInt("book_id"));
        transaction.setBorrowDate(resultSet.getDate("borrow_date").toLocalDate());
        transaction.setDueDate(resultSet.getDate("due_date").toLocalDate());
        
        Date returnDate = resultSet.getDate("return_date");
        if (returnDate != null) {
            transaction.setReturnDate(returnDate.toLocalDate());
        }
        
        transaction.setStatus(resultSet.getString("status"));
        return transaction;
    }
}