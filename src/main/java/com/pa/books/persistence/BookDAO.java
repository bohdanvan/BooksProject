package com.pa.books.persistence;

import com.pa.books.entity.Book;
import com.pa.books.persistence.exception.BookPersistenceException;
import com.pa.books.persistence.exception.BookExistsPersistenceException;

import java.util.List;

/**
 * @author apo
 */
public interface BookDAO {

    List<Book> findAllBooks() throws BookPersistenceException;

    /**
     *
     * @param book
     * @throws BookExistsPersistenceException while book with same ISBN already exists
     * @throws BookPersistenceException while storage error occurred
     */
    void saveBook(Book book) throws BookPersistenceException;

}
