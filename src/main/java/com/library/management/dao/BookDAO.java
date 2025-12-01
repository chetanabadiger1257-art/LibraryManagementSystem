package com.library.management.dao;

import com.library.management.model.Book;
import java.util.List;

public interface BookDAO {
    void addBook(Book book);
    Book getBookById(int bookId);
    Book getBookByISBN(String isbn);
    List<Book> getAllBooks();
    List<Book> searchBooksByTitle(String title);
    List<Book> searchBooksByAuthor(String author);
    void updateBook(Book book);
    void deleteBook(int bookId);
    boolean isBookAvailable(int bookId);
    void updateBookAvailability(int bookId, int newAvailableCopies);
}