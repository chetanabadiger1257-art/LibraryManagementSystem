package com.library.management.service;

import com.library.management.dao.BookDAO;
import com.library.management.dao.impl.BookDAOImpl;
import com.library.management.model.Book;

import java.time.LocalDate;
import java.util.List;

public class BookService {
    private BookDAO bookDAO;
    
    public BookService() {
        this.bookDAO = new BookDAOImpl();
    }
    
    public boolean addBook(String title, String author, String isbn, int publicationYear, String genre, int copies) {
        // Check if book with same ISBN already exists
        if (bookDAO.getBookByISBN(isbn) != null) {
            return false;
        }
        
        // Create new book
        Book book = new Book(title, author, isbn, publicationYear, genre, copies, LocalDate.now());
        bookDAO.addBook(book);
        return true;
    }
    
    public Book getBookById(int bookId) {
        return bookDAO.getBookById(bookId);
    }
    
    public Book getBookByISBN(String isbn) {
        return bookDAO.getBookByISBN(isbn);
    }
    
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }
    
    public List<Book> searchBooksByTitle(String title) {
        return bookDAO.searchBooksByTitle(title);
    }
    
    public List<Book> searchBooksByAuthor(String author) {
        return bookDAO.searchBooksByAuthor(author);
    }
    
    public boolean updateBook(int bookId, String title, String author, String isbn, int publicationYear, String genre, int totalCopies) {
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            return false;
        }
        
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setPublicationYear(publicationYear);
        book.setGenre(genre);
        book.setTotalCopies(totalCopies);
        // Note: We're not updating available copies here as that's managed by transactions
        bookDAO.updateBook(book);
        return true;
    }
    
    public boolean deleteBook(int bookId) {
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            return false;
        }
        
        bookDAO.deleteBook(bookId);
        return true;
    }
    
    public boolean isBookAvailable(int bookId) {
        return bookDAO.isBookAvailable(bookId);
    }
}