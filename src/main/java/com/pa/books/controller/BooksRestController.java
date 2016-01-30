package com.pa.books.controller;

import com.pa.books.persistence.BookDAO;
import com.pa.books.entity.Book;
import com.pa.books.persistence.exception.BookExistsPersistenceException;
import com.pa.books.persistence.exception.BookPersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author apo
 */
@RestController
@RequestMapping(value = "/rest")
public class BooksRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksRestController.class);

    @Autowired
    private BookDAO bookDAO;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> findAllBooks() {
        try {
            List<Book> books = bookDAO.findAllBooks();
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (BookPersistenceException e) {
            LOGGER.error("Exception while getting all books", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/books/save", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> saveBook(@RequestBody Book book) {
        try {
            bookDAO.saveBook(book);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (BookExistsPersistenceException e) {
            LOGGER.error("Book with isbn " + book.getIsbn() + " already exists", e);
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (BookPersistenceException e) {
            LOGGER.error("Exception while saving book", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
