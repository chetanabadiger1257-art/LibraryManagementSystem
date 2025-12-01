package com.library.management.dao.impl;

import com.library.management.dao.BookDAO;
import com.library.management.model.Book;
import com.library.management.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    
    @Override
    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author, isbn, publication_year, genre, total_copies, available_copies, created_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setInt(4, book.getPublicationYear());
            statement.setString(5, book.getGenre());
            statement.setInt(6, book.getTotalCopies());
            statement.setInt(7, book.getAvailableCopies());
            statement.setDate(8, Date.valueOf(book.getCreatedDate()));
            
            int affectedRows = statement.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        book.setBookId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Book getBookById(int bookId) {
        String query = "SELECT * FROM books WHERE book_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return extractBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    public Book getBookByISBN(String isbn) {
        String query = "SELECT * FROM books WHERE isbn = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return extractBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                books.add(extractBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return books;
    }
    
    @Override
    public List<Book> searchBooksByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE title LIKE ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, "%" + title + "%");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                books.add(extractBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return books;
    }
    
    @Override
    public List<Book> searchBooksByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE author LIKE ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, "%" + author + "%");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                books.add(extractBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return books;
    }
    
    @Override
    public void updateBook(Book book) {
        String query = "UPDATE books SET title = ?, author = ?, isbn = ?, publication_year = ?, genre = ?, total_copies = ?, available_copies = ?, created_date = ? WHERE book_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setInt(4, book.getPublicationYear());
            statement.setString(5, book.getGenre());
            statement.setInt(6, book.getTotalCopies());
            statement.setInt(7, book.getAvailableCopies());
            statement.setDate(8, Date.valueOf(book.getCreatedDate()));
            statement.setInt(9, book.getBookId());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteBook(int bookId) {
        String query = "DELETE FROM books WHERE book_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public boolean isBookAvailable(int bookId) {
        Book book = getBookById(bookId);
        return book != null && book.getAvailableCopies() > 0;
    }
    
    @Override
    public void updateBookAvailability(int bookId, int newAvailableCopies) {
        String query = "UPDATE books SET available_copies = ? WHERE book_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, newAvailableCopies);
            statement.setInt(2, bookId);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private Book extractBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setBookId(resultSet.getInt("book_id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setPublicationYear(resultSet.getInt("publication_year"));
        book.setGenre(resultSet.getString("genre"));
        book.setTotalCopies(resultSet.getInt("total_copies"));
        book.setAvailableCopies(resultSet.getInt("available_copies"));
        book.setCreatedDate(resultSet.getDate("created_date").toLocalDate());
        return book;
    }
}