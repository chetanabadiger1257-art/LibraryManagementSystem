package com.library.management.dao;

import com.library.management.model.Transaction;
import java.util.List;

public interface TransactionDAO {
    void addTransaction(Transaction transaction);
    Transaction getTransactionById(int transactionId);
    List<Transaction> getTransactionsByUserId(int userId);
    List<Transaction> getTransactionsByBookId(int bookId);
    List<Transaction> getAllTransactions();
    List<Transaction> getOverdueTransactions();
    void updateTransaction(Transaction transaction);
    void deleteTransaction(int transactionId);
    Transaction getActiveTransaction(int userId, int bookId);
}